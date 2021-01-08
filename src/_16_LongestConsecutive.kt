import kotlin.math.max

/**
 * 给定一个未排序的整数数组，找出最长连续序列的长度。

要求算法的时间复杂度为 O(n)。

示例:

输入: [100, 4, 200, 1, 3, 2]
输出: 4
解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 */
fun main(args: Array<String>) {
    println(
        longestConsecutive(
            intArrayOf(
                100, 4, 200, 1, 3, 2
            )
        )
    )
}

//f(n) = f(n-1) + f(n+1) +1
fun longestConsecutive(nums: IntArray): Int {
    val map = HashMap<Int, IntArray>()
    var maxLength = 0
    for (num in nums) {
        if (map.containsKey(num)) {
            continue
        }
        var left = num
        var right = num
        if (map.containsKey(num - 1)) {
            left = map[num - 1]!![0]
        }
        if (map.containsKey(num + 1)) {
            right = map[num + 1]!![1]
        }
        val length = right - left + 1
        maxLength = max(length, maxLength)
        for (i in left..right) {
            map[i] = intArrayOf(left, right)
        }
    }
    return maxLength
}