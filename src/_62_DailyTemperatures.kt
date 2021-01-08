import java.util.*

/**
 * 根据每日 气温 列表，请重新生成一个列表，对应位置的输出是需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。

例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。

提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 */
fun main(args: Array<String>) {
    println(
        dailyTemperatures(
            intArrayOf(
                73, 74, 75, 71, 69, 72, 76, 73
            )
        ).toList()
    )
}

/**
 * 用一个栈来保存还没找到新的高温天气的气温索引
 */
fun dailyTemperatures(T: IntArray): IntArray {
    val tempStack = Stack<Int>()
    val dailyArray = IntArray(T.size)
    T.forEachIndexed { index, it ->
        while (tempStack.isNotEmpty() && T[tempStack.peek()] < it) {
            val e = tempStack.pop()
            dailyArray[e] = index - e
        }
        tempStack.push(index)
    }
    while (tempStack.isNotEmpty()) {
        dailyArray[tempStack.pop()] = 0
    }
    return dailyArray
}