/**
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。

示例：

输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4
 */
fun main(args: Array<String>) {
    println(
        mergeTwoLists(
            ListNode(1).also {
                it.next = ListNode(2).also {
                    it.next = ListNode(4)
                }
            },
            ListNode(1).also {
                it.next = ListNode(3).also {
                    it.next = ListNode(4)
                }
            }
        )
    )
}

fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
    var cl1 = l1
    var cl2 = l2
    val head = min(cl1, cl2)
    var cHead = head
    while (cHead != null) {
        if (cl1 == cHead) {
            cl1 = cl1.next
        }
        if (cl2 == cHead) {
            cl2 = cl2.next
        }
        val newHead = min(cl1, cl2)
        cHead.next = newHead
        cHead = newHead
    }
    return head
}

fun min(l1: ListNode?, l2: ListNode?): ListNode? {
    return if (l1 == null && l2 == null) {
        null
    } else if (l1 == null) {
        l2
    } else if (l2 == null) {
        l1
    } else {
        if (l1.`val` > l2.`val`) {
            l2
        } else {
            l1
        }
    }
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
    override fun toString(): String {
        return "$`val`,${next?.toString()}"
    }
}