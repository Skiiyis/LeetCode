import java.util.*

/**
 * 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。

示例:

输入: [5,2,6,1]
输出: [2,1,1,0]
解释:
5 的右侧有 2 个更小的元素 (2 和 1).
2 的右侧仅有 1 个更小的元素 (1).
6 的右侧有 1 个更小的元素 (1).
1 的右侧有 0 个更小的元素.
 */
fun main(args: Array<String>) {
    println(
        countSmaller(
            intArrayOf()
        )
    )
}

//维护一个有序队列，插入新的数时获取到数字前面的数
fun countSmaller(nums: IntArray): List<Int> {
    val arr = ArrayList<Int>(nums.size)
    val res = ArrayList<Int>(nums.size)
    for (i in nums.size - 1 downTo 0) {
        val searchResult = arr.binarySearch(nums[i])
        var insertIndex = (-arr.binarySearch(nums[i])) - 1
        if (searchResult >= 0) {
            insertIndex = searchResult
            //找到第一个相同的数的index
            while (arr.getOrNull(insertIndex) == nums[i]) {
                insertIndex--
            }
            insertIndex++
        }
        arr.add(insertIndex, nums[i])
        println(arr)
        res.add(0, insertIndex)
    }
    return res
}