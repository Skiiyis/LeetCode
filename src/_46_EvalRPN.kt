import java.util.*

/**
 * 逆波兰表达式求值
根据逆波兰表示法，求表达式的值。

有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。

说明：

整数除法只保留整数部分。
给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
示例 1：

输入: ["2", "1", "+", "3", "*"]
输出: 9
解释: ((2 + 1) * 3) = 9
 */
fun main(args: Array<String>) {
    print(
        evalRPN(
            arrayOf(
                "2", "1", "+", "3", "*"
            )
        )
    )
}

fun evalRPN(tokens: Array<String>): Int {
    val stack = Stack<Int>()
    for (token in tokens) {
        if (token.toIntOrNull() != null) {
            stack.push(token.toInt())
        } else {
            val second = stack.pop()
            val first = stack.pop()
            if (token == "+") {
                stack.push(first + second)
            }
            if (token == "-") {
                stack.push(first - second)
            }
            if (token == "*") {
                stack.push(first * second)
            }
            if (token == "/") {
                stack.push(first / second)
            }
        }
    }
    return stack.pop()
}