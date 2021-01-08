/**
 * 96. 不同的二叉搜索树
给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？

示例:

输入: 3
输出: 5
解释:
给定 n = 3, 一共有 5 种不同结构的二叉搜索树:

1         3     3      2      1
\       /     /      / \      \
3     2     1      1   3      2
/     /       \                 \
2     1         2                 3
 */
fun main(args: Array<String>) {
    println(
        numTrees(4)
    )
}

/**
 * 动规解法
 * n = 0, dp[0] = 1
 * n = 1, dp[1] = 1
 * n > 1, 二叉搜索树除了根节点可以给左右子树分配 n-1个节点，其中左右子树总共有 n2 总分配结果，
 * 确定了一个子树的分配节点数之后，二叉搜索树的种类为
 * dp[n] = dp[0] * dp[n-1] + dp[1] * dp[n-2] .... + dp [n-1] * dp [0]
 */
fun numTrees(n: Int): Int {
    val dp = IntArray(n + 1) { 0 }
    dp[0] = 1; dp[1] = 1
    for (i in 2..n) {
        for (left in 0 until i) {
            dp[i] += dp[left] * dp[i - left - 1]
        }
    }
    return dp[n]
}