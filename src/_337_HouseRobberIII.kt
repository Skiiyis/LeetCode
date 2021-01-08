/**
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。

计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。

示例 1:

输入: [3,2,3,null,3,null,1]

3
/ \
2   3
\   \
3   1

输出: 7
解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
示例 2:

输入: [3,4,5,1,3,null,1]

     3
/ \
4   5
/ \   \
1   3   1

输出: 9
解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 */
fun main(args: Array<String>) {
    println(
        rob(
            TreeNode(3).also {
                it.left = TreeNode(4).also {
                    it.left = TreeNode(1)
                    it.right = TreeNode(3)
                }
                it.right = TreeNode(5).also {
                    it.right = TreeNode(1)
                }
            }
        )
    )
}

/**
 * 树遍历，会超时
 * +记忆集，通过
 */
fun rob(
    root: TreeNode?,
    canRob: Boolean = true,
    memoCanRob: HashMap<TreeNode, Int> = HashMap(),
    memoCanNotRob: HashMap<TreeNode, Int> = HashMap()
): Int {
    if (root == null) return 0
    if (canRob && memoCanRob.containsKey(root)) return memoCanRob[root]!!
    if (!canRob && memoCanNotRob.containsKey(root)) return memoCanNotRob[root]!!
    val robLeft = rob(root.left, true, memoCanRob, memoCanNotRob)
    val notRobLeft = rob(root.left, false, memoCanRob, memoCanNotRob)
    val robRight = rob(root.right, true, memoCanRob, memoCanNotRob)
    val notRobRight = rob(root.right, false, memoCanRob, memoCanNotRob)
    return if (!canRob) {
        (robLeft + robRight).also {
            memoCanNotRob[root] = it
        }
    } else {
        Math.max(
            robLeft + robRight,
            notRobLeft + notRobRight + root.`val`
        ).also {
            memoCanRob[root] = it
        }
    }
}