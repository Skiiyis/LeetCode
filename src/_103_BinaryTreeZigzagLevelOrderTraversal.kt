/**
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

例如：
给定二叉树 [3,9,20,null,null,15,7],

3
/ \
9  20
/  \
15   7
返回锯齿形层次遍历如下：

[
[3],
[20,9],
[15,7]
]
 */
fun main(args: Array<String>) {
    /*println(
        zigzagLevelOrder(
            TreeNode(3).also {
                it.left = TreeNode(9)
                it.right = TreeNode(20).also {
                    it.left = TreeNode(15)
                    it.right = TreeNode(7)
                }
            }
        )
    )*/
}

/**
 * 层次遍历
 */
fun zigzagLevelOrder(
    root: TreeNode?,
    level: List<TreeNode?> = mutableListOf(root),
    needReverse: Boolean = false,
    ans: MutableList<List<Int>> = mutableListOf()
): List<List<Int>> {
    if (root == null) return emptyList()
    ans.add(if (needReverse) {
        level.reversed()
    } else {
        level
    }.map {
        it?.`val` ?: 0
    })
    val nextLevel = level.flatMap {
        listOf(it?.left, it?.right)
    }.filter {
        it != null
    }
    if (nextLevel.isNotEmpty()) {
        zigzagLevelOrder(root, nextLevel, !needReverse, ans)
    }
    return ans
}