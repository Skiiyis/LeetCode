
/**
 * 最长连续递增序列
给定一个未经排序的整数数组，找到最长且连续的的递增序列。

示例 1:

输入: [1,3,5,4,7]
输出: 3
解释: 最长连续递增序列是 [1,3,5], 长度为3。
尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为5和7在原数组里被4隔开。
示例 2:

输入: [2,2,2,2,2]
输出: 1
解释: 最长连续递增序列是 [2], 长度为1。
注意：数组长度不会超过10000。
 */
fun main(args: Array<String>) {
    println(findLengthOfLCIS(intArrayOf(1,3,5,7)))
}

fun findLengthOfLCIS(nums: IntArray): Int {
    var maxLength = 0
    var cLength = 0
    for (index in 0 until nums.size) {
        if (index == 0) {
            maxLength = 1
            cLength = 1
            continue
        }
        //单调增
        if (nums[index] > nums[index - 1]) {
            cLength++
            maxLength = Math.max(maxLength, cLength)
        } else {
            cLength = 1
        }
    }
    return maxLength
}