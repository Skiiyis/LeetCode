import java.lang.StringBuilder
import java.util.*

/**
 * 基本计算器 II
实现一个基本的计算器来计算一个简单的字符串表达式的值。

字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。

示例 1:

输入: "3+2*2"
输出: 7
示例 2:

输入: " 3/2 "
输出: 1
示例 3:

输入: " 3+5 / 2 "
输出: 5
说明：

你可以假设所给定的表达式都是有效的。
请不要使用内置的库函数 eval。
 */
fun main(args: Array<String>) {
    println(
        calculate("1+1+1")
    )
}

fun calculate(s: String): Int {
    val stack = ArrayList<String>()
    val number = StringBuilder()
    for (c in s.toCharArray()) {
        if (c.isDigit()) {
            number.append(c)
            continue
        }
        if (c == '+' || c == '-' || c == '*' || c == '/') {
            stack.add(number.toString())
            number.clear()
            stack.add(c.toString())
            continue
        }
    }
    stack.add(number.toString())
    println(stack)
    var i = 0
    while (true) {
        if (stack.getOrNull(i) == null) {
            break
        }
        if (stack[i] == "*") {
            stack[i - 1] = (stack[i - 1].toInt() * stack[i + 1].toInt()).toString()
            stack.removeAt(i)
            stack.removeAt(i)
            i--
        } else if (stack[i] == "/") {
            stack[i - 1] = (stack[i - 1].toInt() / stack[i + 1].toInt()).toString()
            stack.removeAt(i)
            stack.removeAt(i)
            i--
        }
        i++
    }
    println(stack)
    i = 0
    while (true) {
        if (stack.getOrNull(i) == null) {
            break
        }
        if (stack[i] == "+") {
            stack[i - 1] = (stack[i - 1].toInt() + stack[i + 1].toInt()).toString()
            stack.removeAt(i)
            stack.removeAt(i)
            i--
        } else if (stack[i] == "-") {
            stack[i - 1] = (stack[i - 1].toInt() - stack[i + 1].toInt()).toString()
            stack.removeAt(i)
            stack.removeAt(i)
            i--
        }
        i++
    }
    return stack.first().toInt()
}