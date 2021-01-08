import java.util.Arrays;

/**
 * 搜索旋转排序数组
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * <p>
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * <p>
 * 你可以假设数组中不存在重复的元素。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 */
public class _08_SearchRotationArray {
    public static void main(String[] args) {
        System.out.println(search(new int[]{9, 0, 2, 7, 8}, 3));
    }

    public static int search(int[] nums, int target) {
        return search(nums, target, 0);
    }

    public static int search(int[] nums, int target, int firstNumsIndex) {
        int[] part1 = Arrays.copyOfRange(nums, 0, nums.length / 2);
        int[] part2 = Arrays.copyOfRange(nums, nums.length / 2, nums.length);
        if (isSorted(part1)) {
            if (isBeInRange(part1, target)) {
                int index = Arrays.binarySearch(part1, target);
                if (index < 0) {
                    return -1;
                } else {
                    return index + firstNumsIndex;
                }
            } else {
                return search(part2, target, firstNumsIndex + part1.length);
            }
        } else if (isSorted(part2)) {
            if (isBeInRange(part2, target)) {
                int index = Arrays.binarySearch(part2, target);
                if (index < 0) {
                    return -1;
                } else {
                    return index + firstNumsIndex + part1.length;
                }
            } else {
                return search(part1, target, firstNumsIndex);
            }
        } else {
            return -1;
        }
    }

    public static boolean isSorted(int[] nums) {
        return nums.length > 0 && nums[0] <= nums[nums.length - 1];
    }

    public static boolean isBeInRange(int[] nums, int num) {
        return nums.length > 0 && nums[0] <= num && nums[nums.length - 1] >= num;
    }
}
