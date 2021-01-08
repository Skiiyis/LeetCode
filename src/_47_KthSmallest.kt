import java.util.*

/**
 * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。

说明：
你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。

示例 1:

输入: root = [3,1,4,null,2], k = 1
3
/ \
1   4
\
2
输出: 1
示例 2:

输入: root = [5,3,6,2,4,null,null,1], k = 3
5
/ \
3   6
/ \
2   4
/
1
输出: 3
进阶：
如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
 */
fun main(args: Array<String>) {
    print(
        kthSmallest(
            TreeNode(5).also {
                it.left = TreeNode(3).also {
                    it.left = TreeNode(2).also {
                        it.left = TreeNode(1)
                    }
                    it.right = TreeNode(4)
                }
                it.right = TreeNode(6)
            }, 3
        )
    )
}

//维持一个优先队列，然后深度优先前序遍历
fun kthSmallest(root: TreeNode, k: Int): Int {
    val queue = PriorityQueue<Int>(k) { o1, o2 -> o2 - o1 }
    search(queue, root, k)
    return queue.peek()
}

fun search(queue: PriorityQueue<Int>, root: TreeNode, k: Int) {
    root.left?.also { search(queue, it, k) }
    if (queue.size == k) {
        return
    }
    queue.add(root.`val`)
    print(queue)
    if (queue.size == k) {
        return
    }
    root.right?.also { search(queue, it, k) }
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null

    override fun toString(): String {
        return "${`val`} ${left?.toString()} ${right?.toString()}"
    }
}