import java.lang.StringBuilder

//给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
//
// 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下： 
//
// 
// "123" 
// "132" 
// "213" 
// "231" 
// "312" 
// "321" 
// 
//
// 给定 n 和 k，返回第 k 个排列。 
//
// 说明： 
//
// 
// 给定 n 的范围是 [1, 9]。 
// 给定 k 的范围是[1, n!]。 
// 
//
// 示例 1: 
//
// 输入: n = 3, k = 3
//输出: "213"
// 
//
// 示例 2: 
//
// 输入: n = 4, k = 9
//输出: "2314"
// 
// Related Topics 数学 回溯算法 
// 👍 297 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
fun getPermutation(n: Int, k: Int): String {
    val sb = StringBuilder()
    var total = 1
    val chooseNumbers = ArrayList<Int>()
    for (i in 1..n) {
        total *= i
        chooseNumbers.add(i)
    }
    var number = k - 1
    while (chooseNumbers.isNotEmpty()) {
        total /= chooseNumbers.size
        val numberString = chooseNumbers.removeAt(number / total).toString()
        number %= total
        sb.append(numberString)
    }
    return sb.toString()
}

/*fun main(args: Array<String>) {
    println(
        Solution().getPermutation(3, 3)
    )
}*/
//leetcode submit region end(Prohibit modification and deletion)
