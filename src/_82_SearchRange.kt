/**
 * 在排序数组中查找元素的第一个和最后一个位置
 *
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

你的算法时间复杂度必须是 O(log n) 级别。

如果数组中不存在目标值，返回 [-1, -1]。

示例 1:

输入: nums = [5,7,7,8,8,10], target = 8
输出: [3,4]
示例 2:

输入: nums = [5,7,7,8,8,10], target = 6
输出: [-1,-1]
 */
fun main(args: Array<String>) {
    println(
        searchRange(
            intArrayOf(
                5,7,7,8,8,10
            ), 6
        ).toList()
    )
}

/**
 * 二分查找
 */
fun searchRange(nums: IntArray, target: Int): IntArray {
    val ans = intArrayOf(-1, -1)
    _82_searchRange(nums, target, findStart = true, range = ans)
    _82_searchRange(nums, target, findEnd = true, range = ans)
    return ans
}

fun _82_searchRange(
    nums: IntArray,
    target: Int,
    start: Int = 0,
    end: Int = nums.size - 1,
    findStart: Boolean = false,
    findEnd: Boolean = false,
    range: IntArray
) {
    var low = start
    var high = end

    while (low <= high) {
        val mid = (low + high).ushr(1)
        val midVal = nums[mid]

        if (midVal < target)
            low = mid + 1
        else if (midVal > target)
            high = mid - 1
        else {
            if (findStart) {
                range[0] = mid
                if (low < mid) {
                    _82_searchRange(nums, target, low, mid - 1, findStart, findEnd, range)
                }
                return
            }
            if (findEnd) {
                range[1] = mid
                if (mid < high) {
                    _82_searchRange(nums, target, mid + 1, high, findStart, findEnd, range)
                }
                return
            }
        }
    }
}