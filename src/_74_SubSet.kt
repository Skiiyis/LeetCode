/**
 * 子集
给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。

说明：解集不能包含重复的子集。

示例:

输入: nums = [1,2,3]
输出:
[
[3],
[1],
[2],
[1,2,3],
[1,3],
[2,3],
[1,2],
[]
]
 */
fun main(args: Array<String>) {
    println(subsets2(intArrayOf(1, 2, 3)))
}

/**
 * 每一个新元素都和之前的元素形成新的子集（迭代
 */
fun subsets(nums: IntArray): List<List<Int>> {
    val ans = ArrayList<ArrayList<Int>>()
    ans.add(ArrayList())
    nums.forEach { num ->
        val size = ans.size
        for (i in 0 until size) {
            ans.add(ArrayList(ans[i]).also { it.add(num) })
        }
    }
    return ans
}

/**
 * 回溯
 */
fun subsets2(nums: IntArray): List<List<Int>> {
    val ans = ArrayList<ArrayList<Int>>()
    backTack(0, nums, ans, ArrayList())
    return ans
}

fun backTack(i: Int, nums: IntArray, ans: ArrayList<ArrayList<Int>>, tmp: ArrayList<Int>) {
    ans.add(ArrayList(tmp))
    println(ans)
    for (j in i until nums.size) {
        tmp.add(nums[j])
        backTack(j + 1, nums, ans, tmp)
        tmp.removeAt(tmp.size - 1)
    }
}