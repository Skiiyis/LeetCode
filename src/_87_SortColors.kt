/**
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。

注意:
不能使用代码库中的排序函数来解决这道题。

示例:

输入: [2,0,2,1,1,0]
输出: [0,0,1,1,2,2]
进阶：

一个直观的解决方案是使用计数排序的两趟扫描算法。
首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
你能想出一个仅使用常数空间的一趟扫描算法吗？
 */
fun main(args: Array<String>) {
    println(
        intArrayOf(
            2,0,2,1,1,0
        ).also {
            sortColors(it)
        }.toList()
    )
}

/**
 * 双指针法
 * 头尾各放一个指针,两个指针都准备向中间移动
 * 从左向右遍历，当遍历为2时和右指针数字交换，移动右指针
 * 遍历为0时和左指针交换，移动左指针
 * 遍历到右指针时结束遍历
 */
fun sortColors(nums: IntArray): Unit {
    var p1 = 0
    var p2 = nums.lastIndex
    var i = 0
    while (true) {
        if (i > p2) return
        if (nums[i] == 2) {
            nums[i] = nums[p2]
            nums[p2] = 2
            p2--
            i--
        } else if (nums[i] == 0) {
            if (i != p1) {
                nums[i] = nums[p1]
                nums[p1] = 0
                p1++
                i--
            }
        }
        i++
    }
}