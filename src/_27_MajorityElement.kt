/**
 * 给定一个大小为 n 的数组，找到其中的多数元素。
 * 多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。

你可以假设数组是非空的，并且给定的数组总是存在多数元素。

示例 1:

输入: [3,2,3]
输出: 3
示例 2:

输入: [2,2,1,1,1,2,2]
输出: 2
 */
fun main(args: Array<String>) {
    println(
        majorityElement2(
            intArrayOf(
                2,2,1,1,1,2,2
            )
        )
    )
}

//计算最大数字出现的次数即可
fun majorityElement(nums: IntArray): Int {
    val map = HashMap<Int, Int>()
    for (num in nums) {
        val times = (map[num] ?: 0) + 1
        if (times > nums.size / 2) {
            return num
        } else {
            map[num] = times
        }
    }
    return 0
}

fun majorityElement2(nums: IntArray): Int {
    var maxNum: Int? = null
    var maxNumCount: Int = 0
    for (num in nums) {
        if (maxNum == null) {
            maxNum = num
            maxNumCount = 1
            continue
        }
        if (maxNum == num) {
            maxNumCount++
        } else {
            maxNumCount--
            if (maxNumCount == 0) {
                maxNum = null
            }
        }
    }
    return maxNum!!
}