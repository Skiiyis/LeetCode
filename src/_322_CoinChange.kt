import java.util.*

/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。



示例 1:

输入: coins = [1, 2, 5], amount = 11
输出: 3
解释: 11 = 5 + 5 + 1
示例 2:

输入: coins = [2], amount = 3
输出: -1


说明:
你可以认为每种硬币的数量是无限的。
 */
fun main(args: Array<String>) {
    println(
        coinChange(
            intArrayOf(456, 117, 5, 145), 1459
        )
    )
}

/**
 * 动态规划 dp[i] = min(dp[i-c1],dp[i-c2],dp[i-c3]....)+1
 */
fun coinChange(coins: IntArray, amount: Int): Int {
    coins.sort()
    if (amount == 0) return 0
    if (amount < coins[0]) return -1
    val dp = Array(amount + 1) { -1 }
    val invalidCoins = coins.filter { it <= amount }
    for (i in 0..amount) {
        dp[i] = if (invalidCoins.contains(i)) {
            1
        } else {
            (invalidCoins
                .map { dp.getOrElse(i - it) { -1 } }
                .filter { it != -1 }
                .minBy { it } ?: -2) + 1
        }
    }
    return dp[amount]
}