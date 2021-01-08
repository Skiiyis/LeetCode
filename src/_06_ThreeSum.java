import com.sun.istack.internal.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 */
public class _06_ThreeSum {
    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-2,0,0,2,2}));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<Integer> numArr = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        for (int num : nums) {
            numArr.add(num);
        }
        for (int i = 0; i < numArr.size() - 2; i++) {
            int start = i + 1;
            int end = numArr.size() - 1;
            int iNum = numArr.get(i);
            if (i > 0 && iNum == numArr.get(i - 1)) {
                continue;
            }
            if (iNum > 0) {
                break;
            }
            while (start < end) {
                int startNum = numArr.get(start);
                int endNum = numArr.get(end);
                if (startNum + endNum + iNum == 0) {
                    List<Integer> a = new ArrayList<>();
                    a.add(iNum);
                    a.add(startNum);
                    a.add(endNum);
                    ans.add(a);
                    start++;
                    end--;
                    while (start < end && numArr.get(start) == startNum) {
                        start++;
                    }
                    while (start < end && numArr.get(end) == endNum) {
                        end--;
                    }
                } else if (startNum + endNum + iNum < 0) {
                    start++;
                    while (start < end && numArr.get(start) == startNum) {
                        start++;
                    }
                } else if (startNum + endNum + iNum > 0) {
                    end--;
                    while (start < end && numArr.get(end) == endNum) {
                        end--;
                    }
                }
            }
        }
        return ans;
    }
}
