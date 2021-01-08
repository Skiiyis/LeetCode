import java.util.*

/**
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。

示例 1:

输入: "(()"
输出: 2
解释: 最长有效括号子串为 "()"
示例 2:

输入: ")()())"
输出: 4
解释: 最长有效括号子串为 "()()"

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-valid-parentheses
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
fun main(args: Array<String>) {
    println(longestValidParentheses(")()())"))
}

/**
 *  线性遍历，记录 ( 和 ) 的数量，当有一个) 和 (配对时有效子串+2，
 *  当)的数量大于(时重置( 和 ) 的数量
 */
fun longestValidParentheses(s: String): Int {
    val stack = Stack<Int>()
    var ans = 0
    stack.push(-1)
    s.toCharArray().forEachIndexed { index, c ->
        if (c == '(') {
            stack.add(index)
        }
        if (c == ')') {
            stack.pop()
            if (stack.isNotEmpty()) {
                ans = Math.max(ans, index - stack.peek())
            } else {
                stack.push(index)
            }
        }
    }
    return ans
}