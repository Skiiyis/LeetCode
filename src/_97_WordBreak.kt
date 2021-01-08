/**
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。

说明：

拆分时可以重复使用字典中的单词。
你可以假设字典中没有重复的单词。
示例 1：

输入: s = "leetcode", wordDict = ["leet", "code"]
输出: true
解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
示例 2：

输入: s = "applepenapple", wordDict = ["apple", "pen"]
输出: true
解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
     注意你可以重复使用字典中的单词。
示例 3：

输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
输出: false
 */
fun main(args: Array<String>) {
    println(
        wordBreak(
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
            listOf("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa")
        )
    )
}

/**
 * 回溯算法？会超时。。
 */
fun wordBreak(s: String, wordDict: List<String>, memo: HashMap<String, Boolean> = HashMap()): Boolean {
    if (memo.containsKey(s)) return memo[s] ?: false
    if (wordDict.contains(s)) {
        println("s = ${s}, memo = ${memo}")
        memo[s] = true
        return true
    }
    wordDict.filter {
        s.startsWith(it)
    }.map {
        wordBreak(s.removePrefix(it), wordDict, memo)
    }.also {
        return if (it.isEmpty()) {
            false
        } else {
            it.reduce { acc, b -> acc || b }
        }.also {
            println("s = ${s}, memo = ${memo}")
            memo[s] = it
        }
    }
}