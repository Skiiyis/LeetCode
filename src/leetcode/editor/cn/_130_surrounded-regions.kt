//给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。 
//
// 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。 
//
// 示例: 
//
// X X X X
//X O O X
//X X O X
//X O X X
// 
//
// 运行你的函数后，矩阵变为： 
//
// X X X X
//X X X X
//X X X X
//X O X X
// 
//
// 解释: 
//
// 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被
//填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。 
// Related Topics 深度优先搜索 广度优先搜索 并查集 
// 👍 355 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * 多源树的遍历
 * 第一遍先将所有不会被填充的O标记为X，第二步求异或
 */
fun solve(board: Array<CharArray>): Unit {
    board.forEachIndexed { y, chars ->
        chars.forEachIndexed { x, c ->
            if ((x == 0 || y == 0 || x == board[0].size - 1 || y == board.size - 1) && c == 'O') {
                visit(x, y, board)
            }
        }
    }
    board.forEachIndexed { y, chars ->
        chars.forEachIndexed { x, c ->
            if (board[y][x] == 'S') {
                board[y][x] = 'O'
            } else {
                board[y][x] = 'X'
            }
        }
    }
}

private fun visit(x: Int, y: Int, board: Array<CharArray>) {
    if (board.getOrNull(y)?.getOrNull(x) == 'O') {
        board[y][x] = 'S'
        visit(x, y + 1, board)
        visit(x, y - 1, board)
        visit(x + 1, y, board)
        visit(x - 1, y, board)
    }
}

/*fun main(args: Array<String>) {
    val board = arrayOf(
        charArrayOf(
            'X', 'X', 'X', 'X'
        ), charArrayOf(
            'X', 'O', 'O', 'X'
        ), charArrayOf(
            'X', 'X', 'O', 'X'
        ), charArrayOf(
            'X', 'O', 'X', 'X'
        )
    )
    Solution().solve(board)
    println(board.map { it.toList() })
}*/
//leetcode submit region end(Prohibit modification and deletion)
