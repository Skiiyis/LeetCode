/**
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。

示例：
二叉树：[3,9,20,null,null,15,7],

3
/ \
9  20
/  \
15   7
返回其层次遍历结果：

[
[3],
[9,20],
[15,7]
]
 */
fun main(args: Array<String>) {
    println(
        levelOrder(
            TreeNode(3).also {
                it.left = TreeNode(9)
                it.right = TreeNode(20).also {
                    it.left = TreeNode(15)
                    it.right = TreeNode(7)
                }
            }
        )
    )
}

fun levelOrder(root: TreeNode?): List<List<Int>> {
    val ans = mutableListOf<List<Int>>()
    val levelNodes = mutableListOf<TreeNode?>()
    if (root == null) return ans
    levelNodes.add(root)
    while (levelNodes.isNotEmpty()) {
        val l = mutableListOf<Int>()
        val nextLevel = levelNodes.flatMap { t ->
            l.add(t?.`val` ?: 0)
            mutableListOf<TreeNode>().also { l ->
                t?.left?.also { l.add(it) }
                t?.right?.also { l.add(it) }
            }
        }
        levelNodes.clear()
        levelNodes.addAll(nextLevel)
        ans.add(l)
    }
    return ans
}