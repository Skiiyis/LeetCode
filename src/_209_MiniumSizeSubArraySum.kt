/**
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。

 

示例：

输入：s = 7, nums = [2,3,1,2,4,3]
输出：2
解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 

进阶：

如果你已经完成了 O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 */
fun main(args: Array<String>) {
    println(
        minSubArrayLen(
            7,
            intArrayOf(
                2, 3, 1, 2, 4, 3
            )
        )
    )
}

/**
 * 滑动窗口法
 */
fun minSubArrayLen(s: Int, nums: IntArray): Int {
    var ans: Int? = null
    var start = -1
    var sum = 0
    for (end in 0 until nums.size) {
        sum += nums[end]
        while (sum >= s) {
            ans = Math.min(ans ?: Int.MAX_VALUE, end - start)
            start++
            sum -= nums[start]
        }
    }
    return ans ?: 0
}