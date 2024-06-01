package rain.tool;

/**
 * 记录一些算法相关方法
 *
 * @since myJava1.2
 */
public class Algorithm {
    /**
     * 二分查找基础版
     * 二分查找：一种在有序数组中查找特定元素的搜索算法
     *
     * @param arr    待查找的有序数组，arr[0]<=arr[1]<=arr[2]<=...<=arr[n-1]
     * @param target 待查找的目标值
     * @return 目标值的索引
     */
    public int binarySearchBasic(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;//中间值
            if (target < arr[mid]) {
                right = mid - 1;//目标在左边
            } else if (arr[mid] < target) {
                left = mid + 1;//目标在右边
            } else {
                return mid;//找到目标值
            }
        }
        return -1; // 找不到目标值
    }

    /**
     * 二分查找的改动优化版，比起基础版
     *
     * @param arr    待查找的有序数组，arr[0]<=arr[1]<=arr[2]<=...<=arr[n-1]
     * @param target 待查找的目标值
     * @return 目标值的索引
     */
    public int binarySearchBasicAlter(int[] arr, int target) {
        int left = 0;
        int right = arr.length;
        while (left < right) {
            int mid = (left + right) >>> 1;//中间值
            if (target < arr[mid]) {
                right = mid;//目标在左边
            } else if (arr[mid] < target) {
                left = mid + 1;//目标在右边
            } else {
                return mid;//找到目标值
            }
        }
        return -1; // 找不到目标值
    }
}
