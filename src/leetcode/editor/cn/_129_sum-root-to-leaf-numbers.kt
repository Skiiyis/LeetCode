//给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。 
//
// 例如，从根到叶子节点路径 1->2->3 代表数字 123。 
//
// 计算从根到叶子节点生成的所有数字之和。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例 1: 
//
// 输入: [1,2,3]
//    1
//   / \
//  2   3
//输出: 25
//解释:
//从根到叶子节点路径 1->2 代表数字 12.
//从根到叶子节点路径 1->3 代表数字 13.
//因此，数字总和 = 12 + 13 = 25. 
//
// 示例 2: 
//
// 输入: [4,9,0,5,1]
//    4
//   / \
//  9   0
// / \
//5   1
//输出: 1026
//解释:
//从根到叶子节点路径 4->9->5 代表数字 495.
//从根到叶子节点路径 4->9->1 代表数字 491.
//从根到叶子节点路径 4->0 代表数字 40.
//因此，数字总和 = 495 + 491 + 40 = 1026. 
// Related Topics 树 深度优先搜索 
// 👍 291 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
//DFS + 记忆化路径求和
fun sumNumbers(root: TreeNode?): Int {
    return mutableListOf<Int>().let {
        sumNumbers(root, numbers = it)
        it.sum()
    }
}

fun sumNumbers(root: TreeNode?, pathCount: Int = 0, numbers: MutableList<Int> = mutableListOf()) {
    if (root == null) {
        return
    }
    if (root.left == null && root.right == null) {
        numbers.add(pathCount * 10 + root.`val`)
        return
    }
    if (root.left != null) {
        sumNumbers(root.left, pathCount * 10 + root.`val`, numbers)
    }
    if (root.right != null) {
        sumNumbers(root.right, pathCount * 10 + root.`val`, numbers)
    }
}
//leetcode submit region end(Prohibit modification and deletion)
