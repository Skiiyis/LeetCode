import java.lang.StringBuilder
import java.util.*

/**
 * 给定一个经过编码的字符串，返回它解码后的字符串。

编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。

你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。

此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。

 

示例 1：

输入：s = "3[a]2[bc]"
输出："aaabcbc"
示例 2：

输入：s = "3[a2[c]]"
输出："accaccacc"
示例 3：

输入：s = "2[abc]3[cd]ef"
输出："abcabccdcdcdef"
示例 4：

输入：s = "abc3[cd]xyz"
输出："abccdcdcdxyz"
 */
fun main(args: Array<String>) {
    println(
        decodeString("3[a]2[bc]")
    )
}

fun decodeString2(s: String): String {
    if (s.isEmpty()) return s
    val queue = ArrayDeque<String>()
    s.toCharArray().forEach {
        when {
            it == '[' -> {
                queue.push("[")
            }
            it == ']' -> {
                val strs = queue.pop()
                queue.pop()
                val times = queue.pop().toInt()
                val sb = StringBuilder()
                for (i in 0 until times) {
                    sb.append(strs)
                }
                if (queue.isNotEmpty() && queue.peek() != "[") {
                    queue.push("${queue.pop()}$sb")
                } else {
                    queue.push(sb.toString())
                }
            }
            it.isDigit() -> when {
                queue.isEmpty() -> queue.push(it.toString())
                //上一个值不是数字，直接push
                queue.peek().toIntOrNull() == null -> queue.push(it.toString())
                //上一个值是数字，加在上一个数字后面
                else -> queue.push("${queue.pop()}$it")
            }
            else -> when {
                queue.isEmpty() -> queue.push(it.toString())
                //上一个值是数字，直接push
                (queue.peek().toIntOrNull() != null || queue.peek() == "[") -> queue.push(it.toString())
                //上一个值不是数字，加在上一个字符串后
                else -> queue.push("${queue.pop()}$it")
            }
        }
    }
    return queue.reduce { acc, s -> "$acc$s" }
}

fun decodeString(s: String): String {
    val rightIndex = s.indexOfFirst { it == ']' }
    if (rightIndex < 0) return s
    val leftIndex = s.substring(0, rightIndex + 1).indexOfLast { it == '[' }
    var timeIndex = leftIndex - 1
    while (timeIndex >= 0 && s[timeIndex].isDigit()) {
        timeIndex--
    }
    val times = s.substring(timeIndex + 1, leftIndex).toInt()
    val sbStr = s.substring(leftIndex + 1, rightIndex)
    val sb = StringBuilder()
    repeat(times) {
        sb.append(sbStr)
    }
    return decodeString(
        s.replaceRange(
            timeIndex + 1, rightIndex + 1, sb
        )
    )
}

