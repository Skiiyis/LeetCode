/**
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。

说明:
1 ≤ m ≤ n ≤ 链表长度。

示例:

输入: 1->2->3->4->5->NULL, m = 2, n = 4
输出: 1->4->3->2->5->NULL
 */
fun main(args: Array<String>) {
    println(
        reverseBetween(
            ListNode(1).also {
                it.next = ListNode(2).also {
                    /*it.next = ListNode(3).also {
                        it.next = ListNode(4).also {
                            it.next = ListNode(5).also {
                                it.next = ListNode(6)
                            }
                        }
                    }*/
                }
            }, 1, 2
        )
    )
}

/**
 * 翻转指定区间的链表，可以先截取翻转再拼接，
 * 由于要一趟扫描完成，使用递归或者迭代。
 */
fun reverseBetween(head: ListNode?, m: Int, n: Int): ListNode? {
    if (head == null) return null
    var pre: ListNode? = null
    var curIndex = 0
    var cur = head
    var preM: ListNode? = null
    var mNode: ListNode? = null
    var nNodeNext: ListNode? = null
    while (cur != null) {
        curIndex++
        val temp = cur
        cur = cur.next
        when (curIndex) {
            m - 1 -> preM = temp
            n + 1 -> {
                //m-1 -> pre ... m -> n+1
                nNodeNext = temp
            }
            in m..n -> {
                if (curIndex == m) mNode = temp
                temp.next = pre
                pre = temp
            }
        }
    }
    preM?.next = pre
    mNode?.next = nNodeNext
    return if (preM != null) head else pre
}