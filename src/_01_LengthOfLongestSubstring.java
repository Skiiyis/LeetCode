import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class _01_LengthOfLongestSubstring {
    public static void main(String[] args) {
        lengthOfLongestSubstring("  ");
    }

    /**
     * 暴力解法，非常的慢O(n3)
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        int length = s.length();
        int maxSubStringLength = 0;
        String maxSubString = "";
        if (s.length() != 0) {
            for (int start = 0; start < length; start++) {
                for (int end = start + 1; end < length + 1; end++) {
                    String subStr = s.substring(start, end);
                    char[] subChars = subStr.toCharArray();
                    HashSet<Character> set = new HashSet<>();
                    for (char subChar : subChars) {
                        set.add(subChar);
                    }
                    if (set.size() == subStr.length() && subStr.length() > maxSubStringLength) {
                        maxSubStringLength = subStr.length();
                        maxSubString = subStr;
                    }
                }
            }
        }
        System.out.println("lengthOfLongestSubstring(" + s + "):" + maxSubString + "，len: " + maxSubStringLength);
        return maxSubStringLength;
    }

    /**
     * 滑动窗口法
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring2(String s) {
        int ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        //[start,end)
        for (int start = 0, end = 0; end < s.length(); end++) {
            if (map.containsKey(s.charAt(end))) {
                start = Math.max(start, map.get(s.charAt(end)));
            }
            ans = Math.max(ans, end - start + 1);
            map.put(s.charAt(end), end + 1);
        }
        return ans;
    }
}
