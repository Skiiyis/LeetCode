//输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构) 
//
// B是A的子结构， 即 A中有出现和B相同的结构和节点值。 
//
// 例如: 
//给定的树 A: 
//
// 3 
// / \ 
// 4 5 
// / \ 
// 1 2 
//给定的树 B： 
//
// 4 
// / 
// 1 
//返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。 
//
// 示例 1： 
//
// 输入：A = [1,2,3], B = [3,1]
//输出：false
// 
//
// 示例 2： 
//
// 输入：A = [3,4,5,1,2], B = [4,1]
//输出：true 
//
// 限制： 
//
// 0 <= 节点个数 <= 10000 
// Related Topics 树 
// 👍 95 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
fun isSubStructure(A: TreeNode?, B: TreeNode?): Boolean {
    if (A == null && B == null) return false
    if ((A == null) xor (B == null)) return false
    return judgeSubStructure(A, B) || isSubStructure(A?.left, B) || isSubStructure(A?.right, B)
}

fun judgeSubStructure(A: TreeNode?, B: TreeNode?): Boolean {
    if (B == null) return true
    if (A == null) return false
    if (A?.`val` == B?.`val` && judgeSubStructure(A?.left, B?.left) && judgeSubStructure(A?.right, B?.right)) {
        return true
    }
    return false
}

/*fun main(args: Array<String>) {
    println(
        Solution().isSubStructure(
            TreeNode(10).also {
                it.left = TreeNode(12).also {
                    it.left = TreeNode(8)
                    it.right = TreeNode(3)
                }
                it.right = TreeNode(6).also {
                    it.left = TreeNode(11)
                }
            }, TreeNode(10).also {
                it.left = TreeNode(12).also {
                    it.left = TreeNode(8)
                }
                it.right = TreeNode(6)
            }
        )
    )
}*/

/*fun main(args: Array<String>) {
    println(
        Solution().isSubStructure(
            TreeNode(10).also {
                it.left = TreeNode(12).also {
                    it.left = TreeNode(8)
                    it.right = TreeNode(3)
                }
                it.right = TreeNode(6).also {
                    it.left = TreeNode(11)
                }
            }, TreeNode(10).also {
                it.left = TreeNode(12).also {
                    it.left = TreeNode(8)
                }
                it.right = TreeNode(6)
            }
        )
    )
}*/
//leetcode submit region end(Prohibit modification and deletion)
