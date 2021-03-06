/**
 * 多数元素
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 */
public class _35_MajorityElement {

    public static void main(String[] args) {
        System.out.println(
                majorityElement(new int[]{2,2,1,1,1,2,2})
        );
    }

    public static int majorityElement(int[] nums) {
        int times = 0;
        Integer majorityNum = null;
        for (int i = 0; i < nums.length; i++) {
            if (majorityNum == null) {
                majorityNum = nums[i];
                times = 1;
            } else if (majorityNum != nums[i]) {
                times--;
                if (times == 0) {
                    majorityNum = null;
                }
            } else {
                times++;
            }
        }
        return majorityNum;
    }
}
