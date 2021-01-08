/**
 *翻转一棵二叉树。

示例：

输入：

4
/   \
2     7
/ \   / \
1   3 6   9
输出：

4
/   \
7     2
/ \   / \
9   6 3   1
 */
fun main(args: Array<String>) {
    println(
        invertTree(
            TreeNode(4).also {
                it.left = TreeNode(2).also {
                    it.left = TreeNode(1)
                    it.right = TreeNode(3)
                }
                it.right = TreeNode(7).also {
                    it.left = TreeNode(6)
                    it.right = TreeNode(9)
                }
            }
        )
    )
}

/**
 * 递归翻转
 */
fun invertTree(root: TreeNode?): TreeNode? {
    if (root == null) return null
    invertTree(root.left)
    invertTree(root.right)
    val temp = root.left
    root.left = root.right
    root.right = temp
    return root
}