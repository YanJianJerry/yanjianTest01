package algorithm.sort;

public class ClassicSort {
    public static void main(String[] args) {

    }

    /**
     * 插入排序：对整型数组进行升序排序
     * 时间复杂度：O(n²) - 最坏和平均情况
     *            O(n)   - 最好情况（数组已有序）
     * 空间复杂度：O(1) - 原地排序
     *
     * 实现简单，代码直观。
     * 对小规模数据或基本有序的数据效率较高。
     * 是稳定排序（相等元素的相对位置不变）。
     * 原地排序，空间复杂度低。
     *
     * @param arr 待排序的整型数组
     */
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return; // 空数组或只有一个元素，无需排序
        }

        // 从第二个元素开始（索引为1），因为第一个元素可以看作已排序部分
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i]; // 当前要插入的元素
            int j = i - 1;    // 已排序部分的最后一个元素的索引

            // 将所有大于 key 的元素向后移动一位
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            // 将 key 插入到正确的位置
            arr[j + 1] = key;
        }
    }

    /**
     * 冒泡排序：对整型数组进行升序排序
     * 时间复杂度：O(n²) - 最坏和平均情况
     *            O(n)   - 最好情况（数组已有序）
     * 空间复杂度：O(1) - 原地排序
     *
     * 实现简单，代码直观。
     * 对小规模数据效率尚可。
     * 是稳定排序（相等元素的相对位置不变）。
     * 原地排序，空间复杂度低。
     *
     * @param arr 待排序的整型数组
     */
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return; // 空数组或只有一个元素，无需排序
        }

        int n = arr.length;
        // 外层循环控制排序轮数
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false; // 标记本轮是否发生交换
            // 内层循环进行相邻元素比较和交换
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // 交换元素
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            // 如果本轮没有发生交换，说明数组已经有序
            if (!swapped) {
                break;
            }
        }
    }

    /**
     * 选择排序：对整型数组进行升序排序
     * 时间复杂度：O(n²) - 所有情况
     * 空间复杂度：O(1) - 原地排序
     *
     * 实现简单，代码直观。
     * 交换次数少，最多进行n-1次交换。
     * 不是稳定排序。
     * 原地排序，空间复杂度低。
     *
     * @param arr 待排序的整型数组
     */
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return; // 空数组或只有一个元素，无需排序
        }

        int n = arr.length;
        // 外层循环控制排序位置
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i; // 假设当前位置为最小值位置
            // 内层循环寻找未排序部分的最小值
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j; // 更新最小值索引
                }
            }
            // 将找到的最小值与当前位置交换
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }

    /**
     * 快速排序：对整型数组进行升序排序
     * 时间复杂度：O(n log n) - 平均情况
     *            O(n²)     - 最坏情况（每次选的基准都是最大或最小元素）
     * 空间复杂度：O(log n) - 递归调用栈
     *
     * 实现相对复杂，但效率高。
     * 对大规模数据效率很高。
     * 不是稳定排序。
     * 分治思想的典型应用。
     *
     * @param arr 待排序的整型数组
     */
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return; // 空数组或只有一个元素，无需排序
        }
        quickSortHelper(arr, 0, arr.length - 1);
    }

    /**
     * 快速排序辅助方法
     *
     * @param arr   待排序的整型数组
     * @param left  排序区间的左边界
     * @param right 排序区间的右边界
     */
    private static void quickSortHelper(int[] arr, int left, int right) {
        if (left < right) {
            // 获取分区点
            int pivotIndex = partition(arr, left, right);
            // 递归排序左半部分
            quickSortHelper(arr, left, pivotIndex - 1);
            // 递归排序右半部分
            quickSortHelper(arr, pivotIndex + 1, right);
        }
    }

    /**
     * 分区方法，将数组分为小于基准和大于基准的两部分
     *
     * @param arr   待分区的整型数组
     * @param left  分区区间的左边界
     * @param right 分区区间的右边界
     * @return 基准元素的最终位置
     */
    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right]; // 选择最右边的元素作为基准
        int i = left - 1; // 小于基准的元素的索引

        for (int j = left; j < right; j++) {
            // 如果当前元素小于或等于基准
            if (arr[j] <= pivot) {
                i++;
                // 交换元素
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // 将基准元素放到正确位置
        int temp = arr[i + 1];
        arr[i + 1] = arr[right];
        arr[right] = temp;

        return i + 1; // 返回基准元素的位置
    }

    /**
     * 归并排序：对整型数组进行升序排序
     * 时间复杂度：O(n log n) - 所有情况
     * 空间复杂度：O(n) - 需要额外的存储空间
     *
     * 效率稳定，时间复杂度固定。
     * 是稳定排序。
     * 分治思想的典型应用。
     * 需要额外的存储空间。
     *
     * @param arr 待排序的整型数组
     */
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return; // 空数组或只有一个元素，无需排序
        }
        // 创建临时数组，避免在递归中重复创建数组
        int[] temp = new int[arr.length];
        mergeSortHelper(arr, temp, 0, arr.length - 1);
    }

    /**
     * 归并排序辅助方法
     *
     * @param arr   待排序的整型数组
     * @param temp  临时数组，用于合并
     * @param left  排序区间的左边界
     * @param right 排序区间的右边界
     */
    private static void mergeSortHelper(int[] arr, int[] temp, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2; // 防止溢出
            // 递归排序左半部分
            mergeSortHelper(arr, temp, left, mid);
            // 递归排序右半部分
            mergeSortHelper(arr, temp, mid + 1, right);
            // 合并两个已排序的部分
            merge(arr, temp, left, mid, right);
        }
    }

    /**
     * 合并两个已排序的子数组
     *
     * @param arr   原数组
     * @param temp  临时数组
     * @param left  左子数组的起始位置
     * @param mid   左子数组的结束位置
     * @param right 右子数组的结束位置
     */
    private static void merge(int[] arr, int[] temp, int left, int mid, int right) {
        // 将原数组内容复制到临时数组
        System.arraycopy(arr, left, temp, left, right - left + 1);

        int i = left;     // 左子数组的起始索引
        int j = mid + 1;  // 右子数组的起始索引
        int k = left;     // 合并后数组的起始索引

        // 比较两个子数组的元素，将较小的元素放入原数组
        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                arr[k++] = temp[i++];
            } else {
                arr[k++] = temp[j++];
            }
        }

        // 将左子数组剩余元素复制到原数组
        while (i <= mid) {
            arr[k++] = temp[i++];
        }

        // 将右子数组剩余元素复制到原数组
        while (j <= right) {
            arr[k++] = temp[j++];
        }
    }

}
