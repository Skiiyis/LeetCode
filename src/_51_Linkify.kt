/**
 * 将        4        转换成  1         这种只有右子树的模式
 *         /  \              \
 *        2    6              2
 *       / \  / \              \
 *      1  3 5  7               3
 *                               \
 *                                4
 *                                 \
 *                                  5...
 *
 *  二叉搜索树转链表
 */
fun main(args: Array<String>) {
    val node = Node(
        4,
        Node(
            2,
            null,
            Node(3)
        ),
        Node(
            6
        )
    )
    printNode(linkify(node)[0])
}

fun printNode(root: Node) {
    print(root.value)
    root.left?.also(::printNode)
    root.right?.also(::printNode)
}

/**
 * 基本思路：分治+递归
 * 左右子树分治再合并, 考虑到链表化之后头节点不是原来的子树的根节点，递归方法要考虑返回值
 */
fun linkify(root: Node): Array<Node> {
    val leftLinkify = root.left?.let { linkify(it) }
    val rightLinkify = root.right?.let { linkify(it) }
    var head = root
    var tail = root
    if (leftLinkify != null) {
        head = leftLinkify[0]
        leftLinkify[1].right = root
    }
    if (rightLinkify != null) {
        tail = rightLinkify[1]
        root.right = rightLinkify[0]
    }
    root.left = null
    return arrayOf(head, tail)
}

data class Node(
    val value: Int,
    var left: Node? = null,
    var right: Node? = null
)