/**
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。

示例:

输入: [1,1,2]
输出:
[
[1,1,2],
[1,2,1],
[2,1,1]
]
 */
fun main(args: Array<String>) {
    println(
        permuteUnique(
            intArrayOf(3, 3, 0, 3)
        )
    )
}

fun permuteUnique(nums: IntArray): List<List<Int>> {
    return permuteUnique(nums.toMutableList().also { it.sort() })
}

fun permuteUnique(
    nums: MutableList<Int>,
    cur: MutableList<Int> = mutableListOf(),
    ans: MutableList<MutableList<Int>> = mutableListOf()
): List<List<Int>> {
    if (nums.isEmpty()) {
        ans.add(ArrayList(cur))
    }
    for (i in 0 until nums.size) {
        if (i > 0 && nums[i] == nums[i - 1]) {
            continue
        }
        cur.add(nums.removeAt(i))
        permuteUnique(nums, cur, ans)
        nums.add(i, cur.removeAt(cur.size - 1))
    }
    return ans
}

