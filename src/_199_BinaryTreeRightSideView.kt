/**
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。

示例:

输入: [1,2,3,null,5,null,4]
输出: [1, 3, 4]
解释:

1            <---
/   \
2     3         <---
\     \
5     4       <---
 */
fun main(args: Array<String>) {
    println(
        rightSideView(
            TreeNode(1).also {
                it.left = TreeNode(2).also {
                    it.right = TreeNode(5)
                }
                it.right = TreeNode(3).also {
                    it.right = TreeNode(4)
                }
            }
        )
    )
}

//按照根，右，左的方式DFS遍历，每一层记录该层第一次出现的值
fun rightSideView(root: TreeNode?, depth: Int = 0, ans: ArrayList<Int> = ArrayList()): List<Int> {
    if (root == null) {
        return ans
    }
    if (ans.size <= depth) {
        ans.add(root.`val`)
    }
    rightSideView(root.right, depth + 1, ans)
    rightSideView(root.left, depth + 1, ans)
    return ans
}