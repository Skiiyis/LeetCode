/**
 * 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。

现在要求你戳破所有的气球。每当你戳破一个气球 i 时，你可以获得 nums[left] * nums[i] * nums[right] 个硬币。 这里的 left 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。

求所能获得硬币的最大数量。

说明:

你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
示例:

输入: [3,1,5,8]
输出: 167
解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
     coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */
fun main(args: Array<String>) {
    println(
        maxCoins2(
            intArrayOf(
                8,2,6,8,9,8,1,4,1,5,3,0,7,7,0,4,2,2,5,5
            )
        )
    )
}

fun maxCoins(nums: IntArray): Int {
    return _312_maxCoins(nums.toMutableList())
}

/**
 * 动态规划
 * dp[i][j] 表示从戳破(i,j)的气球🎈可以得到的最大硬币
 * dp[i][j] = max((dp[i+1][k-1]+dp[k+1][j-1]+f(i)*f(j)*f(k))...) i<k<j
 * 边界条件
 * dp[i][j] = 0  j-i<1
 * dp[i-1][i+1] = f(i-1)*f(i+1)*f(i)
 *
 *
 */
fun maxCoins2(nums: IntArray): Int {
    val numsArray = nums.toMutableList()
    numsArray.add(0, 1)
    numsArray.add(numsArray.size, 1)
    val dp = Array(numsArray.size) { IntArray(numsArray.size) }
    for (i in numsArray.size - 1 downTo 0) {
        for (j in 0 until numsArray.size) {
            for (k in i + 1 until j) {
                dp[i][j] = Math.max(
                    dp[i][j],
                    dp[i][k] + dp[k][j] + numsArray[i] * numsArray[j] * numsArray[k]
                )
            }
        }
    }
    return dp.first().last()
}

/**
 * 回溯算法..+剪枝，不减枝会超时
 */
fun _312_maxCoins(
    nums: MutableList<Int>,
    memo: HashMap<String, Int> = HashMap()
): Int {
    if (nums.isEmpty()) return 0
    val memoKey = nums.toString()
    if (memo.containsKey(memoKey)) return memo[memoKey]!!
    val numsList = nums.toMutableList()
    var maxCoins = 0
    for (i in 0 until numsList.size) {
        val left = numsList.getOrElse(i - 1) { 1 }
        val right = numsList.getOrElse(i + 1) { 1 }
        val center = numsList.removeAt(i)
        maxCoins = Math.max(maxCoins, left * right * center + _312_maxCoins(numsList, memo))
        numsList.add(i, center)
    }
    memo[memoKey] = maxCoins
    println(memo)
    return maxCoins
}