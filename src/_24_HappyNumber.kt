/**
 *编写一个算法来判断一个数是不是“快乐数”。

一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是无限循环但始终变不到 1。如果可以变为 1，那么这个数就是快乐数。

示例:

输入: 19
输出: true
解释:
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
 */
fun main(args: Array<String>) {
    println(isHappy(3))
}

fun isHappy(n: Int, history: MutableList<Int> = ArrayList()): Boolean {
    if (history.contains(n)) {
        return false
    }
    var t = n
    val arr = ArrayList<Int>()
    do {
        arr.add(t % 10)
        t /= 10
    } while (t != 0)
    var sum = 0
    for (i in arr) {
        sum += i * i
    }
    return if (sum == 1) {
        true
    } else {
        isHappy(sum, history.also { it.add(n) })
    }
}