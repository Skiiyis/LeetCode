/**
 * 160.相交链表
 */
public class _22_GetIntersectionNode {
    public static void main(String[] args) {

    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode cA = headA;
        ListNode cB = headB;
        if (headA == null || headB == null) {
            return null;
        }
        int iA = 1;
        int iB = 1;
        while (cA != cB) {
            cA = cA.next;
            if (cA == null) {
                if (iA == 0) {
                    return null;
                }
                cA = headB;
                iA = 0;
            }
            cB = cB.next;
            if (cB == null) {
                if (iB == 0) {
                    return null;
                }
                cB = headA;
                iB = 0;
            }
        }
        return cA;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
