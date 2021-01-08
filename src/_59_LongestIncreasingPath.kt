/**
 *给定一个整数矩阵，找出最长递增路径的长度。

对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。

示例 1:

输入: nums =
[
[9,9,4],
[6,6,8],
[2,1,1]
]
输出: 4
解释: 最长递增路径为 [1, 2, 6, 9]。
示例 2:

输入: nums =
[
[3,4,5],
[3,2,6],
[2,2,1]
]
输出: 4
解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
 */
fun main(args: Array<String>) {

}

/**
 * 先找到所有的起点，即一个点小于它上下左右的点所有的点，记录起点为1，
 * 再根据起点找到新一轮遍历的起点，等于上一轮起点的值+1
 */
fun longestIncreasingPath(matrix: Array<IntArray>): Int {
    val maxPathArray = Array(matrix.size) { Array<Int?>(matrix[0].size) { null } }
    var maxPath = 1
    for (j in 0 until matrix.size) {
        for (i in 0 until matrix[0].size) {
            val v = matrix[i][j]
            val vLeft = matrix.getOrNull(i - 1)?.getOrNull(j) ?: Int.MAX_VALUE
            val vTop = matrix.getOrNull(i)?.getOrNull(j - 1) ?: Int.MAX_VALUE
            val vRight = matrix.getOrNull(i + 1)?.getOrNull(j) ?: Int.MAX_VALUE
            val vBottom = matrix.getOrNull(i - 1)?.getOrNull(j + 1) ?: Int.MAX_VALUE
            if (v <= vLeft && v <= vTop && v <= vRight && v <= vBottom) {
                maxPathArray[i][j] = maxPath
            }
        }
    }
    return 0
}

fun findPathNext(matrix: Array<IntArray>, maxPathArray: Array<IntArray>, path: Int): Boolean {
    var needFindNextPath = false
    for (j in 0 until matrix.size) {
        for (i in 0 until matrix[0].size) {
            val v = matrix[i][j]
            if (v != path) {
                continue
            }
            val vLeft = matrix.getOrNull(i - 1)?.getOrNull(j) ?: Int.MAX_VALUE
            val vTop = matrix.getOrNull(i)?.getOrNull(j - 1) ?: Int.MAX_VALUE
            val vRight = matrix.getOrNull(i + 1)?.getOrNull(j) ?: Int.MAX_VALUE
            val vBottom = matrix.getOrNull(i - 1)?.getOrNull(j + 1) ?: Int.MAX_VALUE
            if (v <= vLeft && v <= vTop && v <= vRight && v <= vBottom) {
                maxPathArray[i][j] = path + 1
                needFindNextPath
            }
        }
    }
    return needFindNextPath
}