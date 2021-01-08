import java.util.*
import kotlin.math.max

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

求在该柱状图中，能够勾勒出来的矩形的最大面积。

以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。

图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 */
fun main(args: Array<String>) {
    println(
        largestRectangleArea(
            intArrayOf(
                5, 4, 1, 2
            )
        )
    )
}

/**
 * 单调栈
 */
fun largestRectangleArea(heights: IntArray): Int {
    val stack = Stack<Int>()
    stack.push(-1)
    var maxArea = 0
    heights.forEachIndexed { index, i ->
        while (stack.peek() != -1 && i < heights[stack.peek()]) {
            maxArea = Math.max(maxArea, heights[stack.peek()] * (index - stack.peek() - 1))
            stack.pop()
        }
        stack.push(index)
    }
    while (stack.peek() != -1) {
        val index = stack.pop()
        maxArea = Math.max(maxArea, heights[index] * (heights.size - stack.peek() - 1))
    }
    return maxArea
}