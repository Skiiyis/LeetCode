import java.util.*

/**
 * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。

注意：
总人数少于1100人。

示例

输入:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

输出:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */
fun main(args: Array<String>) {
    println(
        reconstructQueue(
            arrayOf(
                intArrayOf(
                    7, 0
                ), intArrayOf(
                    4, 4
                ), intArrayOf(
                    7, 1
                ), intArrayOf(
                    5, 0
                ), intArrayOf(
                    6, 1
                ), intArrayOf(
                    5, 2
                )
            )
        ).toList().map { it.toList() }
    )
}

/**
 * 先最高的按前面人数排序，
 * 后面矮的则插入到他前面有的人数的索引位置
 */
fun reconstructQueue(people: Array<IntArray>): Array<IntArray> {
    val ans = MutableList(0) { IntArray(2) }
    Arrays.sort(people) { o1, o2 ->
        if (o1[0] != o2[0]) o2[0] - o1[0] else o1[1] - o2[1]
    }
    people.forEach {
        ans.add(it[1], it)
    }
    return ans.toTypedArray()
}