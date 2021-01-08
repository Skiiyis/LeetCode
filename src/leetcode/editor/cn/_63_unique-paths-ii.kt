//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。 
//
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。 
//
// 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？ 
//
// 
//
// 网格中的障碍物和空位置分别用 1 和 0 来表示。 
//
// 说明：m 和 n 的值均不超过 100。 
//
// 示例 1: 
//
// 输入:
//[
//  [0,0,0],
//  [0,1,0],
//  [0,0,0]
//]
//输出: 2
//解释:
//3x3 网格的正中间有一个障碍物。
//从左上角到右下角一共有 2 条不同的路径：
//1. 向右 -> 向右 -> 向下 -> 向下
//2. 向下 -> 向下 -> 向右 -> 向右
// 
// Related Topics 数组 动态规划 
// 👍 393 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {
    val dp = IntArray(obstacleGrid[0].size)
    dp[0] = 1
    obstacleGrid.forEach { arr ->
        arr.forEachIndexed { y, i ->
            if (i == 1) {
                dp[y] = 0
            } else {
                val left = dp.getOrElse(y - 1) { 0 }
                val top = dp.getOrElse(y) { 0 }
                dp[y] = left + top
            }
        }
    }
    return dp.last()
}

/*fun main(args: Array<String>) {
    println(
        Solution().uniquePathsWithObstacles(
            arrayOf(
                intArrayOf(
                    0, 0, 0
                ), intArrayOf(
                    0, 1, 0
                ), intArrayOf(
                    0, 0, 0
                )
            )
        )
    )
}*/
//leetcode submit region end(Prohibit modification and deletion)
