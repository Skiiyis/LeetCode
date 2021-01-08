import java.util.*
import kotlin.collections.ArrayList

/**
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。

示例:

输入: [10,9,2,5,3,7,101,18]
输出: 4
解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
说明:

可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
你算法的时间复杂度应该为 O(n2) 。
进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
fun main(args: Array<String>) {
    println(
        lengthOfLIS2(intArrayOf(10, 9, 2, 5, 3, 7, 101, 18))
    )
}

/**
 * 动态规划
 * f(i) = min(f(0),..,f(i-1))+1
 */
fun lengthOfLIS(nums: IntArray): Int {
    if (nums.isEmpty()) return 0
    val lengthArray = IntArray(nums.size) { 1 }
    var maxLengthOfLIS = 1
    for (i in 1 until nums.size) {
        var lengthOfLIS = 0
        for (j in 0 until i) {  //可优化使用二分查找法
            if (nums[j] < nums[i]) {
                lengthOfLIS = Math.max(lengthOfLIS, lengthArray[j])
            }
        }
        lengthOfLIS++
        lengthArray[i] = lengthOfLIS
        maxLengthOfLIS = Math.max(maxLengthOfLIS, lengthOfLIS)
    }
    return maxLengthOfLIS
}

/**
 * 定义dp数组，k = 数组索引+1 , 表示子序列为长度为 k 的最后一个数为 dp[k]
 *       可知 dp[k+1] > dp[k], dp数组为递增数组
 */
fun lengthOfLIS2(nums: IntArray): Int {
    if (nums.isEmpty()) return 0
    val dp = ArrayList<Int>()
    for (i in 0 until nums.size) {
        val num = nums[i]
        var numAtIndex = 0
        for (j in 0 until dp.size) {
            if (dp[j] < num) {
                numAtIndex++
            }
        }
        if (numAtIndex > dp.size - 1) {
            dp.add(numAtIndex, num)
        } else {
            dp[numAtIndex] = Math.min(dp[numAtIndex], num)
        }
    }
    return dp.size
}