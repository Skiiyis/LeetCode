/**
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。


示例 1：

输入：nums = [4,1,4,6]
输出：[1,6] 或 [6,1]
示例 2：

输入：nums = [1,2,10,4,1,4,3,3]
输出：[2,10] 或 [10,2]
 

限制：

2 <= nums.length <= 10000
 */
fun main(args: Array<String>) {
    println(
        singleNumbers(
            intArrayOf(
                1, 2, 10, 4, 1, 4, 3, 3
            )
        ).toList()
    )
    //10,1010
}

fun singleNumbers(nums: IntArray): IntArray {
    val xorRes = nums.reduce { acc, i -> acc xor i }
    var first1 = 1
    while (xorRes and first1 != first1) {
        first1 = first1 shl 1
    }
    val num1 = nums.filter {
        it and first1 == first1
    }.reduce { acc, i -> acc xor i }
    val nums2 = nums.filter {
        it and first1 != first1
    }.reduce { acc, i -> acc xor i }
    return intArrayOf(num1, nums2)
}