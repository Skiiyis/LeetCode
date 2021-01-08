import kotlin.math.max

/**
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。

示例 1:

输入: 2
输出: 1
解释: 2 = 1 + 1, 1 × 1 = 1。
示例 2:

输入: 10
输出: 36
解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
说明: 你可以假设 n 不小于 2 且不大于 58。
 */
fun main(args: Array<String>) {
    println(integerBreak(
        10
    ))
}

/**
 * 动态规划
 * f(n) = max(1 * max(n-1,f(n-1)), ..., (n-1) * max(1,f(1)))
 *
 * basecase
 * f(0) = 0
 * f(1) = 1
 * f(2) = 1
 * f(3) = 2
 */
fun integerBreak(n: Int): Int {
    val ans = IntArray(n + 1)
    ans[0] = 0
    ans[1] = 1
    for (i in 2 until ans.size) {
        var max = 0
        for (m in 1 until i) {
            max = max(max, m * max(i - m, ans[i - m]))
        }
        ans[i] = max
    }
    return ans[n]
}

