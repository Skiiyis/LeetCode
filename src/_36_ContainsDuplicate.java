import java.util.HashMap;

/**
 * 存在重复元素
 * 给定一个整数数组，判断是否存在重复元素。
 * <p>
 * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3,1]
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4]
 * 输出: false
 * 示例 3:
 * <p>
 * 输入: [1,1,1,3,3,4,3,2,4,2]
 * 输出: true
 */
public class _36_ContainsDuplicate {

    public static void main(String[] args) {
        System.out.println(containsDuplicate(new int[]{1,1,1,3,3,4,3,2,4,2}));
    }

    public static boolean containsDuplicate(int[] nums) {
        HashMap map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.get(nums[i]) == null) map.put(nums[i], 1);
            else return true;
        }
        return false;
    }
}
