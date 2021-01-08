/**
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。

示例 1:

输入: 4->2->1->3
输出: 1->2->3->4
示例 2:

输入: -1->5->3->4->0
输出: -1->0->3->4->5
 */
fun main(args: Array<String>) {
    println(
        sortList(
            ListNode(4).also {
                it.next = ListNode(2).also {
                    it.next = ListNode(1).also {
                        it.next = ListNode(3)
                    }
                }
            }
        )
    )
}

/**
 * 并归排序
 */
fun sortList(head: ListNode?): ListNode? {
    if (head == null) return null
    if (head.next == null) return head
    _148_split(head).also {
        return _148_merge(sortList(it.first) to sortList(it.second))
    }
}

fun _148_merge(listNodes: Pair<ListNode?, ListNode?>): ListNode? {
    var ans = ListNode(Int.MIN_VALUE)
    var left = listNodes.first
    var right = listNodes.second
    var merged = ans
    while (true) {
        when {
            left == null -> return ans.next.also { merged.next = right }
            right == null -> return ans.next.also { merged.next = left }
            else -> {
                if (left.`val` < right.`val`) {
                    merged.next = left
                    merged = left
                    left = left.next
                } else {
                    merged.next = right
                    merged = right
                    right = right.next
                }
            }
        }
    }
}

fun _148_split(head: ListNode?): Pair<ListNode?, ListNode?> {
    var pFast = head
    var pSlow = head
    while (true) {
        pFast = pFast?.next
        pFast = pFast?.next
        if (pFast != null) {
            pSlow = pSlow?.next
        } else {
            return (pSlow?.next to head).also {
                pSlow?.next = null
            }
        }
    }
}

