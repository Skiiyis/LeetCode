//给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。 
//
// 我们是这样定义一个非递减数列的： 对于数组中所有的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。 
//
// 
//
// 示例 1: 
//
// 输入: nums = [4,2,3]
//输出: true
//解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
// 
//
// 示例 2: 
//
// 输入: nums = [4,2,1]
//输出: false
//解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
// 
//
// 
//
// 说明： 
//
// 
// 1 <= n <= 10 ^ 4 
// - 10 ^ 5 <= nums[i] <= 10 ^ 5 
// 
// Related Topics 数组 
// 👍 322 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
fun checkPossibility(nums: IntArray): Boolean {
    if (nums.size <= 1) return true
    var modified = false
    for (i in 0 until nums.size) {
        val num = nums[i]
        val left = nums.getOrElse(i - 1) { Int.MIN_VALUE }
        val right = nums.getOrElse(i + 1) { Int.MAX_VALUE }
        if (right < num) { //出现了下降的拐点
            if (modified) return false
            modified = true
            if (left < right) {
                nums[i] = right
            } else {
                nums[i + 1] = num
            }
        }
    }
    return true
}

/*fun main(args: Array<String>) {
    println(
        Solution().checkPossibility(
            intArrayOf(
                4, 2, 1
            )
        )
    )
}*/
//leetcode submit region end(Prohibit modification and deletion)
