/**
 * 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。

示例:

输入:
[
["1","0","1","0","0"],
["1","0","1","1","1"],
["1","1","1","1","1"],
["1","0","0","1","0"]
]
输出: 6
 */
fun main(args: Array<String>) {
    println(
        maximalRectangle(
            arrayOf(
                charArrayOf(
                    '1', '0', '1', '0', '0'
                ),
                charArrayOf(
                    '1', '0', '1', '1', '1'
                ),
                charArrayOf(
                    '1', '1', '1', '1', '1'
                ),
                charArrayOf(
                    '1', '0', '0', '1', '0'
                )
            )
        )
    )
}

/**
 * 动规做法，每一行可以看作是柱状图，然后转换成求柱状图的最大矩形
 *
 */
fun maximalRectangle(matrix: Array<CharArray>): Int {
    val dp = IntArray(matrix[0].size) { 0 }
    var ans = 0
    matrix.forEachIndexed { i, chars ->
        chars.forEachIndexed { j, c ->
            dp[j] = if (c == '1') dp[j] + 1 else 0
        }
        ans = Math.max(ans, largestRectangleArea(dp))
    }
    return ans
}