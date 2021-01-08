/**
给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。

请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。

你可以假设 nums1 和 nums2 不会同时为空。

示例 1:

nums1 = [1, 3]
nums2 = [2]

则中位数是 2.0
示例 2:

nums1 = [1, 2, 3, 3]
nums2 = [2, 4, 4, 5]  //1，2，2，3，3，4，4，5

则中位数是 (2 + 3)/2 = 2.5
 */
fun main(args: Array<String>) {
    println(
        findMedianSortedArrays(
            intArrayOf(1, 5, 6),
            intArrayOf(3, 5, 7, 8, 9)
        )
    )
}

fun findMedianSortedArrays2(nums1: IntArray, nums2: IntArray): Double {
    return 0.0
}

fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
    val res = IntArray(nums1.size + nums2.size)
    var index1 = 0
    var index2 = 0
    if (res.size % 2 == 0) {
        index1 = res.size / 2
        index2 = index1 - 1
    } else {
        index1 = res.size / 2
        index2 = index1
    }
    var i1 = 0
    var i2 = 0
    for (i in 0 until res.size) {
        if (nums1.size > i1 && nums2.size > i2) {
            if (nums1[i1] < nums2[i2]) {
                res[i] = nums1[i1]
                i1++
            } else {
                res[i] = nums2[i2]
                i2++
            }
        } else if (nums1.size > i1) {
            res[i] = nums1[i1]
            i1++
        } else if (nums2.size > i2) {
            res[i] = nums2[i2]
            i2++
        }
    }
    return (res[index1] + res[index2]) / 2.0
}