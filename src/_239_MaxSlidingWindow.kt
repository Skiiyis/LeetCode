import kotlin.math.max

/**
 *给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

返回滑动窗口中的最大值。



示例:

输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
输出: [3,3,5,5,6,7]
解释:

滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
1 [3  -1  -3] 5  3  6  7       3
1  3 [-1  -3  5] 3  6  7       5
1  3  -1 [-3  5  3] 6  7       5
1  3  -1  -3 [5  3  6] 7       6
1  3  -1  -3  5 [3  6  7]      7


提示：

你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。



进阶：

你能在线性时间复杂度内解决此题吗？
 */
fun main(args: Array<String>) {
    maxSlidingWindow(intArrayOf(1, 3, -1, -3, 5, 3, 6, 7), 3)
        .forEach {
            print("$it ")
        }
}

fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
    if (k == 0) return IntArray(nums.size).also { it.fill(0) }
    if (k == 1) return nums
    val left = IntArray(nums.size)
    val right = IntArray(nums.size)
    for (i in 0 until nums.size) {
        if (i % k == 0) {
            left[i] = nums[i]
        } else {
            left[i] = Math.max(left[i - 1], nums[i])
        }
    }
    for (i in nums.size - 1 downTo 0) {
        if (i % k == 0) {
            right[i] = nums[i]
        } else {
            right[i] = Math.max(right.getOrElse(i + 1) { Int.MIN_VALUE }, nums[i])
        }
    }
    //[1,2,3,4]
    val res = IntArray(nums.size - k + 1)
    for (i in 0 until res.size) {
        res[i] = Math.max(right[i], left[i + k - 1])
    }
    return res
}