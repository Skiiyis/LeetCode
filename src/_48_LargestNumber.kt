import java.lang.StringBuilder

/**
 * 最大数
给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。

示例 1:

输入: [10,2]
输出: 210
示例 2:

输入: [3,30,34,5,9]
输出: 9534330
 */
fun main(args: Array<String>) {
    println(
        largestNumber(
            intArrayOf(
                3,30,34,5,9
            )
        )
    )
}

//两个两个比较，组合更大的数更大
fun largestNumber(nums: IntArray): String {
    return ""
}