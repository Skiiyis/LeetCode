/**
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。

岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。

此外，你可以假设该网格的四条边均被水包围。



示例 1:

输入:
11110
11010
11000
00000
输出: 1
示例 2:

输入:
11000
11000
00100
00011
输出: 3
解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
 */
fun main(args: Array<String>) {
    println(
        numIslands(
            arrayOf(
                charArrayOf(
                    '1', '1', '1', '1', '0'
                ),
                charArrayOf(
                    '1', '1', '0', '1', '0'
                ),
                charArrayOf(
                    '1', '1', '0', '0', '0'
                ),
                charArrayOf(
                    '0', '0', '0', '0', '0'
                )
            )
        )
    )
}

fun numIslands(grid: Array<CharArray>): Int {
    var numIsLands = 0
    for (i in 0 until grid.size) {
        for (j in 0 until grid[0].size) {
            if (grid[i][j] == '1') {
                numIsLands++
                visitLandAreas(grid, i, j)
            }
        }
    }
    return numIsLands
}

fun visitLandAreas(grid: Array<CharArray>, i: Int, j: Int) {
    grid[i][j] = '0'
    if (grid.getOrNull(i - 1)?.getOrNull(j) == '1') {
        visitLandAreas(grid, i - 1, j)
    }
    if (grid.getOrNull(i + 1)?.getOrNull(j) == '1') {
        visitLandAreas(grid, i + 1, j)
    }
    if (grid.getOrNull(i)?.getOrNull(j - 1) == '1') {
        visitLandAreas(grid, i, j - 1)
    }
    if (grid.getOrNull(i)?.getOrNull(j + 1) == '1') {
        visitLandAreas(grid, i, j + 1)
    }
}