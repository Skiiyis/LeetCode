import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List
import java.util.*

/**
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。

你可以假设除了数字 0 之外，这两个数字都不会以零开头。

进阶：

如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。

示例：

输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 8 -> 0 -> 7
 */
fun main(args: Array<String>) {
    println(
        _445_addTwoNumbers(
            ListNode(7).also {
                it.next = ListNode(2).also {
                    it.next = ListNode(4).also {
                        it.next = ListNode(3)
                    }
                }
            }, ListNode(5).also {
                it.next = ListNode(6).also {
                    it.next = ListNode(4).also {
                    }
                }
            }
        )
    )
}

/**
 * 逆序可以考虑使用栈
 */
fun _445_addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    val stack1 = Stack<Int>()
    var head = l1
    while (head != null) {
        stack1.push(head.`val`)
        head = head.next
    }
    val stack2 = Stack<Int>()
    head = l2
    while (head != null) {
        stack2.push(head.`val`)
        head = head.next
    }
    var ans: ListNode? = null
    var flow = 0
    while (stack1.isNotEmpty() || stack2.isNotEmpty() || flow > 0) {
        val n1 = if (stack1.isNotEmpty()) {
            stack1.pop()
        } else {
            0
        }
        val n2 = if (stack2.isNotEmpty()) {
            stack2.pop()
        } else {
            0
        }
        val sum = (n1 + n2 + flow)
        flow = sum / 10
        val node = ListNode(sum % 10)
        node.next = ans
        ans = node
    }
    return ans
}
