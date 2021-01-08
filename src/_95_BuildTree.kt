/**
 * 根据一棵树的前序遍历与中序遍历构造二叉树。

注意:
你可以假设树中没有重复的元素。

例如，给出

前序遍历 preorder = [3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]
返回如下的二叉树：

3
/ \
9  20
/  \
15   7

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
fun main(args: Array<String>) {
    println(
        buildTree(
            intArrayOf(1,2,3),
            intArrayOf(3,2,1)
        )
    )
}

/**
 * 前序遍历的结果[根节点，[左子树的前序遍历的结果],[右子树的前序遍历结果]]
 * 中序遍历的结果[[左子树的中序遍历的结果],根节点,[右子树的中序遍历结果]]
 * 先取出来前序遍历的结果中的根节点，再把左右子树分别递归获取到结果
 */
fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
    if (preorder.isEmpty()) return null
    val rootValue = preorder[0]
    val rootIndex = inorder.indexOf(rootValue) //1
    val leftInOrder = inorder.copyOfRange(
        0,
        Math.max(0, rootIndex)
    )//[0,1) = 0
    val rightInOrder = inorder.copyOfRange(
        rootIndex + 1,
        Math.max(inorder.size, rootIndex + 1)
    )//[2,5) = 2,3,4
    val leftPreOrder = preorder.copyOfRange(
        1,
        Math.max(1, leftInOrder.size + 1)
    )//[1,2) = 1
    val rightPreOrder = preorder.copyOfRange(
        preorder.size - rightInOrder.size,
        Math.max(preorder.size - rightInOrder.size, preorder.size)
    ) //[2,5) = 2,3,4
    return TreeNode(rootValue).also {
        it.left = buildTree(leftPreOrder, leftInOrder)
        it.right = buildTree(rightPreOrder, rightInOrder)
    }
}

