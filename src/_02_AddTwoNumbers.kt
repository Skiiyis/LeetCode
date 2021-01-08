/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

示例：

输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807
 */
fun main(args: Array<String>) {
    println(
        addTwoNumbers(
            ListNode(2).also {
                it.next = ListNode(4).also {
                    it.next = ListNode(3)
                }
            }, ListNode(5).also {
                it.next = ListNode(6).also {
                    it.next = ListNode(4)
                }
            }
        )
    )
}

/**
 * 先翻转链表，再求和，再翻转链表
 */
fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    var add = 0
    var c1 = l1
    var c2 = l2
    var rAns: ListNode? = null
    var cAns: ListNode? = null
    while (c1 != null || c2 != null || add != 0) {
        val count = (c1?.`val` ?: 0) + (c2?.`val` ?: 0) + add
        val node = ListNode(count % 10)
        add = count / 10
        if (rAns == null) {
            rAns = node
        }
        if (cAns == null) {
            cAns = node
        } else {
            cAns.next = node
            cAns = cAns.next
        }
        c1 = c1?.next
        c2 = c2?.next
    }
    return rAns
}

fun _2_reverseNode(head: ListNode?): ListNode? {
    if (head == null) return null
    var pre: ListNode? = null
    var cur = head
    while (cur != null) {
        val temp = cur
        cur = cur.next
        temp.next = pre
        pre = temp
    }
    return pre
}