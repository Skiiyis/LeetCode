import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

/**
 *你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。

在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]

给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？



示例 1:

输入: 2, [[1,0]]
输出: true
解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
示例 2:

输入: 2, [[1,0],[0,1]]
输出: false
解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。


提示：

输入的先决条件是由 边缘列表 表示的图形，而不是 邻接矩阵 。详情请参见图的表示法。
你可以假定输入的先决条件中没有重复的边。
1 <= numCourses <= 10^5
 */
fun main(args: Array<String>) {
    println(
        findOrder(
            2,
            arrayOf(
                intArrayOf(
                    0, 1
                )
            )
        ).toList()
    )
}

/**
 * 拓扑排序，先找到入度为0的节点，再删除入度为0的节点，新生成的入度为0的节点递归此操作
 * 直到没有入度为0的节点为止
 */
fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
    val courseSet = HashSet<Int>()
    for (course in 0 until numCourses) {
        courseSet.add(course)
    }
    var requireCourse = prerequisites.toList()
    val order = ArrayList<Int>()
    while (true) {
        val find0 = courseSet.minus(
            requireCourse.map { it[0] }.toSet()
        )
        for (i in find0) {
            if (!order.contains(i)) {
                order.add(i)
            }
        }
        val filterRequire = prerequisites.filter { !find0.contains(it[1]) }
        if (filterRequire.size == requireCourse.size) break
        requireCourse = filterRequire
    }
    return if (!requireCourse.isEmpty()) {
        intArrayOf()
    } else {
        order.toIntArray()
    }
}