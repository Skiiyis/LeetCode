import kotlin.math.max

/**
 * 求二叉树两个叶子节点间的最长路径
 * leetcode 124 的简单版本
 **/
fun maxPath(root: Node?): Int {
    // 遍历过程中求解，有两种备选答案
    // 1. 最长路径从当前节点的左子树到当前节点的右子树
    // 2. 最长路径仅经过左右子树深度更深的那半边，这个在父级节点可以转化为答案1
    var ans = 0
    // 返回树的深度
    fun dfs(root: Node?): Int {
        if (root == null) return -1
        val leftMaxPath = dfs(root.left) + 1
        val rightMaxPath = dfs(root.right) + 1
        println("ans: $ans, leftMaxPath: $leftMaxPath, rightMaxPath: $rightMaxPath")
        ans = max(ans, leftMaxPath + rightMaxPath)
        return max(leftMaxPath, rightMaxPath)
    }
    dfs(root)
    return ans
}

fun main(args: Array<String>) {
    println(
        maxPath(
            Node(0).also {
                it.left = Node(0).also {
                    it.left = Node(0).also {
                        it.left = Node(0)
                    }
                    it.right = Node(0)
                }
                it.right = Node(0)
            }
        )
    )
}