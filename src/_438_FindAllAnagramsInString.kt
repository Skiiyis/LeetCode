/**
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。

字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。

说明：

字母异位词指字母相同，但排列不同的字符串。
不考虑答案输出的顺序。
示例 1:

输入:
s: "cbaebabacd" p: "abc"

输出:
[0, 6]

解释:
起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 示例 2:

输入:
s: "abab" p: "ab"

输出:
[0, 1, 2]

解释:
起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 */
fun main(args: Array<String>) {
    println(
        findAnagrams(
            "cbaebabacd",
            "abc"
        )
    )
}

/**
 * 字母异位词其实是全排列，如果直接求全排列再遍历比较复杂度将是 O(n!)
 * 维护一个p字符串的哈希表h，kv为字符和对应的字符个数
 * 维护一个长度为p.length的滑动窗口，窗口在s上滑动，
 * 当有字符进入窗口时减少h中对应的字符数量
 * 有字符离开窗口时增加h中对应的字符数量
 * 当h中的字符数量恰好都为0时满足条件为字母异位词
 */
fun findAnagrams(s: String, p: String): List<Int> {
    if (s.length < p.length) return emptyList()
    val h = HashMap<Char, Int>()
    p.toCharArray().forEach {
        h[it] = h.getOrElse(it) { 0 } + 1
    }
    for (tail in 0 until p.length) {
        h[s[tail]] = h.getOrElse(s[tail]) { 0 } - 1
    }
    val ans = mutableListOf<Int>()
    if (h.values.none { it != 0 }) {
        ans.add(0)
    }
    for (head in 1..s.length - p.length) {
        val tail = head + p.length - 1
        h[s[head - 1]] = h.getOrElse(s[head - 1]) { 0 } + 1
        h[s[tail]] = h.getOrElse(s[tail]) { 0 } - 1
        if (h.values.none { it != 0 }) {
            ans.add(head)
        }
    }
    return ans
}