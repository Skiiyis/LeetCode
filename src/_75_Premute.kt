/**
 * 全排列
给定一个 没有重复 数字的序列，返回其所有可能的全排列。

示例:

输入: [1,2,3]
输出:
[
[1,2,3],
[1,3,2],
[2,1,3],
[2,3,1],
[3,1,2],
[3,2,1]
]
 */
fun main(args: Array<String>) {
    println(
        permute(
            intArrayOf(
                1, 2, 3
            )
        )
    )
}

//全排列，决策树的遍历，
fun permute(nums: IntArray): List<List<Int>> {
    val ans = ArrayList<ArrayList<Int>>()
    _75_backTack(nums, ans = ans)
    return ans
}

fun _75_backTack(
    nums: IntArray,
    ans: ArrayList<ArrayList<Int>> = ArrayList(),
    selected: ArrayList<Int> = ArrayList()
) {
    if (selected.size == nums.size) {
        ans.add(ArrayList(selected))
    }
    nums.forEach {
        if (!selected.contains(it)) {
            selected.add(it)
            _75_backTack(nums, ans, selected)
            selected.removeAt(selected.size - 1)
        }
    }
}