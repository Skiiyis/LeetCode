/**
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。

如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

必须原地修改，只允许使用额外常数空间。

以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
 */
fun main(args: Array<String>) {
    println(
        intArrayOf(
            4,2,0,2,3,2,0
        ).also(::nextPermutation)
            .toList()
    )
}

/**
 * 排列问题使用回溯法做，这个地方由于只允许原地修改，
 * 1243 -> 1324
 * 从3开始倒序遍历，先找到第一个比自己小的数字（2），交换位置，然后将后面的数字排序成正序
 *
 */
fun nextPermutation(nums: IntArray): Unit {
    if (nums.isEmpty() || nums.size == 1) return
    var f1 = -1
    var f2 = -1
    loop@ for (i in nums.size - 1 downTo 0) {
        for (j in i - 1 downTo 0) {
            if (nums[i] > nums[j]) {
                println("i,j = $i,$j")
                f1 = i
                f2 = j
                break@loop
            }
        }
    }
    if (f2 == -1) {
        nums.sort()
        return
    }
    val temp = nums[f1]
    nums[f1] = nums[f2]
    nums[f2] = temp
    nums.sort(f2 + 1)
}