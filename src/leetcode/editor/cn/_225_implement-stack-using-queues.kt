import com.sun.jmx.remote.internal.ArrayQueue
import java.util.*

//使用队列实现栈的下列操作：
//
// 
// push(x) -- 元素 x 入栈 
// pop() -- 移除栈顶元素 
// top() -- 获取栈顶元素 
// empty() -- 返回栈是否为空 
// 
//
// 注意: 
//
// 
// 你只能使用队列的基本操作-- 也就是 push to back, peek/pop from front, size, 和 is empty 这些操作是合
//法的。 
// 你所使用的语言也许不支持队列。 你可以使用 list 或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。 
// 你可以假设所有操作都是有效的（例如, 对一个空的栈不会调用 pop 或者 top 操作）。 
// 
// Related Topics 栈 设计 
// 👍 257 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class MyStack() {

    /** Initialize your data structure here. */
    val queue1: Queue<Int> = LinkedList<Int>()
    val queue2: Queue<Int> = LinkedList<Int>()

    /** Push element x onto stack. */
    fun push(x: Int) {
        val emptyQueue = if (queue1.isEmpty()) queue1 else queue2
        val notEmptyQueue = if (queue1.isNotEmpty()) queue1 else queue2
        emptyQueue.offer(x)
        while (notEmptyQueue.isNotEmpty()) {
            emptyQueue.offer(notEmptyQueue.poll())
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    fun pop(): Int {
        val notEmptyQueue = if (queue1.isNotEmpty()) queue1 else queue2
        return notEmptyQueue.poll()
    }

    /** Get the top element. */
    fun top(): Int {
        val notEmptyQueue = if (queue1.isNotEmpty()) queue1 else queue2
        return notEmptyQueue.peek()
    }

    /** Returns whether the stack is empty. */
    fun empty(): Boolean {
        val notEmptyQueue = if (queue1.isNotEmpty()) queue1 else queue2
        return notEmptyQueue.isEmpty()
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * var obj = MyStack()
 * obj.push(x)
 * var param_2 = obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.empty()
 */
//leetcode submit region end(Prohibit modification and deletion)
