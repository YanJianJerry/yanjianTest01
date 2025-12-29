package algorithm.test1;

import java.util.*;
import java.util.stream.Collectors;

public class FlexibleTSPSolver {

    public static class Point {
        public final String id;
        public final double x, y;

        public Point(String id, double x, double y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }

        public Point(double x, double y) {
            this(null, x, y);
        }

        public double dist(Point other) {
            return Math.sqrt((x - other.x) * (x - other.x) + (y - other.y) * (y - other.y));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return Objects.equals(id, point.id) &&
                   Double.compare(point.x, x) == 0 &&
                   Double.compare(point.y, y) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, x, y);
        }

        @Override
        public String toString() {
            return id != null ? id : String.format("(%.1f,%.1f)", x, y);
        }
    }

    // ==================== 公共接口 ====================
    /**
     * 求解路径：支持起点=终点（回路）或起点≠终点（开放路径）
     */
    public static List<Point> solvePath(List<Point> allPoints, Point start, Point end) {
        if (start == null || end == null)
            throw new IllegalArgumentException("Start or end is null");
        if (!allPoints.contains(start) || !allPoints.contains(end))
            throw new IllegalArgumentException("Start or end not in point list");

        if (allPoints.size() == 1) {
            return new ArrayList<>(Arrays.asList(start));
        }

        if (start.equals(end)) {
            return solveTSPCycle(allPoints, start);
        } else {
            return solveOpenTSP(allPoints, start, end);
        }
    }

    // ==================== Open TSP（起点≠终点）====================
    private static List<Point> solveOpenTSP(List<Point> allPoints, Point start, Point end) {
        List<Point> middle = allPoints.stream()
                .filter(p -> !p.equals(start) && !p.equals(end))
                .collect(Collectors.toList());

        List<Point> middlePath = nearestNeighbor(start, middle);
        List<Point> fullPath = new ArrayList<>();
        fullPath.add(start);
        fullPath.addAll(middlePath);
        fullPath.add(end);

        return twoOptFixedEndpoints(fullPath);
    }

    private static List<Point> nearestNeighbor(Point current, List<Point> unvisited) {
        if (unvisited.isEmpty()) return new ArrayList<>();
        List<Point> path = new ArrayList<>();
        Set<Point> remaining = new LinkedHashSet<>(unvisited);
        Point cur = current;
        while (!remaining.isEmpty()) {
            Point next = null;
            double minDist = Double.MAX_VALUE;
            for (Point p : remaining) {
                double d = cur.dist(p);
                if (d < minDist) {
                    minDist = d;
                    next = p;
                }
            }
            path.add(next);
            remaining.remove(next);
            cur = next;
        }
        return path;
    }

    private static List<Point> twoOptFixedEndpoints(List<Point> path) {
        int n = path.size();
        if (n <= 3) return new ArrayList<>(path);

        List<Point> best = new ArrayList<>(path);
        double bestDist = totalDistanceOpen(best);
        boolean improved;

        do {
            improved = false;
            for (int i = 1; i < n - 2; i++) {
                for (int j = i + 2; j < n - 1; j++) {
                    List<Point> newRoute = new ArrayList<>(best);
                    Collections.reverse(newRoute.subList(i + 1, j + 1));
                    double newDist = totalDistanceOpen(newRoute);
                    if (newDist < bestDist) {
                        best = newRoute;
                        bestDist = newDist;
                        improved = true;
                    }
                }
            }
        } while (improved);
        return best;
    }

    private static double totalDistanceOpen(List<Point> path) {
        double d = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            d += path.get(i).dist(path.get(i + 1));
        }
        return d;
    }

    // ==================== TSP Cycle（起点=终点）====================
    private static List<Point> solveTSPCycle(List<Point> allPoints, Point start) {
        // Step 1: 生成初始回路（从任意点开始）
        List<Point> cycle = new ArrayList<>(allPoints);
        // 可选：从 start 开始 NN，但不影响最终旋转
        cycle = nearestNeighborCycle(cycle);

        // Step 2: 2-opt 优化（支持环形）
        cycle = twoOptForCycle(cycle);

        // Step 3: 旋转使 start 在开头
        int idx = cycle.indexOf(start);
        if (idx == -1) throw new RuntimeException("Start not found in cycle");
        List<Point> rotated = new ArrayList<>();
        for (int i = 0; i < cycle.size(); i++) {
            rotated.add(cycle.get((idx + i) % cycle.size()));
        }
        return rotated;
    }

    private static List<Point> nearestNeighborCycle(List<Point> points) {
        List<Point> unvisited = new ArrayList<>(points);
        List<Point> path = new ArrayList<>();
        Point current = unvisited.remove(0);
        path.add(current);
        while (!unvisited.isEmpty()) {
            Point next = null;
            double minDist = Double.MAX_VALUE;
            for (Point p : unvisited) {
                double d = current.dist(p);
                if (d < minDist) {
                    minDist = d;
                    next = p;
                }
            }
            path.add(next);
            unvisited.remove(next);
            current = next;
        }
        return path;
    }

    /**
     * 完整的 2-opt for TSP Cycle（支持跨越首尾的边交换）
     */
    private static List<Point> twoOptForCycle(List<Point> path) {
        int n = path.size();
        if (n <= 2) return new ArrayList<>(path);

        List<Point> best = new ArrayList<>(path);
        double bestDist = totalDistanceCycle(best);
        boolean improved;

        do {
            improved = false;
            // 遍历所有可能的 (i, j) 边对，其中 i < j-1（避免相邻）
            for (int i = 0; i < n; i++) {
                for (int j = i + 2; j < n; j++) {
                    // 情况1: 普通反转 [i+1, j]
                    List<Point> newRoute = new ArrayList<>(best);
                    reverseSegment(newRoute, i + 1, j);
                    double newDist = totalDistanceCycle(newRoute);
                    if (newDist < bestDist) {
                        best = newRoute;
                        bestDist = newDist;
                        improved = true;
                    }
                }
                // 情况2: 反转跨越末尾和开头的部分
                // 即断开 (i, i+1) 和 (n-1, 0)，反转 [i+1, n-1] + [0, k]
                // 等价于：将路径视为环，反转从 i+1 到 i-1 的部分
                // 技巧：我们可以通过“切开环”来模拟
                if (i >= 1) {
                    // 反转 [i+1, n-1] 和 [0, i-1] 整体
                    List<Point> newRoute = new ArrayList<>();
                    newRoute.add(best.get(i));
                    // 添加 [i-1, i-2, ..., 0]
                    for (int k = i - 1; k >= 0; k--) {
                        newRoute.add(best.get(k));
                    }
                    // 添加 [n-1, n-2, ..., i+1]
                    for (int k = n - 1; k >= i + 1; k--) {
                        newRoute.add(best.get(k));
                    }
                    double newDist = totalDistanceCycle(newRoute);
                    if (newDist < bestDist) {
                        best = newRoute;
                        bestDist = newDist;
                        improved = true;
                    }
                }
            }
        } while (improved);
        return best;
    }

    private static void reverseSegment(List<Point> list, int from, int to) {
        while (from < to) {
            Collections.swap(list, from++, to--);
        }
    }

    private static double totalDistanceCycle(List<Point> path) {
        double d = 0;
        int n = path.size();
        for (int i = 0; i < n; i++) {
            d += path.get(i).dist(path.get((i + 1) % n));
        }
        return d;
    }

    // ==================== 示例 ====================
    public static void main(String[] args) {
        List<Point> points = Arrays.asList(
            new Point("A", 0, 0),
            new Point("B", 1, 3),
            new Point("C", 4, 1),
            new Point("D", 5, 5),
            new Point("E", 2, 6)
        );

        // 情况1: 起点 ≠ 终点
        System.out.println("=== Open TSP: A -> E ===");
        List<Point> openPath = solvePath(points, 
            points.stream().filter(p -> "A".equals(p.id)).findFirst().get(),
            points.stream().filter(p -> "E".equals(p.id)).findFirst().get()
        );
        System.out.println(openPath);
        System.out.printf("Distance: %.3f\n\n", totalDistanceOpen(openPath));

        // 情况2: 起点 = 终点 (TSP Cycle)
        System.out.println("=== TSP Cycle: Start/End = A ===");
        List<Point> cyclePath = solvePath(points,
            points.stream().filter(p -> "A".equals(p.id)).findFirst().get(),
            points.stream().filter(p -> "A".equals(p.id)).findFirst().get()
        );
        System.out.println(cyclePath);
        System.out.printf("Cycle distance: %.3f\n", totalDistanceCycle(cyclePath));
        // 注意：cyclePath 不显式包含重复的 A，但逻辑上是 A->...->A
    }
}