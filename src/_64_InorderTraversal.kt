import java.util.*

/**
 * 给定一个二叉树，返回它的中序 遍历。

示例:

输入: [1,null,2,3]
1
\
2
/
3

输出: [1,3,2]
进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
fun main(args: Array<String>) {
    println(inorderTraversal(
        TreeNode(1).also {
            it.right = TreeNode(2).also {
                it.left = TreeNode(3)
            }
        }
    ))
}

/**
 * 迭代求解
 */
fun inorderTraversal(root: TreeNode?): List<Int> {
    val ans = ArrayList<Int>()
    if (root == null) return ans
    val stack = Stack<TreeNode>()
    stack.push(root)
    while (stack.isNotEmpty()) {
        val e = stack.pop()
        if (e.left != null || e.right != null) {
            e.right?.also { stack.push(it) }
            stack.push(e)
            e.left?.also { stack.push(it) }
            e.left = null
            e.right = null
        } else {
            ans.add(e.`val`)
        }
    }
    return ans
}

/**
 * 递归求解
 */
fun inorderTraversal(root: TreeNode?, ans: ArrayList<Int> = ArrayList()): List<Int> {
    if (root == null) return ans
    root.left.also { inorderTraversal(root.left, ans) }
    root.`val`.also { ans.add(it) }
    root.right.also { inorderTraversal(root.right, ans) }
    return ans
}