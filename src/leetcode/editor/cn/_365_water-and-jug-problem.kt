//有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？ 
//
// 如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。 
//
// 你允许： 
//
// 
// 装满任意一个水壶 
// 清空任意一个水壶 
// 从一个水壶向另外一个水壶倒水，直到装满或者倒空 
// 
//
// 示例 1: (From the famous "Die Hard" example) 
//
// 输入: x = 3, y = 5, z = 4
//输出: True
// 
//
// 示例 2: 
//
// 输入: x = 2, y = 6, z = 5
//输出: False
// 
// Related Topics 数学 
// 👍 241 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * 图的遍历，图的入口即两个水壶都为0的情况。图的遍历可以通过以下几种方式
 * 1.壶1倒空
 * 2.壶2倒空
 * 3.壶1中所有水倒入壶2直到壶2满或者壶1空
 * 4.壶2中所有水倒入壶1直到壶1满或者壶2空
 * 5.壶1倒满
 * 6.壶2倒满
 * 需要记录已经遍历过的状态
 */
fun canMeasureWater(x: Int, y: Int, z: Int): Boolean {
    val memo = HashSet<String>()
    val stack = ArrayList<Pair<Int, Int>>()
    stack.add(0 to 0)
    while (stack.isNotEmpty()) {
        val water = stack.removeAt(0)
        if (water.first + water.second == z) return true
        if (memo.contains("${water.first}-${water.second}")) {
            continue
        }
        memo.add("${water.first}-${water.second}")
        stack.add(0 to water.second)
        stack.add(water.first to 0)
        stack.add(x to water.second)
        stack.add(water.first to y)
        val total = water.first + water.second
        //壶2倒入壶1
        if (total > x) {
            stack.add(x to total - x)
        } else {
            stack.add(total to 0)
        }
        //壶1倒入壶2
        if (total > y) {
            stack.add(total - y to y)
        } else {
            stack.add(0 to total)
        }
    }
    return false
}
//leetcode submit region end(Prohibit modification and deletion)
