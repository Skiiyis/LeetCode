/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

示例：

给定一个链表: 1->2->3->4->5, 和 n = 2.

当删除了倒数第二个节点后，链表变为 1->2->3->5.
说明：

给定的 n 保证是有效的。

进阶：

你能尝试使用一趟扫描实现吗？

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
fun main(args: Array<String>) {
    println(
        removeNthFromEnd(
            ListNode(1), 1
        )
    )
    println(
        removeNthFromEnd(
            ListNode(1).also {
                it.next = ListNode(2)
            }, 2
        )
    )
    println(
        removeNthFromEnd(
            ListNode(1).also {
                it.next = ListNode(2).also {
                    it.next = ListNode(3).also {
                        it.next = ListNode(4).also {
                            it.next = ListNode(5)
                        }
                    }
                }
            }, 3
        )
    )
}

/**
 * 双指针法，保持一个两个指针相差为n,第一个指针到达尾节点的时候第二个指针刚好到达倒数第n-1个
 */
fun removeNthFromEnd(head: ListNode?, k: Int): ListNode? {
    var distance = 0
    var currentNode: ListNode? = head
    var prevNode: ListNode? = head
    while (currentNode?.next != null) {
        currentNode = currentNode.next
        when {
            distance != k -> distance++
            distance == k -> {
                prevNode = prevNode?.next
            }
        }
    }
    if (distance != k) {
        return head?.next
    }
    val next = prevNode?.next?.next
    prevNode?.next = next
    return head
}