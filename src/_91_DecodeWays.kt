/**
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：

'A' -> 1
'B' -> 2
...
'Z' -> 26
给定一个只包含数字的非空字符串，请计算解码方法的总数。

示例 1:

输入: "12"
输出: 2
解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
示例 2:

输入: "226"
输出: 3
解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 */
fun main(args: Array<String>) {
    println(
        numDecodings("101")//(2,2,2,2)(22,2,2)(2,22,2)(2,2,22)(22,22)
        //(2,2,6,2)(22,6,2)(2,26,2)
    )
}

/**
 * 动态规划来做
 * dp[i] 可以由 dp[i-1] 或 dp[i-2] 转移过来
 * dp[i] = dp[i-1] + dp[i-2]
 */
fun numDecodings(s: String): Int {
    val dp = IntArray(s.length + 1)
    dp[0] = 1
    dp[1] = if (s[0] - '0' in 1..9) 1 else 0
    for (i in 2 until dp.size) {
        val code = s.substring(i - 2 until i).toInt()
        //00 无法转换成字母
        if (code == 0) return 0
        //10 or 20 只能转化成 10 or 20 对应的字符，不能转换成 1,0 这样的
        else if (code == 10 || code == 20) dp[i] = dp[i - 2]
        // 10~26 可以正常转换成 一个or两个字符
        else if (code in 10..26) dp[i] = dp[i - 1] + dp[i - 2]
        // >26只能转换成两个字符
        else if (code > 26) dp[i] = dp[i - 1]
    }
    return dp.last()
}