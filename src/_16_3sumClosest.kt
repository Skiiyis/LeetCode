/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

 

示例：

输入：nums = [-1,2,1,-4], target = 1
输出：2
解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 

提示：

3 <= nums.length <= 10^3
-10^3 <= nums[i] <= 10^3
-10^4 <= target <= 10^4
 */
fun main(args: Array<String>) {
    println(
        threeSumClosest2(
            intArrayOf(
                1, 2, 4, 8, 16, 32, 64, 128
            ),
            82
        )
    )
}

//先排序，然后用双指针遍历一遍求最小值，（不对）
//固定一个值，剩下两个按双指针走，中间可剪枝？O(n^3)
fun threeSumClosest(nums: IntArray, target: Int): Int {
    nums.sort()
    var ans: Int? = null
    for (f in 0 until nums.size) {
        for (s in (f + 1) until nums.size) {
            for (e in (s + 1) until nums.size) {
                val sum = nums[f] + nums[s] + nums[e]
                if (Math.abs(sum - target) <= Math.abs((ans ?: sum) - target)) {
                    //sum更接近target
                    ans = sum
                }
                //再往后遍历数字更大，距离target也更远，可跳过
                if (sum > target) break
            }
        }
    }
    return ans ?: throw Exception("no answer!!")
}

fun threeSumClosest2(nums: IntArray, target: Int): Int {
    nums.sort()
    var ans: Int? = null
    for (f in 0 until nums.size) {
        var s = f + 1
        var e = nums.size - 1
        while (s < e) {
            val sum = nums[f] + nums[s] + nums[e]
            if (Math.abs(sum - target) <= Math.abs((ans ?: sum) - target)) {
                //sum更接近target
                ans = sum
            }
            if (sum > target) {
                e--
            } else {
                s++
            }
        }
    }
    return ans ?: throw Exception("no answer!!")
}