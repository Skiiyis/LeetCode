import java.lang.Integer.max

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。

示例 1:

输入: [1,2,3,1]
输出: 4
解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
偷窃到的最高金额 = 1 + 3 = 4 。
示例 2:

输入: [2,7,9,3,1]
输出: 12
解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 */
fun main(args: Array<String>) {
    println(
        rob2(
            intArrayOf(
                1, 2, 3, 1
            )
        )
    )
}

/**
 * 动态规划问题，先找出状态转移方程
 * F(x) = max(F(x-1)+0,F(x-2)+f(x)，F(x-3)+f(x))
 */
fun rob(nums: IntArray): Int {
    if (nums.isEmpty()) {
        return 0
    }
    for (i in 0 until nums.size) {
        if (i == 0) {
            continue
        }
        if (i == 1) {
            nums[i] = max(nums[i], nums[i - 1])
            continue
        }
        if (i == 2) {
            nums[i] = max(nums[i - 1], nums[i - 2] + nums[i])
            continue
        }
        nums[i] = max(max(nums[i - 1], nums[i - 2] + nums[i]), nums[i - 3] + nums[i])
    }
    return nums[nums.size - 1]
}

/**
 * F(x) = max(F(x-1),F(x-2)+f(x))
 */
fun rob2(nums: IntArray): Int {
    if (nums.isEmpty()) {
        return 0
    }
    var prevMax = 0 // F(x-2)
    var currentMax = 0 // F(x-1)
    for (i in 0 until nums.size) {
        val temp = currentMax
        currentMax = max(prevMax + nums[i], currentMax)
        prevMax = temp
    }
    return currentMax
}