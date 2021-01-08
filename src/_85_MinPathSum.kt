/**
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

说明：每次只能向下或者向右移动一步。

示例:

输入:
[
  [1,3,1],
[1,5,1],
[4,2,1]
]
输出: 7
解释: 因为路径 1→3→1→1→1 的总和最小。
 */
fun main(args: Array<String>) {
    println(
        minPathSum(
            arrayOf(
                intArrayOf(
                    1, 3, 1
                ),
                intArrayOf(
                    1, 5, 1
                ),
                intArrayOf(
                    4, 2, 1
                )
            )
        )
    )
}

//动态规划 dp[i][j] = min(dp[i-1][j],dp[i][j-1])+s[i][j]
fun minPathSum(grid: Array<IntArray>): Int {
    val dp = Array(grid.size) { IntArray(grid[0].size) }
    for (i in 0 until dp.size) {
        for (j in 0 until dp[0].size) {
            val t1 = dp.getOrNull(i - 1)?.getOrNull(j)
            val t2 = dp.getOrNull(i)?.getOrNull(j - 1)
            dp[i][j] = when {
                t1 != null && t2 != null -> Math.min(t1, t2) + grid[i][j]
                t1 == null && t2 != null -> t2 + grid[i][j]
                t1 != null && t2 == null -> t1 + grid[i][j]
                else -> grid[i][j]
            }
        }
    }
    return dp.last().last()
}