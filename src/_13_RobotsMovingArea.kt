/**
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？

 

示例 1：

输入：m = 2, n = 3, k = 1
输出：3
示例 2：

输入：m = 3, n = 1, k = 0
输出：1
提示：

1 <= n,m <= 100
0 <= k <= 20
 */
fun main(args: Array<String>) {
    println(
        movingCount(
            3, 1, 0
        )
    )
}

/**
 * 多源BFS层序遍历，从0，0开始
 */
fun movingCount(m: Int, n: Int, k: Int): Int {
    val level = mutableListOf<Pair<Int, Int>>()
    var ans = 1
    level.add(0 to 0)
    val isValidCoordinate: (Int, Int, Int) -> Boolean = { i, j, k ->
        (i / 100 + i / 10 + i % 10) + (j / 100 + j / 10 + j % 10) <= k
    }
    while (level.isNotEmpty()) {
        val levelSet = hashMapOf<String, Pair<Int, Int>>()
        level.flatMap { pair ->
            arrayListOf<Pair<Int, Int>>().also {
                //筛选一下不超过边界的点
                if (pair.first + 1 < m) {
                    it.add(pair.first + 1 to pair.second)
                }
                if (pair.second + 1 < n) {
                    it.add(pair.first to pair.second + 1)
                }
            }
        }.forEach {
            //判断有效性 & 去重
            if (isValidCoordinate(it.first, it.second, k)) {
                levelSet[it.toString()] = it
            }
        }
        levelSet.values.also {
            ans += it.size
            level.clear()
            level.addAll(it)
        }
    }
    return ans
}

