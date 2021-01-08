//给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
//
// 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。 
//
// 示例: 
//
// 给定的有序链表： [-10, -3, 0, 5, 9],
//
//一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
//
//      0
//     / \
//   -3   9
//   /   /
// -10  5
// 
// Related Topics 深度优先搜索 链表 
// 👍 347 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
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
fun sortedListToBST(head: ListNode?): TreeNode? {
    return null
}

var currentListNode: ListNode? = null
fun buildTree(left: Int, right: Int): TreeNode? {
    val root = TreeNode(0)
    root.left = buildTree(left, (right + left) / 2)
    root.`val` = currentListNode?.`val` ?: 0
    currentListNode = currentListNode?.next
    root.right = buildTree((right + left) / 2, right)
    return root
}

fun getListLength(head: ListNode?): Int {
    var h: ListNode? = head ?: return 0
    var ret = 1
    while (h?.next != null) {
        ret++
        h = h.next
    }
    return ret
}
//leetcode submit region end(Prohibit modification and deletion)
