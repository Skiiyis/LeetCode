/**
 * Nim 游戏
你和你的朋友，两个人一起玩 Nim 游戏：桌子上有一堆石头，每次你们轮流拿掉 1 - 3 块石头。 拿掉最后一块石头的人就是获胜者。你作为先手。

你们是聪明人，每一步都是最优解。 编写一个函数，来判断你是否可以在给定石头数量的情况下赢得游戏。

示例:

输入: 4
输出: false
解释: 如果堆中有 4 块石头，那么你永远不会赢得比赛；
因为无论你拿走 1 块、2 块 还是 3 块石头，最后一块石头总是会被你的朋友拿走。
 */
fun main(args: Array<String>) {
    println(
        canWinNim(100)
    )
}

/**
 * 动态规划问题
 * dp[i] = !(dp[i-1] && dp[i-2] && dp[i-3])
 */
fun canWinNim(n: Int): Boolean {
    val dp = BooleanArray(n)
    if (n < 4) return true
    dp[0] = true
    dp[1] = true
    dp[2] = true
    for (i in 3 until n) {
        dp[i] = !(dp[i - 1] && dp[i - 2] && dp[i - 3])
        println("dp($i) = ${dp[i]}")
    }
    return dp[n - 1]
}