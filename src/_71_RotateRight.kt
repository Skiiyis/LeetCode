/**
 * 旋转链表
给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。

示例 1:

输入: 1->2->3->4->5->NULL, k = 2
输出: 4->5->1->2->3->NULL
解释:
向右旋转 1 步: 5->1->2->3->4->NULL
向右旋转 2 步: 4->5->1->2->3->NULL
示例 2:

输入: 0->1->2->NULL, k = 4
输出: 2->0->1->NULL
解释:
向右旋转 1 步: 2->0->1->NULL
向右旋转 2 步: 1->2->0->NULL
向右旋转 3 步: 0->1->2->NULL
向右旋转 4 步: 2->0->1->NULL
 */
fun main(args: Array<String>) {
    println(
        rotateRight(
            ListNode(1).also {
                it.next = ListNode(2).also {
                    it.next = ListNode(3).also {
                        it.next = ListNode(4).also {
                            it.next = ListNode(5)
                        }
                    }
                }
            }, 6
        )
    )
}

/**
 * 双指针，两个指针保持一个k的距离，如果链表长度小于k则需要计算一下
 */
fun rotateRight(head: ListNode?, k: Int): ListNode? {
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
    return if (distance + 1 <= k) {
        rotateRight(head, k % (distance + 1))
    } else {
        currentNode?.next = head
        prevNode?.next.also {
            prevNode?.next = null
        }
    }
}