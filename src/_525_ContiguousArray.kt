/**
 * 给定一个二进制数组, 找到含有相同数量的 0 和 1 的最长连续子数组（的长度）。

示例 1:

输入: [0,1]
输出: 2
说明: [0, 1] 是具有相同数量0和1的最长连续子数组。
示例 2:

输入: [0,1,0]
输出: 2
说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。

 */
fun main(args: Array<String>) {
    println(
        findMaxLength(
            intArrayOf(
                0, 1, 0
            )
        )
    )
}

/**
 * 前缀和，寻找首次出现的前缀和和最后一次出现的前缀和两者的下标差即是结果
 */
fun findMaxLength(nums: IntArray): Int {
    val map = hashMapOf<Int, Int>()
    var sum = 0
    var ans = 0
    map[0] = -1
    nums.forEachIndexed { index, i ->
        if (i == 1) {
            sum++
        } else {
            sum--
        }
        if (map.containsKey(sum)) {
            ans = Math.max(ans, (index - (map[sum] ?: Int.MAX_VALUE)))
        } else {
            map[sum] = index
        }
    }
    return ans
}