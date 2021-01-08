/**
 * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 * <p>
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 */
public class _34_MaxProduct {
    public static void main(String[] args) {
        System.out.println(
                maxProduct2(new int[]{2,3,-2,4})
        );
    }

    public static int maxProduct2(int[] nums) {
        int[] dpMax = new int[nums.length];
        int[] dpMin = new int[nums.length];
        //dpMax[i] = max(nums[i],dpMax[i-1]*nums[i],dpMin[i-1]*nums[i])
        //dpMin[i] = min(nums[i],dpMax[i-1]*nums[i],dpMin[i-1]*nums[i])
        //dpMax[0] = nums[0];
        //dpMin[0] = nums[0];
        int maxProduct = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                dpMax[0] = nums[0];
                dpMin[0] = nums[0];
            }else{
                dpMax[i] = Math.max(Math.max(nums[i], dpMax[i - 1] * nums[i]), dpMin[i - 1] * nums[i]);
                dpMin[i] = Math.min(Math.min(nums[i], dpMax[i - 1] * nums[i]), dpMin[i - 1] * nums[i]);
            }
            maxProduct = Math.max(dpMax[i], maxProduct);
        }
        return maxProduct;
    }

    public static int maxProduct(int[] nums) {
        if (nums.length == 1 && nums[0] < 0) return nums[0];
        int nNumberTimes = 0;
        int lastZeroIndex = 0;
        int maxProduct = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                nNumberTimes++;
            }
            if (nums[i] == 0) {
                maxProduct = Math.max(maxProduct(nums, lastZeroIndex, i, nNumberTimes), maxProduct);
                lastZeroIndex = i + 1;
                nNumberTimes = 0;
            }
            if (i == nums.length - 1) {
                maxProduct = Math.max(maxProduct(nums, lastZeroIndex, i + 1, nNumberTimes), maxProduct);
            }
        }
        return maxProduct;
    }

    public static int maxProduct(int[] nums, int start, int end, int nNumberTimes) {
        if (end == start) return 0;
        if (end - start == 1 && nNumberTimes == 1) return 0;
        if (nNumberTimes % 2 == 0) {
            int maxProduct = 1;
            for (int i = start; i < end; i++) {
                maxProduct *= nums[i];
            }
            return maxProduct;
        } else {
            int leftProduct = 1;
            int leftnNumberTimes = 0;
            for (int i = start; i < end; i++) {//[start,end)
                int num = nums[i];
                if (num < 0) {
                    leftnNumberTimes++;
                    if (leftnNumberTimes == nNumberTimes) {
                        break;
                    }
                }
                leftProduct *= num;
            }
            int rightProduct = 1;
            int rightnNumberTimes = 0;
            for (int i = end - 1; i >= start; i--) {
                int num = nums[i];
                if (num < 0) {
                    rightnNumberTimes++;
                    if (rightnNumberTimes == nNumberTimes) {
                        break;
                    }
                }
                rightProduct *= num;
            }
            return Math.max(rightProduct, leftProduct);
        }
    }
}
