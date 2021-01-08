import java.util.*

object BinarySearch {

    fun search(nums: IntArray, target: Int, start: Int = 0, end: Int = nums.size - 1): Int {
        if (start > end) return -start - 1
        val mid = (start + end) / 2
        return when {
            nums[mid] == target -> mid
            nums[mid] > target -> search(nums, target, start, mid - 1)
            else -> search(nums, target, mid + 1, end)
        }
    }
}

fun main(args: Array<String>) {
    println(
        BinarySearch.search(
            intArrayOf(-1, 0, 3, 5, 9, 12),
            12
        )
    )
}