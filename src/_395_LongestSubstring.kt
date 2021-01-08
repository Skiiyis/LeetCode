import kotlin.math.max

/**
 * 找到给定字符串（由小写字符组成）中的最长子串 T ， 要求 T 中的每一字符出现次数都不少于 k 。输出 T 的长度。

示例 1:

输入:
s = "aaabb", k = 3

输出:
3

最长子串为 "aaa" ，其中 'a' 重复了 3 次。
示例 2:

输入:
s = "ababbc", k = 2

输出:
5

最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
 */
fun main(args: Array<String>) {
    println(longestSubstring("bbaaacbd", 3))
}

/**
 * 分治，先找到不满足条件的字母，然后这些字母将字符串分开，子串再执行此方法，最后返回一个最大值
 */
fun longestSubstring(s: String, k: Int): Int {
    if (s.length < k) return 0
    val charMap = HashMap<Char, Int>()
    var maxLength = 0
    s.toCharArray().forEach {
        charMap[it] = charMap.getOrElse(it) { 0 } + 1
    }
    val splitMap = charMap.filter { it.value < k }
    if (splitMap.isEmpty()) return s.length
    s.split(*splitMap.keys.toCharArray()).forEach { part ->
        maxLength = max(maxLength, longestSubstring(part, k))
    }
    return maxLength
}