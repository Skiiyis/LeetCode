/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 * <p>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */
public class _53_MaxSubArray {

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2,-1}));
    }

    /**
     * 最大子序和。
     * 每来一个数，如果之前的子序和为负数 -> 放弃之前的子序，从新的数开始计算最大自序
     *                      和为正数 -> 将新的数添加到之前的子序中
     * 然后计算最大自序和和之前的最大自序和比较
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (sum < 0) {
                sum = num;
            } else {
                sum += num;
            }
            max = Math.max(max, sum);
        }
        return max;
    }
}
