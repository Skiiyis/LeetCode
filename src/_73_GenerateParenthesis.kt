/**
 * 括号生成
数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。



示例：

输入：n = 3
输出：[
"((()))",
"(()())",
"(())()",
"()(())",
"()()()"
]
 */
fun main(args: Array<String>) {
    println(
        generateParenthesis(4)
    )
    println(
        generateParenthesis2(4)
    )
}

/**
 * 当作树来遍历做, 每一个节点有两种或者一种可能的子叶"("或者")"
 */
fun generateParenthesis2(nLeft: Int, nRight: Int = 0): List<String> {
    val arr = ArrayList<String>()
    if (nLeft > 0) {
        arr.addAll(generateParenthesis2(nLeft - 1, nRight + 1).map { "($it" })
    }
    if (nRight > 0) {
        arr.addAll(generateParenthesis2(nLeft, nRight - 1).map { ")$it" })
    }
    if (nLeft == 0 && nRight == 0) {
        arr.add("")
    }
    return arr
}

/**
 * 动态规划
 * dp[n] = (dp[1]+dp[n-2])+...+(dp[n-2]dp[1])
 */
fun generateParenthesis(n: Int): List<String> {
    val dp = Array<ArrayList<String>>(n + 1) { ArrayList() }
    dp[0].add("")
    for (i in 0..n) {
        for (j in 0 until i) {
            val dp1 = dp[j]
            val dp2 = dp[i - j - 1]
            dp1.forEach { dp1S ->
                dp2.forEach { dp2S ->
                    dp[i].add("($dp1S)$dp2S")
                }
            }
        }
    }
    return dp[n]
}

