/**
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。

返回可以使最终数组和为目标数 S 的所有添加符号的方法数。

示例 1:

输入: nums: [1, 1, 1, 1, 1], S: 3
输出: 5
解释:

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

一共有5种方法让最终目标和为3。
注意:

数组非空，且长度不会超过20。
初始的数组的和不会超过1000。
保证返回的最终结果能被32位整数存下。
 */
fun main(args: Array<String>) {
    println(
        findTargetSumWays2(
            intArrayOf(
                0,0,0,0,0,0,0,0,1
            ), 1
        )
    )
}

/**
 * 动规求解，dp[i][j]数组的每个元素代表 第[i]个数字求和后和为[j]的方案数
 * dp[i][j] = dp[i-1][j-nums[i]] + dp[i-1][j+nums[i]]
 */
fun findTargetSumWays2(nums: IntArray, S: Int): Int {
    val total = nums.reduce { acc, i -> acc + i }
    val dp = Array(nums.size) { IntArray(2 * total + 1) { 0 } }
    dp[0][total + nums[0]] += 1
    dp[0][total - nums[0]] += 1
    for (i in 1 until nums.size) {
        for (j in 0..total) {
            println("i:$i,j:$j")
            dp[i][total + j] +=
                    dp[i - 1].getOrElse(total + j - nums[i]) { 0 } +
                    dp[i - 1].getOrElse(total + j + nums[i]) { 0 }
            dp[i][total - j] =
                    dp[i - 1].getOrElse(total - j - nums[i]) { 0 } +
                    dp[i - 1].getOrElse(total - j + nums[i]) { 0 }
        }
    }
    return dp[nums.size - 1].getOrElse(total + S) { 0 }
}

/**
 * 遍历求解
 */
fun findTargetSumWays(nums: IntArray, S: Int, depth: Int = 1, prevSum: Int = 0): Int {
    return if (depth == nums.size) {
        val num = nums[depth - 1]
        var findTarget = 0
        if (num + prevSum == S) findTarget++
        if (-num + prevSum == S) findTarget++
        findTarget
    } else {
        findTargetSumWays(
            nums, S, depth + 1, prevSum + nums[depth - 1]
        ) + findTargetSumWays(
            nums, S, depth + 1, prevSum - nums[depth - 1]
        )
    }
}