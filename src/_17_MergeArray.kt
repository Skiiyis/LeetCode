import java.util.*

/**
 *给出一个区间的集合，请合并所有重叠的区间。

示例 1:

输入: [[1,3],[2,6],[8,10],[15,18]]
输出: [[1,6],[8,10],[15,18]]
解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
示例 2:

输入: [[1,4],[4,5]]
输出: [[1,5]]
解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间
 */
fun main(args: Array<String>) {
    val arrs = merge(
        arrayOf(
            intArrayOf(1, 3),
            intArrayOf(2, 6),
            intArrayOf(8, 10),
            intArrayOf(15, 18)
        )
    )
    println(
        arrs
    )
}

/**
 * [1,3] [4,6] [2,5]
 */
fun merge(intervals: Array<IntArray?>): Array<IntArray?> {
    loop@ for (i in 0 until intervals.size - 1) {
        val first = intervals[i] ?: continue@loop
        for (j in i + 1 until intervals.size) {
            val second = intervals[j] ?: continue
            if (first[1] >= second[0] && first[1] <= second[1]) {
                second[0] = Math.min(first[0], second[0])
                second[1] = Math.max(first[1], second[1])
                intervals[i] = null
                continue@loop
            } else if (second[1] >= first[0] && second[1] <= first[1]) {
                second[0] = Math.min(first[0], second[0])
                second[1] = Math.max(first[1], second[1])
                intervals[i] = null
                continue@loop
            }
        }
    }
    return intervals.filter { return@filter it != null }.toTypedArray()
}

