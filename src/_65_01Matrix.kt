import kotlin.collections.ArrayList

/**
 * 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。

两个相邻元素间的距离为 1 。

示例 1:
输入:

0 0 0
0 1 0
0 0 0
输出:

0 0 0
0 1 0
0 0 0
示例 2:
输入:

0 0 0
0 1 0
1 1 1
输出:

0 0 0
0 1 0
1 2 1
注意:

给定矩阵的元素个数不超过 10000。
给定矩阵中至少有一个元素是 0。
矩阵中的元素只在四个方向上相邻: 上、下、左、右。
 */
fun main(args: Array<String>) {
    println(
        updateMatrix(
            arrayOf(
                intArrayOf(1, 0, 1, 1, 0, 0, 1, 0, 0, 1),
                intArrayOf(0, 1, 1, 0, 1, 0, 1, 0, 1, 1),
                intArrayOf(0, 0, 1, 0, 1, 0, 0, 1, 0, 0),
                intArrayOf(1, 0, 1, 0, 1, 1, 1, 1, 1, 1),
                intArrayOf(0, 1, 0, 1, 1, 0, 0, 0, 0, 1),
                intArrayOf(0, 0, 1, 0, 1, 1, 1, 0, 1, 0),
                intArrayOf(0, 1, 0, 1, 0, 1, 0, 0, 1, 1),
                intArrayOf(1, 0, 0, 0, 1, 1, 1, 1, 0, 1),
                intArrayOf(1, 1, 1, 1, 1, 1, 1, 0, 1, 0),
                intArrayOf(1, 1, 1, 1, 0, 1, 0, 0, 1, 1)
            )
        ).map { it.toList() }.toList()
    )
}

/**
 * BFS 广度优先搜索
 * 设置一个depth = 1
 * 有几种方式，将所有值为depth的对象入队，然后遍历符合上下左右有元素为depth-1的出队，其他值修改为depth
 * 修改depth++,再迭代下去直到所有对象出队
 */
fun updateMatrix(matrix: Array<IntArray>): Array<IntArray> {
    val temp = ArrayList<IntArray>()
    matrix.forEachIndexed { i, ints ->
        ints.forEachIndexed { j, v ->
            if (v == 1) {
                temp.add(intArrayOf(i, j))
            }
        }
    }
    var depth = 1
    var depthSize = temp.size
    while (!temp.isEmpty()) {
        val ele = temp.removeAt(0)
        val i = ele[0]
        val j = ele[1]
        val left = matrix.getOrNull(i)?.getOrNull(j - 1) ?: depth
        val right = matrix.getOrNull(i)?.getOrNull(j + 1) ?: depth
        val top = matrix.getOrNull(i - 1)?.getOrNull(j) ?: depth
        val bottom = matrix.getOrNull(i + 1)?.getOrNull(j) ?: depth
        if (left == depth && right == depth && top == depth && bottom == depth) {
            temp.add(intArrayOf(i, j))
        }
        depthSize--
        if (depthSize == 0) {
            depth++
            temp.forEach {
                matrix[it[0]][it[1]] = depth
            }
            depthSize = temp.size
        }
    }
    return matrix
}