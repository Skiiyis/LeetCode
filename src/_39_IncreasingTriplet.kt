import kotlin.math.max

/**
 * 给定一个未排序的数组，判断这个数组中是否存在长度为 3 的递增子序列。

数学表达式如下:

如果存在这样的 i, j, k,  且满足 0 ≤ i < j < k ≤ n-1，
使得 mArr[i] < mArr[j] < mArr[k] ，返回 true ; 否则返回 false 。
说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1) 。

示例 1:
输入: [1,2,6,4]  dp[] = [1,2,3]

输出: true
示例 2:

输入: [5,4,3,2,1]
输出: false
 */
fun main(args: Array<String>) {
    println(increasingTriplet(intArrayOf(2, 1, 5, 0, 4, 6)))
}

//动态规划,等同于求最大上升子序列
fun increasingTriplet(nums: IntArray): Boolean {
    val dp = ArrayList<Int>(2)
    for (num in nums) {
        var insertIndex = 0
        for (i in 0 until dp.size) {
            if (num > dp[i]) {
                insertIndex++
            }
        }
        if (insertIndex >= dp.size) {
            dp.add(num)
            if (insertIndex >= 2) {
                return true
            }
        } else {
            dp[insertIndex] = num
        }
    }
    return false
}

//dp[i] = max(dp[i-1]+1,dp[i-2]+1....) && num[i-1]<num[i]
//输入: [1,2,6,4]  dp[] = [1,2,3]
fun increasingTriplet2(nums: IntArray): Boolean {
    val dp = ArrayList<Int>(nums.size)
    if (nums.isEmpty() || nums.size < 3) return false
    dp[0] = 1
    for (i in 1 until nums.size) {
        var length = 1
        for (j in 0 until i) {
            if (nums[i] > nums[j]) {
                length = max(length, dp[j] + 1)
            }
        }
        dp[i] = length
        if (length >= 3) {
            return true
        }
    }
    return false
}