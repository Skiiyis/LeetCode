//给出整数数组 A，将该数组分隔为长度最多为 K 的几个（连续）子数组。分隔完成后，每个子数组的中的值都会变为该子数组中的最大值。 
//
// 返回给定数组完成分隔后的最大和。 
//
// 
//
// 示例： 
//
// 输入：A = [1,15,7,9,2,5,10], K = 3
//输出：84
//解释：A 变为 [15,15,15,9,10,10,10] 
//
// 
//
// 提示： 
//
// 
// 1 <= K <= A.length <= 500 
// 0 <= A[i] <= 10^6 
// 
// Related Topics 图 
// 👍 63 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
//dp[i] 即第i位上分割完后可以得到的最大和
fun maxSumAfterPartitioning(A: IntArray, K: Int): Int {
    val dp = IntArray(A.size)
    for (i in 0 until dp.size) {
        var max: Int? = null
        var j = i
        while (j >= 0 && i - j < K) {
            max = Math.max(A[j], max ?: Int.MIN_VALUE)
            dp[i] = Math.max(dp[i], dp.getOrElse(j - 1) { 0 } + max * (i - j + 1))
            j--
        }
    }
    return dp.last()
}

/*fun main(args: Array<String>) {
    println(
        Solution().maxSumAfterPartitioning(
            intArrayOf(1, 15, 7, 9, 2, 5, 10),
            3
        )
    )
}*/
//leetcode submit region end(Prohibit modification and deletion)
