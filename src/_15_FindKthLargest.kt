/**
 * 数组中的第K个最大元素
在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

示例 1:

输入: [3,2,1,5,6,4] 和 k = 2
输出: 5
示例 2:

输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
输出: 4
说明:

你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。


 */
fun main(args: Array<String>) {
    val arr = intArrayOf(3,2,3,1,2,4,5,5,6)
    println(
        findKthLargest(
            arr,
            4,
            0,
            arr.size
        )
    )
}

/**
 * 快速选择
 * 选定一个数字，将比该数字大的放在左边，比该数字小的放右边
 * 寻找第k大的数字在哪边再递归处理
 */
fun findKthLargest(nums: IntArray, k: Int, i: Int = 0, originArrayLength: Int = nums.size): Int {
    val center = nums[0]
    val left = ArrayList<Int>()
    val right = ArrayList<Int>()
    for (i in 1 until nums.size) {
        val num = nums[i]
        if (num < center) {
            left.add(num)
        } else {
            right.add(num)
        }
    }
    //[0,originArrayLength-1]
    //[i,nums.size + i]
    val centerIndex = nums.size + i - right.size - 1
    val kIndex = originArrayLength - k
    return when {
        centerIndex < kIndex -> findKthLargest(right.toIntArray(), k, centerIndex + 1, originArrayLength)
        centerIndex > kIndex -> findKthLargest(left.toIntArray(), k, centerIndex - left.size, originArrayLength)
        else -> center
    }
}