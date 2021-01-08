/**
 * 给定一个二叉树，原地将它展开为一个单链表。

例如，给定二叉树

1
/ \
2   5
/ \   \
3   4   6
将其展开为：

1
\
2
\
3
\
4
\
5
\
6

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
fun main(args: Array<String>) {
    val r = TreeNode(1).also {
        it.left = TreeNode(2).also {
            it.left = TreeNode(3)
            it.right = TreeNode(4)
        }
        it.right = TreeNode(5).also {
            it.right = TreeNode(6)
        }
    }
    flatten(
        r
    )
    println(
        r
    )

}

fun flatten(root: TreeNode?): Unit {
    if (root == null) return
    when {
        root.left == null -> flatten(root.right)
        root.right == null -> {
            flatten(root.left)
            root.right = root.left
            root.left = null
        }
        else -> {
            flatten(root.left)
            var leftLastNode = root.left
            while (leftLastNode?.right != null) {
                leftLastNode = leftLastNode.right
            }
            flatten(root.right)
            leftLastNode?.right = root.right
            root.right = root.left
            root.left = null
        }
    }
}