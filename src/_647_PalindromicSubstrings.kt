/**
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。

具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。

示例 1:

输入: "abc"
输出: 3
解释: 三个回文子串: "a", "b", "c".
示例 2:

输入: "aaa"
输出: 6
说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
注意:

输入的字符串长度不会超过1000。
 */
fun main(args: Array<String>) {
    println(
        countSubstrings(
            "abc"
        )
    )
}

/**
 * 动态规划
 * dp[i][j] 代表s[i,j]的子串是否为回文串
 * dp[i][j] = dp[i+1][j-1] && s[i] == s[j]
 * basecase
 * dp[i][i] = true
 * dp[i][i-n] = true
 */
fun countSubstrings(s: String): Int {
    val dp = Array(s.length) { BooleanArray(s.length) { false } }
    var ans = 0
    for (i in s.length - 1 downTo 0) {
        for (j in 0 until s.length) {
            if (j - i < 1) {
                dp[i][j] = true
            } else {
                dp[i][j] = (dp.getOrNull(i + 1)?.getOrNull(j - 1) ?: true) && (s[i] == s[j])
            }
            if (j >= i && dp[i][j]) {
                ans++
            }
        }
    }
    return ans
}