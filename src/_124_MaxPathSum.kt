import java.lang.Math.max

/**
 * 给定一个非空二叉树，返回其最大路径和。

本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。

示例 1:

输入: [1,2,3]

1
/   \
2   3

输出: 6
示例 2:

输入: [-10,9,20,null,null,15,7]

-10
/ \
9  20
/  \
15   7

输出: 42
 */
fun main(args: Array<String>) {
    println(
        maxPathSum(
            TreeNode(-3)
        )
    )
    println("getMaxPath $maxPath")
}

fun maxPathSum(root: TreeNode?): Int {
    return max(maxPath(root), maxPath)
}

var maxPath = Int.MIN_VALUE
fun maxPath(node: TreeNode?): Int {
    if (node?.`val` == null) {
        return 0
    }
    val maxLeftPath = max(0, maxPath(node.left))
    val maxRightPath = max(0, maxPath(node.right))
    maxPath = max(maxLeftPath + maxRightPath + node.`val`, maxPath)
    return max(maxLeftPath, maxRightPath) + node.`val`
}