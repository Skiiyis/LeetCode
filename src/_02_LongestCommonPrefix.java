import java.util.HashSet;
import java.util.Set;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * <p>
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 * <p>
 * 所有输入只包含小写字母 a-z
 */
public class _02_LongestCommonPrefix {
    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"aaa", "aab"}));
    }

    /**
     * 正序遍历扫描，倒序遍历扫描会更快
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 1) {
            return strs[0];
        }
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() == 0) {
                return "";
            }
        }
        String maxCommonPrefix = "";
        int end = 0;
        Set<String> prefixs = new HashSet<>();
        while (true) {
            prefixs.clear();
            for (int i = 0; i < strs.length; i++) {
                String str = strs[i];
                //1<1
                if (str.length() < end) {
                    return maxCommonPrefix;
                }
                prefixs.add(str.substring(0, end));
            }
            if (prefixs.size() == 1) {
                maxCommonPrefix = prefixs.iterator().next();
                end++;
            } else {
                return maxCommonPrefix;
            }
        }
    }
}
