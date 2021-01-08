/**
 *  验证二叉搜索树
给定一个二叉树，判断其是否是一个有效的二叉搜索树。

假设一个二叉搜索树具有如下特征：

节点的左子树只包含小于当前节点的数。
节点的右子树只包含大于当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。
示例 1:

输入:
2
/ \
1   3
输出: true
示例 2:

输入:
5
/ \
1   4
/ \
3   6
输出: false
解释: 输入为: [5,1,4,null,null,3,6]。
根节点的值为 5 ，但是其右子节点值为 4 。
 */
fun main(args: Array<String>) {
    //[10,5,15,null,null,6,20]
    println(
        isValidBST(
            TreeNode(10).also {
                it.left = TreeNode(5)
                it.right = TreeNode(15).also {
                    it.left = TreeNode(6)
                    it.right = TreeNode(20)
                }
            }
        )
    )
}

/**
 * 树的遍历，这个地方注意遍历时需要确定当前节点的上下界
 */
fun isValidBST(root: TreeNode?, left: Int? = null, right: Int? = null): Boolean {
    if (root == null) return true
    val leftValid = if (root.left != null) isValidBST(root.left, left, root.`val`) else true
    val rootValid = (if (left != null) root.`val` > left else true) && (if (right != null) root.`val` < right else true)
    val rightValid = if (root.right != null) isValidBST(root.right, root.`val`, right) else true
    return leftValid && rootValid && rightValid
}