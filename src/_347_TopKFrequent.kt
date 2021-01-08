import java.util.*
import kotlin.collections.ArrayList

/**
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。

示例 1:

输入: nums = [1,1,1,2,2,3], k = 2
输出: [1,2]
示例 2:

输入: nums = [1], k = 1
输出: [1]
说明：

你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 */
fun main(args: Array<String>) {
    println(
        topKFrequent2(
            intArrayOf(
                -1, -1
            ), 1
        )
    )
}

fun topKFrequent2(nums: IntArray, k: Int): List<Int> {
    val map = HashMap<Int, Int>()
    for (num in nums) {
        map[num] = map.getOrDefault(num, 0) + 1
    }
    val res = PriorityQueue<Int>(k) { o1, o2 -> return@PriorityQueue map[o1]!! - map[o2]!! }
    for (kv in map) {
        res.add(kv.key)
        if (res.size > k) {
            res.poll()
        }
    }
    return ArrayList<Int>().also { arr ->
        res.forEach {
            arr.add(it)
        }
    }
}

fun topKFrequent(nums: IntArray, k: Int): List<Int> {
    val map = HashMap<Int, Int>()
    for (num in nums) {
        map[num] = map.getOrDefault(num, 0) + 1
    }
    val sortedMap = TreeMap<Int, ArrayList<Int>>()
    map.forEach { kv ->
        sortedMap[kv.value] = (sortedMap[kv.value] ?: ArrayList()).also {
            it.add(kv.key)
        }
    }
    return ArrayList<Int>().also { res ->
        sortedMap.forEach {
            res.addAll(it.value)
        }
    }.reversed().take(k)
}