//n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
//
// 
//
// 上图为 8 皇后问题的一种解法。 
//
// 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。 
//
// 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。 
//
// 示例: 
//
// 输入: 4
//输出: [
// [".Q..",  // 解法 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // 解法 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
//解释: 4 皇后问题存在两个不同的解法。
// 
//
// 
//
// 提示： 
//
// 
// 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一到七步
//，可进可退。（引用自 百度百科 - 皇后 ） 
// 
// Related Topics 回溯算法 
// 👍 503 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
fun solveNQueens(
    n: Int,
    y: Int = 0,
    temple: String = StringBuilder().let { sb ->
        repeat(n) {
            sb.append(".")
        }
        sb.toString()
    },
    ans: MutableList<String> = mutableListOf(),
    totalAns: MutableList<MutableList<String>> = mutableListOf()
): List<List<String>> {
    for (x in 0 until n) {
        if (!canSetQueen(x, y, ans)) continue
        val path = StringBuilder(temple).let {
            it[x] = 'Q'
            it.toString()
        }
        ans.add(path)
        if (ans.size == n) {
            //获取到一个答案
            totalAns.add(ArrayList(ans))
        } else {
            //处理下一层
            solveNQueens(n, y + 1, temple, ans, totalAns)
            if (ans.size == n) {
                //获取到一个答案
                totalAns.add(ArrayList(ans))
            }
        }
        ans.removeAt(ans.size - 1)
    }
    return totalAns
}

fun canSetQueen(x: Int, y: Int, ans: List<String>): Boolean {
    for (yy in y - 1 downTo 0) {
        //竖直上方向没有Queen
        if (ans.getOrNull(yy)?.getOrNull(x) == 'Q') {
            return false
        }
        //左斜上方向没有Queen
        if (ans.getOrNull(yy)?.getOrNull(x - y + yy) == 'Q') {
            return false
        }
        //右斜上方向没有Queen
        if (ans.getOrNull(yy)?.getOrNull(x + y - yy) == 'Q') {
            return false
        }
    }
    return true
}

/*fun main(args: Array<String>) {
    println(Solution().solveNQueens(8))
}*/
//leetcode submit region end(Prohibit modification and deletion)
