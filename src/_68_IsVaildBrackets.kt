import java.util.*

/**
 * 有效的括号
给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
注意空字符串可被认为是有效字符串。

示例 1:

输入: "()"
输出: true
示例 2:

输入: "()[]{}"
输出: true
示例 3:

输入: "(]"
输出: false
 */
fun main(args: Array<String>) {
    println(isValid("]"))
}

//这道题用栈做
fun isValid(s: String): Boolean {
    val stack = Stack<Char>()
    s.toCharArray().forEach {
        if (it == '(' || it == '[' || it == '{') {
            stack.push(it)
        } else if (it == ')') {
            if (stack.isNotEmpty() && stack.peek() == '(') {
                stack.pop()
            } else {
                return false
            }
        } else if (it == ']') {
            if (stack.isNotEmpty() && stack.peek() == '[') {
                stack.pop()
            } else {
                return false
            }
        } else if (it == '}') {
            if (stack.isNotEmpty() && stack.peek() == '{') {
                stack.pop()
            } else {
                return false
            }
        }
    }
    return stack.isEmpty()
}