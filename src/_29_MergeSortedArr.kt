/**
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。

说明:

初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
示例:

输入:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

输出: [1,2,2,3,5,6]

nums1 = [1,2,3,4,5,6,0,0,0], m = 5
nums2 = [2,5,6],       n = 3

 */
fun main(args: Array<String>) {
    println(
        merge(
            intArrayOf(1, 2, 3, 0, 0, 0),
            3,
            intArrayOf(2, 5, 6),
            3
        )
    )
}

fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int) {
    var i1 = m - 1
    var i2 = n - 1
    for (i in nums1.size - 1 downTo 0) {
        if (nums1.getOrElse(i1) { Int.MIN_VALUE } > nums2.getOrElse(i2) { Int.MIN_VALUE }) {
            nums1[i] = nums1.getOrElse(i1) { 0 }
            i1--
        } else {
            nums1[i] = nums2.getOrElse(i2) { 0 }
            i2--
        }
    }
}