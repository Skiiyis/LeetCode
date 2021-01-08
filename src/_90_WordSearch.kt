/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

 

示例:

board =
[
['A','B','C','E'],
['S','F','C','S'],
['A','D','E','E']
]

给定 word = "ABCCED", 返回 true
给定 word = "SEE", 返回 true
给定 word = "ABCB", 返回 false
 

提示：

board 和 word 中只包含大写和小写英文字母。
1 <= board.length <= 200
1 <= board[i].length <= 200
1 <= word.length <= 10^3
 */
fun main(args: Array<String>) {
    /*println(
        exist(
            arrayOf(
                charArrayOf(
                    'A', 'B', 'C', 'E'
                ),
                charArrayOf(
                    'S', 'F', 'C', 'S'
                ),
                charArrayOf(
                    'A', 'D', 'E', 'E'
                )
            ), "ABCB"
        )
    )*/

    println(
        exist(arrayOf(charArrayOf('a', 'a')), "aaa")
    )
}

/**
 * 深度优先搜索?
 */
fun exist(board: Array<CharArray>, word: String): Boolean {
    board.forEachIndexed { x, chars ->
        chars.forEachIndexed { y, c ->
            if (c == word.first()) {
                board[x][y] = '@'
                if (exist(board, word, x, y)) {
                    return true
                }
                board[x][y] = c
            }
        }
    }
    return false
}

/**
 * 深度优先搜索?
 */
fun exist(board: Array<CharArray>, word: String, prevX: Int, prevY: Int, index: Int = 1): Boolean {
    if (index >= word.length) return true
    val searchChar = word[index]
    arrayOf(
        prevX - 1 to prevY,
        prevX + 1 to prevY,
        prevX to prevY - 1,
        prevX to prevY + 1
    ).forEach {
        val t = board.getOrNull(it.first)?.getOrNull(it.second)
        if (t == searchChar) {
            board[it.first][it.second] = '@'
            if (exist(board, word, it.first, it.second, index + 1)) {
                return true
            }
            board[it.first][it.second] = t
        }
    }
    return false
}