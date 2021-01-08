import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.min

/**
 * 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。

请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。

说明:
不允许旋转信封。

示例:
 */
fun main(args: Array<String>) {
    println(
        maxEnvelopes(
            arrayOf(
                intArrayOf(4, 5),
                intArrayOf(4, 6),
                intArrayOf(6, 7),
                intArrayOf(2, 3),
                intArrayOf(1, 1)
            )
        )
    )
}

/**
 * 排序+求最大子升序
 */
fun maxEnvelopes(envelopes: Array<IntArray>): Int {
    val tr = TreeMap<Int, TreeSet<Int>>()
    for (envelope in envelopes) {
        val key = envelope[0]
        tr[key] = (tr[key] ?: TreeSet()).also { it.add(envelope[1]) }
    }
    val nums = ArrayList<Int>().also { list ->
        tr.forEach {
            it.value.reversed().forEach { v ->
                list.add(v)
            }
        }
    }.toTypedArray()
    if (nums.isEmpty()) return 0
    val lengthArray = IntArray(nums.size) { 1 }
    var maxLengthOfLIS = 1
    for (i in 1 until nums.size) {
        var lengthOfLIS = 0
        for (j in 0 until i) {  //可优化使用二分查找法
            if (nums[j] < nums[i]) {
                lengthOfLIS = Math.max(lengthOfLIS, lengthArray[j])
            }
        }
        lengthOfLIS++
        lengthArray[i] = lengthOfLIS
        maxLengthOfLIS = Math.max(maxLengthOfLIS, lengthOfLIS)
    }
    return maxLengthOfLIS
}

