package algorithm.test1;

import java.util.*;
import java.util.stream.Collectors;

public class OpenTSPFixedEndpoints {

    public static class Point {
        public final double x, y;
        // 可选：用于精确匹配（避免浮点误差导致 equals 失效）
        public final String id;

        // 主构造函数（完整参数）
        public Point(String id, double x, double y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }

        // 无 ID 的构造函数：委托给主构造函数，传入 null
        public Point(double x, double y) {
            this(null, x, y);  // ✅ 正确：调用上面的构造函数
        }

        public double dist(Point other) {
            return Math.sqrt((x - other.x) * (x - other.x) + (y - other.y) * (y - other.y));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            if (id != null && point.id != null) {
                return id.equals(point.id);
            }
            // 若无 id，则按坐标近似相等（慎用！）
            return Math.abs(x - point.x) < 1e-9 && Math.abs(y - point.y) < 1e-9;
        }

        @Override
        public int hashCode() {
            return id != null ? id.hashCode() : Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return id != null ? id + "(" + x + "," + y + ")" : "(" + x + "," + y + ")";
        }
    }

    // 主求解函数：任意指定 start 和 end
    public static List<Point> solveOpenTSP(
            List<Point> allPoints,
            Point start,
            Point end) {

        if (start == null || end == null) {
            throw new IllegalArgumentException("Start and end cannot be null");
        }

        if (!allPoints.contains(start)) {
            throw new IllegalArgumentException("Start point not in the point list");
        }
        if (!allPoints.contains(end)) {
            throw new IllegalArgumentException("End point not in the point list");
        }

        if (start.equals(end) && allPoints.size() > 1) {
            throw new IllegalArgumentException("Start and end must be different when more than one point exists");
        }

        // 特殊情况：只有起点和终点（可能相同）
        if (allPoints.size() == 1) {
            return new ArrayList<>(Arrays.asList(start));
        }
        if (allPoints.size() == 2) {
            return new ArrayList<>(Arrays.asList(start, end));
        }

        // 构建中间点列表（排除 start 和 end）
        List<Point> middlePoints = allPoints.stream()
                .filter(p -> !p.equals(start) && !p.equals(end))
                .collect(Collectors.toList());

        // 用最近邻生成中间路径（从 start 出发）
        List<Point> middlePath = nearestNeighbor(start, middlePoints);

        // 拼接完整路径
        List<Point> fullPath = new ArrayList<>();
        fullPath.add(start);
        fullPath.addAll(middlePath);
        fullPath.add(end);

        // 2-opt 优化（固定首尾）
        return twoOptFixedEndpoints(fullPath);
    }

    // 最近邻：从 current 出发，访问 unvisited 中所有点
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

    // 计算路径总长度
    public static double totalDistance(List<Point> path) {
        double total = 0.0;
        for (int i = 0; i < path.size() - 1; i++) {
            total += path.get(i).dist(path.get(i + 1));
        }
        return total;
    }

    // 2-opt 优化：固定 index=0 和 index=n-1
    private static List<Point> twoOptFixedEndpoints(List<Point> path) {
        int n = path.size();
        if (n <= 3) return new ArrayList<>(path); // 无法优化

        List<Point> best = new ArrayList<>(path);
        double bestDist = totalDistance(best);
        boolean improved;

        do {
            improved = false;
            // i: 第一条边的起点（i -> i+1），i 不能是最后一个点
            // 起点 index=0 固定，所以 i 从 1 开始
            for (int i = 1; i < n - 2; i++) {
                // j: 第二条边的终点（j -> j+1），j >= i+2，且 j+1 <= n-1 → j <= n-2
                for (int j = i + 2; j < n - 1; j++) {
                    // 反转 [i+1, j] 区间
                    List<Point> newRoute = new ArrayList<>(best);
                    Collections.reverse(newRoute.subList(i + 1, j + 1));

                    double newDist = totalDistance(newRoute);
                    if (newDist < bestDist) {
                        best = newRoute;
                        bestDist = newDist;
                        improved = true;
                        // 注意：不 break，继续寻找更多改进
                    }
                }
            }
        } while (improved);

        return best;
    }

    // ================== 示例 ==================
    public static void main(String[] args) {
        // 定义点集（带 ID 更安全）
        List<Point> points = Arrays.asList(
            new Point("A", 0, 0),
            new Point("B", 1, 3),
            new Point("C", 4, 1),
            new Point("D", 5, 5),
            new Point("E", 2, 6),
            new Point("F", 6, 2)
        );

        // 任意指定起点和终点
        Point start = points.stream().filter(p -> "C".equals(p.id)).findFirst().orElse(null);
        Point end = points.stream().filter(p -> "E".equals(p.id)).findFirst().orElse(null);

        List<Point> result = solveOpenTSP(points, start, end);

        System.out.println("Path from " + start.id + " to " + end.id + ":");
        for (Point p : result) {
            System.out.println("  -> " + p);
        }
        System.out.printf("Total distance: %.3f\n", totalDistance(result));
    }
}