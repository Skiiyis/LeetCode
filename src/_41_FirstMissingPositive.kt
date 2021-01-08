/**
 * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。


示例 1:

输入: [1,2,0]
输出: 3
示例 2:

输入: [3,4,-1,1] -> [-1,4,3,1],[-1,1,3,4] [1,-1,3,4]
输出: 2
示例 3:

输入: [7,8,9,11,12]
输出: 1
 

提示：

你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。
 */
fun main(args: Array<String>) {
    println(
        firstMissingPositive(
            intArrayOf(
                1, 1
            )
        )
    )
}

/**
 * 位图法，遍历数组，在[0,size]区间内的数字给数组对应下标的位置置1，然后寻找第一个不是1的下标
 * 由于要使用常数级别额外空间，所以需要原地修改原数字。。
 * 把原数组视为hash表，index = num-1
 */
fun firstMissingPositive(nums: IntArray): Int {
    for (i in 0 until nums.size) {
        Loop@ while (true) {
            val n = nums[i]
            val index = n - 1
            if (index in 0 until nums.size && index != i && n != nums[index]) {
                nums[i] = nums[index]
                nums[index] = n
            } else {
                break@Loop
            }
        }
    }
    nums.forEachIndexed { index, i ->
        if (i != index + 1) {
            return index + 1
        }
    }
    return nums.size + 1
}

