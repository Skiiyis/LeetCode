/**
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​

设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:

你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
示例:

输入: [1,2,3,0,2]
输出: 3
解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 */
fun main(args: Array<String>) {
    println(
        _309_maxProfit(
            intArrayOf(
                1, 2, 3, 0, 2
            )
        )
    )
}

/**
 * 动态规划，每天有两种状态，持有股票 or 不持有股票
 * dp[1][i] = 第i天持有股票 = Max(第i-1天持有股票，第i-2天卖出股票-今天买入股票）
 * dp[0][i] = 第i天没有股票 = Max(第i-1天持有股票+今天卖出股票，第i-1天没有股票）
 * 边界条件
 * dp[1][0] = 第0天持有股票 = 0
 * dp[0][0] = 第0天没有股票 = 0
 * dp[1][1] = 第1天持有股票 = -num[1]
 * dp[0][1] = 第1天没有股票 = 0
 */
fun _309_maxProfit(prices: IntArray): Int {
    if (prices.isEmpty()) return 0
    val dp = Array(2) { IntArray(prices.size + 1) }
    dp[1][0] = 0
    dp[0][0] = 0
    dp[1][1] = -prices[0]
    dp[0][1] = 0
    for (i in 2 until prices.size + 1) {
        dp[1][i] = Math.max(dp[1][i - 1], dp[0][i - 2] - prices[i - 1])
        dp[0][i] = Math.max(dp[0][i - 1], dp[1][i - 1] + prices[i - 1])
    }
    return dp[0].last()
}
