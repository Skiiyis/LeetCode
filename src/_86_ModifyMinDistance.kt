/**
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。

你可以对一个单词进行如下三种操作：

插入一个字符
删除一个字符
替换一个字符
 

示例 1：

输入：word1 = "horse", word2 = "ros"
输出：3
解释：
horse -> rorse (将 'h' 替换为 'r')
rorse -> rose (删除 'r')
rose -> ros (删除 'e')
示例 2：

输入：word1 = "intention", word2 = "execution"
输出：5
解释：
intention -> inention (删除 't')
inention -> enention (将 'i' 替换为 'e')
enention -> exention (将 'n' 替换为 'x')
exention -> exection (将 'n' 替换为 'c')
exection -> execution (插入 'u')
 */
fun main(args: Array<String>) {
    println(
        minDistance(
            "horse",
            "ros"
        )
    )
}

/**
 * 动态规划来做
 * dp[i][j] 表示word1的前i个字符转换到word2的前j个字符需要 dp[i][j] 步
 *
 * 那么word1的前i个字符转换到word2的前j个字符有三种情况。
 * 1. 已经通过 dp[i-1][j-1] 步将 word1 的前i个字符转换为 word2 的前j个字符
 * 此时word1还有第i个字符需要变为word2的第j个字符，通过1次或0次替换操作可以实现
 * 当word1的第i个字符正好等于word2的第j个字符是为0次操作
 *  dp[i][j] = dp[i-1][j-1] + 1 or 0
 *
 * 2. 已经通过 dp[i][j-1] 步将 word1 的前i个字符转换为 word2 的前j-1个字符
 * 此时还需要添加word2的第j个字符即可转换为word2
 *  dp[i][j] = dp[i][j-1] + 1
 *
 * 3. 已经通过 dp[i-1][j] 步将 word1 的前i-1个字符转换为 word2 的前j个字符
 * 此时还需要删除word1的第i个字符即可转换为word2
 *  dp[i][j] = dp[i-1][j] + 1
 *
 * 最小步数即上面三种情况中的最小值。
 */
fun minDistance(word1: String, word2: String): Int {
    val dp = Array(word1.length + 1) { IntArray(word2.length + 1) }
    for (i in 0 until dp.size) {
        for (j in 0 until dp[0].size) {
            if (i == 0) {
                dp[i][j] = j
                continue
            }
            if (j == 0) {
                dp[i][j] = i
                continue
            }
            val lastOpIsReplace = dp[i - 1][j - 1] + if (word1[i - 1] == word2[j - 1]) 0 else 1
            val lastOpIsAdd = dp[i][j - 1] + 1
            val lastOpIsDel = dp[i - 1][j] + 1
            dp[i][j] = Math.min(lastOpIsReplace, Math.min(lastOpIsAdd, lastOpIsDel))
        }
    }
    return dp.last().last()
}