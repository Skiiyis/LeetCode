/**
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的每个数字在每个组合中只能使用一次。

说明：

所有数字（包括目标数）都是正整数。
解集不能包含重复的组合。 
示例 1:

输入: candidates = [10,1,2,7,6,1,5], target = 8,
所求解集为:
[
[1, 7],
[1, 2, 5],
[2, 6],
[1, 1, 6]
]
示例 2:

输入: candidates = [2,5,2,1,2], target = 5,
所求解集为:
[
  [1,2,2],
  [5]
]

 */
fun main(args: Array<String>) {
    println(
        combinationSum2(
            intArrayOf(
                14,6,25,9,30,20,33,34,28,30,16,12,31,9,9,12,34,16,25,32,8,7,30,12,33,20,21,29,24,17,27,34,11,17,30,6,32,21,27,17,16,8,24,12,12,28,11,33,10,32,22,13,34,18,12
            ),
            27
        )
    )
}

fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
    val nums = candidates.toMutableList().also { it.sort() }
    return combinationSum2(nums, target)
}

//@超时
fun combinationSum2(
    nums: MutableList<Int>,
    target: Int,
    start: Int = 0,
    cur: MutableList<Int> = mutableListOf(),
    curSum: Int = 0,
    ans: MutableList<MutableList<Int>> = mutableListOf()
): MutableList<MutableList<Int>> {
    if (nums.isEmpty() || curSum >= target) {
        if (curSum == target) {
            ans.add(ArrayList(cur))
        }
        return ans
    }
    for (i in start until nums.size) {
        if (i > start && nums[i] == nums[i - 1]) {
            continue
        }
        cur.add(nums[i])
        combinationSum2(nums, target, i + 1, cur, curSum + nums[i], ans)
        cur.removeAt(cur.size - 1)
    }
    return ans
}