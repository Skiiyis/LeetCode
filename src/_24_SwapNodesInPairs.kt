/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

 

示例:

给定 1->2->3->4, 你应该返回 2->1->4->3.
 */
fun main(args: Array<String>) {
    println(
        swapPairs(
            ListNode(1).also {
                it.next = ListNode(2).also {
                    it.next = ListNode(3).also {
                        it.next = ListNode(4)
                    }
                }
            }
        )
    )
}

fun swapPairs(head: ListNode?): ListNode? {
    var prevprevNode: ListNode? = null
    var prevNode: ListNode? = null
    var ans: ListNode? = null
    var currentNode = head
    while (currentNode != null) {
        if (prevNode == null) {
            prevNode = currentNode
        } else {
            val nextNode = currentNode.next
            prevNode.next = nextNode
            currentNode.next = prevNode
            if (ans == null) {
                ans = currentNode
            }
            prevprevNode?.next = currentNode
            prevprevNode = prevNode
            currentNode = prevNode
            prevNode = null
        }
        currentNode = currentNode.next
    }
    return ans ?: head
}

