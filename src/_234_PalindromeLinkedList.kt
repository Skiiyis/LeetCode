/**
 * 请判断一个链表是否为回文链表。

示例 1:

输入: 1->2
输出: false
示例 2:

输入: 1->2->2->1
输出: true
进阶：
你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
fun main(args: Array<String>) {
    println(
        isPalindrome(
            ListNode(1).also {
                it.next = ListNode(2).also {
                    it.next = ListNode(2).also {
                        it.next = ListNode(2).also {
                        }
                    }
                }
            }
        )
    )
}

/**
 * 翻转链表 + 比较
 */
fun isPalindrome(head: ListNode?): Boolean {
    val cutListNodes = _234_centerCutListNode(head)
    val lRight = _234_reverseNode2(cutListNodes.second)
    val lLeft = cutListNodes.first
    var pRight = lRight
    var pLeft = lLeft
    while (pRight != null) {
        if (pLeft == null || pRight.`val` != pLeft.`val`) {
            return false
        }
        pRight = pRight.next
        pLeft = pLeft.next
    }
    return true
}

fun _234_centerCutListNode(head: ListNode?): Pair<ListNode?, ListNode?> {
    if (head == null) return null to null
    var pFast = head
    var pSlow = head
    while (true) {
        pFast = pFast?.next
        pFast = pFast?.next
        if (pFast != null) {
            pSlow = pSlow?.next
        } else {
            return (head to pSlow?.next).also {
                pSlow?.next = null
            }
        }
    }
}

fun _234_reverseNode2(head: ListNode?): ListNode? {
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

fun _234_reverseNode(head: ListNode?): Pair<ListNode?, ListNode?> {
    if (head?.next == null) return head to head
    _234_reverseNode(head.next).also {
        it.second?.next = head
        head.next = null
        return (it.first to head)
    }
}