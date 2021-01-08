/**
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。

示例:

输入:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

输出: 4
 */
fun main(args: Array<String>) {
    println(
        maximalSquare(
            arrayOf(
                /*charArrayOf('0', '0', '0', '1'),
                charArrayOf('1', '1', '0', '1'),
                charArrayOf('1', '1', '1', '1'),
                charArrayOf('0', '1', '1', '1'),
                charArrayOf('0', '1', '1', '1')*/
                charArrayOf('1')
            )
        )
    )
}

//f(i,j) = min(f(i-1,j-1),f(i-1,j),f(i,j-1)+1
fun maximalSquare(matrix: Array<CharArray>): Int {
    val height = matrix.size
    if (height == 0) {
        return 0
    }
    val width = matrix[0].size
    val dp = Array(height) { IntArray(width) }
    var maxSquareSize = 0
    for (i in 0 until height) {
        for (j in 0 until width) {
            if (matrix[i][j] != '1') {
                dp[i][j] = 0
                continue
            }
            if (i == 0 || j == 0) {
                dp[i][j] = if (matrix[i][j] != '1') {
                    0
                } else {
                    1
                }
            } else {
                dp[i][j] = minOf(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1
            }
            maxSquareSize = Math.max(maxSquareSize, dp[i][j])
        }
    }
    return maxSquareSize * maxSquareSize
}