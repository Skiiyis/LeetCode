/**
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。

说明: 叶子节点是指没有子节点的节点。

示例:
给定如下二叉树，以及目标和 sum = 22，

5
/ \
4   8
/   / \
11  13  4
/  \    / \
7    2  5   1
返回:

[
[5,4,11,2],
[5,8,4,5]
]

 */
fun main(args: Array<String>) {
    println(
        pathSum(
            TreeNode(5).also {
                it.left = TreeNode(4).also {
                    it.left = TreeNode(11).also {
                        it.left = TreeNode(7)
                        it.right = TreeNode(2)
                    }
                }
                it.right = TreeNode(8).also {
                    it.left = TreeNode(13)
                    it.right = TreeNode(4).also {
                        it.left = TreeNode(5)
                        it.right = TreeNode(1)
                    }
                }
            }, 22
        )
    )
}

/**
 * 带路径的DFS?
 */
fun pathSum(root: TreeNode?, sum: Int, path: MutableList<Int> = mutableListOf()): List<List<Int>> {
    if (root == null) {
        return emptyList()
    }
    if (root.left == null && root.right == null) {
        return if (sum == root.`val`) {
            listOf(ArrayList(path).also { it.add(root.`val`) })
        } else {
            emptyList()
        }
    }
    path.add(root.`val`)
    val leftPath = pathSum(root.left, sum - root.`val`, path)
    val rightPath = pathSum(root.right, sum - root.`val`, path)
    path.removeAt(path.size - 1)
    return leftPath.plus(rightPath)
}

