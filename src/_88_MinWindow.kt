/**
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。

示例：

输入: S = "ADOBECODEBANC", T = "ABC"
输出: "BANC"
说明：

如果 S 中不存这样的子串，则返回空字符串 ""。
如果 S 中存在这样的子串，我们保证它是唯一的答案。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimum-window-substring
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
fun main(args: Array<String>) {
    println(minWindow("cabwefgewcwaefgcf", "cae"))
}

/**
 * 维护一个滑动窗口，窗口每进入一个目标值，就查看窗口队列的第一个值是不是可以出队列
 * 一直到遍历完毕
 */
fun minWindow(s: String, t: String): String {
    val charMap = HashMap<Char, Int>()
    t.forEach {
        charMap[it] = charMap.getOrDefault(it, 0) + 1
    }
    var ans = ""
    val window = ArrayList<Int>()
    for (p2 in 0 until s.length) {
        val c = s[p2]
        if (charMap.containsKey(c)) {
            window.add(p2)
            charMap[c] = charMap.getOrDefault(c, 0) - 1
            //新的可以选字符入队之后
            //若已经满足目标值的要求，可以考虑从左开始缩小滑动窗口的范围，看是否依然满足要求
            while (charMap.values.none { it > 0 }) {
                val anAns = s.substring(window.first()..window.last())
                if (anAns.length <= ans.length || ans.isEmpty()) {
                    ans = anAns
                }
                val windowLeft = s[window.first()]
                if (charMap.getOrDefault(windowLeft, 0) < 0) {
                    window.removeAt(0)
                    charMap[windowLeft] = charMap.getOrDefault(windowLeft, 0) + 1
                } else {
                    break
                }
            }
        }
    }
    return ans
}