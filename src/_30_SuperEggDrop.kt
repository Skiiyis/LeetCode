/**
 * 你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。

每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。

你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。

每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。

你的目标是确切地知道 F 的值是多少。

无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？



示例 1：

输入：K = 1, N = 2
输出：2
解释：
鸡蛋从 1 楼掉落。如果它碎了，我们肯定知道 F = 0 。
否则，鸡蛋从 2 楼掉落。如果它碎了，我们肯定知道 F = 1 。
如果它没碎，那么我们肯定知道 F = 2 。
因此，在最坏的情况下我们需要移动 2 次以确定 F 是多少。
示例 2：

输入：K = 2, N = 6
输出：3
示例 3：

输入：K = 3, N = 14
输出：4


提示：

1 <= K <= 100
1 <= N <= 10000
 */
fun main(args: Array<String>) {
    println(
        superEggDrop(2, 6)
    )
}

/**
 * 动态规划求解
 * dp[i][j] 代表有i个鸡蛋执行j次最高可以测试的楼层数，假设为N
 *
 * 可以了解到，如果一个鸡蛋丢在第k层碎了
 * 那么还剩下i-1个鸡蛋和j-1次测试机会可以求到k-1层中的具体楼层
 * 如果鸡蛋丢在k层没碎
 * 那么还剩下i个鸡蛋和j-1次机会可以求到N-k中的具体楼层
 * dp[i][j] = dp[i-1][j-1] + dp[i][j-1] + 1
 * 边界条件
 * 当i = 0 时 dp[i][j] = 0
 * 当i = 1 时 dp[i][j] = j
 * 当j = 0 时 dp[i][j] = 0
 * 当j = 1 时 dp[i][j] = 1
 * 可以认为j <= N,因为最多也只要操作N次即可以得到，
 * 或者使用记忆化的方式先从 dp[i][0] 递推执行到 dp[i][N]
 */
fun superEggDrop(K: Int, N: Int): Int {
    val dp = Array(K + 1) { IntArray(N + 1) }
    for (i in 0..K) {
        for (j in 0..N) {
            if (i == 0) {
                dp[i][j] = 0
                continue
            }
            if (i == 1) {
                dp[i][j] = j
                continue
            }
            if (j == 0) {
                dp[i][j] = 0
                continue
            }
            if (j == 1) {
                dp[i][j] = 1
                continue
            }
            dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1] + 1
        }
    }
    return dp[K].indexOf(dp[K].find { it >= N } ?: 0)
}