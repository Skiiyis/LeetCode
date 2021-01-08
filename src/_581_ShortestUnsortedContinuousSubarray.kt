/**
 * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。

你找到的子数组应是最短的，请输出它的长度。

示例 1:

输入: [2, 6, 4, 8, 10, 9, 15]
输出: 5
解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
说明 :

输入的数组长度范围在 [1, 10,000]。
输入的数组可能包含重复元素 ，所以升序的意思是<=。
 */
fun main(args: Array<String>) {
    println(
        findUnsortedSubarray(
            intArrayOf(
                2, 6, 4, 8, 10, 9, 15
            )
        )
    )
}

/**
 * 目标数组需满足[升序子数组1，乱序数组，升序子数组2]
 * 且乱序数组的最小值大于升序子数组1
 * 且乱序数组的最大值小于升序子数组2
 * 对原数组排序，头尾向中间遍历，和原数组不一样的下标即是结果
 */
fun findUnsortedSubarray(nums: IntArray): Int {
    val sortedArray = nums.copyOf().also { it.sort() }
    var head = 0
    while (head < nums.size) {
        if (sortedArray[head] != nums[head]) {
            break
        }
        head++
    }
    var tail = nums.size - 1
    while (tail > head) {
        if (sortedArray[tail] != nums[tail]) {
            break
        }
        tail--
    }
    println("head = $head,tail = $tail")
    return tail - head + 1
}