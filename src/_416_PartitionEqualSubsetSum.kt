import java.util.*

/**
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

注意:

每个数组中的元素不会超过 100
数组的大小不会超过 200
示例 1:

输入: [1, 5, 11, 5]

输出: true

解释: 数组可以分割成 [1, 5, 5] 和 [11].
 

示例 2:

输入: [1, 2, 3, 5]

输出: false

解释: 数组不能分割成两个元素和相等的子集.
 */
fun main(args: Array<String>) {
    println(
        canPartition2(
            intArrayOf(
                1, 5, 11, 5
            )
        )
    )
}

/**
 * 01背包问题，先得到背包的大小Sum/2
 * dp[i] 代表前i个数字放入到背包空间为j的情况下的元素总和的情况
 * 状态转移方程
 * dp[i] = dp[i-1] each +- nums[i]
 * 当dp[i] 中恰好存在背包大小时条件成立
 */
fun canPartition(nums: IntArray): Boolean {
    if (nums.size == 1) return false
    val sum = nums.reduce { acc, a -> acc + a }
    if (sum % 2 == 1) return false
    val s = sum / 2
    val dp = Array<Set<Int>>(nums.size) { setOf() }
    dp[0] = mutableSetOf(0, nums[0])
    for (i in 1 until dp.size) {
        dp[i] = dp[i - 1].flatMap { mutableListOf(it, it + nums[i]) }.toSet()
        if (dp[i].contains(s)) {
            return true
        }
    }
    return false
}

/**
 * 01背包问题，先得到背包的大小Sum/2
 * dp[i][j] 代表前i个数字中是否可以组成和为j的情况
 * 状态转移方程
 * dp[i][j] = dp[i-1][j-nums[i]] || dp[i-1][j]
 * basecase
 * dp[i][j] = true if nums[i] == j
 */
fun canPartition2(nums: IntArray): Boolean {
    if (nums.size == 1) return false
    val sum = nums.reduce { acc, a -> acc + a }
    if (sum % 2 == 1) return false
    val s = sum / 2
    val dp = Array(nums.size) { BooleanArray(s + 1) { false } }
    for (i in 0 until nums.size) {
        for (j in 0 until s + 1) {
            if (nums[i] == j) {
                dp[i][j] = true
            } else {
                dp[i][j] = (dp.getOrNull(i-1)?.getOrNull(j)?:false) ||
                        (dp.getOrNull(i - 1)?.getOrNull(j - nums[i]) ?: false)
            }
        }
    }
    return dp.last().last()
}