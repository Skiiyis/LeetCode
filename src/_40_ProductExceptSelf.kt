/**
 * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。



示例:

输入: [1,2,3,4]
输出: [24,12,8,6]


提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。

说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。

进阶：
你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 */
fun main(args: Array<String>) {
    productExceptSelf(
        intArrayOf(1,2,3,4)
    ).forEach {
        print("$it ")
    }
}

fun productExceptSelf(nums: IntArray): IntArray {
    val left = IntArray(nums.size)
    val right = IntArray(nums.size)
    left[0] = nums[0]
    right[nums.size - 1] = nums[nums.size - 1]
    for (i in 1 until nums.size) {
        left[i] = left[i - 1] * nums[i]
        val j = nums.size - 1 - i
        right[j] = nums[j] * right[j + 1]
    }
    val res = IntArray(nums.size)
    res[0] = right[1]
    res[nums.size - 1] = left[nums.size - 2]
    for (i in 1 until nums.size - 1) {
        res[i] = left[i - 1] * right[i + 1]
    }
    return res
}