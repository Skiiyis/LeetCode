/**
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。

示例 1:

输入: n = 12
输出: 3
解释: 12 = 4 + 4 + 4.
示例 2:

输入: n = 13
输出: 2
解释: 13 = 4 + 9.

 */
fun main(args: Array<String>) {
    println(numSquares(48))
}

fun numSquares(n: Int): Int {
    val dp = IntArray(n + 1) { Int.MAX_VALUE }
    for (i in 1..n) {
        var j = 1
        if (i == j * j) {
            dp[i] = 1
        }
        while (j * j < i) {
            dp[i] = kotlin.math.min(dp[i], dp[i - j * j] + 1)
            j++
        }
    }
    return dp[n]
}

/**
 * 任意一个数都可以变成 一个平方数+一个其他数 的形式，其他数又可以变成 一个平方数+一个其他数 记录最短路径即可。
 *
 */
fun numSquares(n: Int, dp: HashMap<Int, Int> = HashMap()): Int {
    if (dp.containsKey(n)) return dp[n]!!
    var i = 1
    val ansArray = ArrayList<Int>()
    while (true) {
        if (i * i > n) {
            break
        }
        if (n == i * i) {
            dp[n] = 1
            return 1
        }
        val ans = numSquares(n - i * i, dp) + 1
        ansArray.add(ans)
        i++
    }
    dp[n] = ansArray.min()!!
    return ansArray.min()!!
}