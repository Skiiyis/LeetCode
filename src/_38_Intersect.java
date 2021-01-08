import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 两个数组的交集 II
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 * 示例 2:
 * <p>
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [4,9]
 * 说明：
 * <p>
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 * 我们可以不考虑输出结果的顺序。
 * 进阶:
 * <p>
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 */
public class _38_Intersect {
    public static void main(String[] args) {
        for (int i : intersect(new int[]{1, 2, 2, 1}, new int[]{2, 2})) {
            System.out.print(i + " ");
        }
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> m1 = new HashMap<>();
        HashMap<Integer, Integer> m2 = new HashMap<>();
        for (int i : nums1) {
            Integer times = m1.get(i);
            if (times == null) {
                times = 1;
            } else {
                times++;
            }
            m1.put(i, times);
        }

        for (int i : nums2) {
            Integer times = m2.get(i);
            if (times == null) {
                times = 1;
            } else {
                times++;
            }
            m2.put(i, times);
        }

        ArrayList<Integer> arr = new ArrayList<>();
        for (Integer key : m1.keySet()) {
            if (m2.containsKey(key)) {
                int times = Math.min(m1.get(key), m2.get(key));
                for (int i = 0; i < times; i++) {
                    arr.add(key);
                }
            }
        }
        int[] res = new int[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            res[i] = arr.get(i);
        }
        return res;
    }
}
