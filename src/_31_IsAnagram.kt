/**
 * 有效的字母异位词
给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。

示例 1:

输入: s = "anagram", t = "nagaram"
输出: true
示例 2:

输入: s = "rat", t = "car"
输出: false
说明:
你可以假设字符串只包含小写字母。

进阶:
如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 */
fun main(args: Array<String>) {
    println(
        isAnagram(
            "rat",
            "car"
        )
    )
}

fun isAnagram(s: String, t: String): Boolean {
    if (s.length != t.length) return false
    val cs = HashMap<Char, Int>()
    for (c in s.toCharArray()) {
        cs[c] = (cs[c] ?: 0) + 1
    }
    for (c in t.toCharArray()) {
        val times = cs[c]
        if (times == null || times == 0) {
            return false
        } else {
            cs[c] = times - 1
        }
    }
    return true
}