import kotlin.math.max

/**
 * 三角形最小路径和
给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。

例如，给定三角形：

[
[2],
[3,4],
[6,5,7],
[4,1,8,3]
]
自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。

说明：

如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分
 */
fun main(args: Array<String>) {
    println(
        minimumTotal(
            listOf(
                listOf(2),
                listOf(3, 4),
                listOf(6, 5, 7),
                listOf(4, 1, 8, 3)
            )
        )
    )
}

//f(i,j) = min(f(i-1,j),f(i-1,j-1))
fun minimumTotal(triangle: List<List<Int>>): Int {
    val dp = IntArray(triangle.size)
    for (i in 0 until triangle.size) {
        val currentList = triangle[i]
        for (j in 0 until currentList.size) {
            val index = currentList.size - j - 1
            when (index) {
                0 -> dp[index] += currentList[index]
                i -> dp[index] = dp[index - 1] + currentList[index]
                else -> dp[index] = Math.min(dp[index - 1], dp[index]) + currentList[index]
            }
            print("${dp[index]},")
        }
        println()
    }
    return dp.min() ?: 0
}