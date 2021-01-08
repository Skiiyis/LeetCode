/**
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。

示例 1:

输入: 1->2->3->3->4->4->5
输出: 1->2->5
示例 2:

输入: 1->1->1->2->3
输出: 2->3
 */
fun main(args: Array<String>) {
    println(
        deleteDuplicates(
            ListNode(1).also {
                it.next = ListNode(2).also {
                    it.next = ListNode(3).also {
                        it.next = ListNode(3).also {
                            it.next = ListNode(4).also {
                                it.next = ListNode(4).also {
                                    it.next = ListNode(5)
                                }
                            }
                        }
                    }
                }
            }
        )
    )
}

fun deleteDuplicates(node: ListNode?, prevNodeVal: Int? = null): ListNode? {
    if (node == null) return null
    val next = deleteDuplicates(node.next, node.`val`)
    return if (node.`val` != prevNodeVal && node.`val` != node.next?.`val`) {
        node.also { it.next = next }
    } else {
        next
    }
}