import java.util.HashMap;

/**
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * <p>
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 * <p>
 * 示例1:
 * <p>
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 * <p>
 * <p>
 * 示例2:
 * <p>
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 * <p>
 * <p>
 * 注意：
 * <p>
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 */
public class _03_CheckInclusion {
    public static void main(String[] args) {
        System.out.println(checkInclusion("adc", "dcda"));
    }

    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        HashMap<Character, Integer> s1Chars = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            Integer length = s1Chars.get(s1.charAt(i));
            s1Chars.put(s1.charAt(i), (length == null ? 0 : length) + 1);
        }
        HashMap<Character, Integer> s1c = (HashMap<Character, Integer>) s1Chars.clone();
        for (int i = 0; i < s2.length(); i++) {
            Character c = s2.charAt(i);
            if (!s1c.containsKey(c)) {
                s1c = (HashMap<Character, Integer>) s1Chars.clone();
                continue;
            } else {
                int cLength = s1c.get(c);
                cLength--;
                if (cLength == 0) {
                    s1c.remove(c);
                    if (s1c.isEmpty()) {
                        return true;
                    }
                } else {
                    s1c.put(c, cLength);
                }
            }
        }
        return false;
    }

}
