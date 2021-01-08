import java.lang.StringBuilder
import kotlin.math.max

/**
 * 最长回文子串
给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

示例 1：

输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。
示例 2：

输入: "cbbd"
输出: "bb"
 */
fun main(args: Array<String>) {
    println(
        longestPalindrome2(
            "zttzsknks"
        )
    )
}

/**
 * 动态规划求解, 下标[i,j]的子串是否是回文串取决于下标[i+1,j-1]的子串是否为回文串且s[i]==s[j]
 * 状态转移方程S(i,j) = S(i+1,j-1) && (s[i] == s[j])
 * "aaaa"
 * S(0,0) = a = true
 * S(0,3) = S(1,2) && (s[0] == s[3])
 */
fun longestPalindrome(s: String): String {
    if (s.isEmpty()) return ""
    val dp = Array(s.length) { IntArray(s.length) { 0 } }
    val longestPalindromeIndex = IntArray(2)
    var maxLength = 0
    for (i in s.length - 1 downTo 0) {
        for (j in 0 until s.length) {
            if (i >= j) {
                dp[i][j] = 1
            } else {
                val dpPrev = dp[i + 1][j - 1]
                if (dpPrev > 0 && (s[i] == s[j])) {
                    dp[i][j] = j - i + 1
                }
            }
            println("[$i,$j] = ${dp[i][j]} ${s.substring(if (i >= j) j else i, j + 1)}")
            maxLength = max(maxLength, dp[i][j])
            if (maxLength == dp[i][j]) {
                longestPalindromeIndex[0] = i
                longestPalindromeIndex[1] = j
            }
        }
    }
    println("max[i,j] = [${longestPalindromeIndex[0]},${longestPalindromeIndex[1]}]")
    if (longestPalindromeIndex[0] >= longestPalindromeIndex[1]) {
        longestPalindromeIndex[0] = longestPalindromeIndex[1]
    }
    return s.substring(longestPalindromeIndex[0], longestPalindromeIndex[1] + 1)
}

//S(i,j) = S(i+1,j-1) && (s[i] == s[j])
fun longestPalindrome2(s: String): String {
    var res = ""
    val dp = BooleanArray(s.length)
    for (i in s.length - 1 downTo 0) {
        for (j in s.length - 1 downTo 0) {
            if (i >= j) {
                dp[j] = true
                if (res.isEmpty()) {
                    res = s.substring(j, j + 1)
                }
            } else {
                if (dp[j - 1] && (s[i] == s[j])) {
                    dp[j] = true
                    if (j - i + 1 > res.length) {
                        res = s.substring(i, j + 1)
                    }
                } else {
                    dp[j] = false
                }
            }
            println("[$i,$j] = ${dp[j]} ${s.substring(if (i >= j) j else i, j + 1)}")
        }
    }
    return res
}