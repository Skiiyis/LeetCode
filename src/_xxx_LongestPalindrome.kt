import java.lang.Exception

/**
 * 求最长回文子串的三个方法: 中心扩展，动态规划，马拉车法
 */
fun longestPalindromeInString(s: String) {
    var centerIndex = 0
    var ans = ""
    while (centerIndex < s.length * 2) { // abc
        // centerIndex = 0 ,left = 0 , right = 0
        // centerIndex = 1 ,left = 0 , right = 1
        // centerIndex = 2 ,left = 1 , right = 1
        // centerIndex = 3 ,left = 1 , right = 2

        var leftIndex = centerIndex / 2
        var rightIndex = if (centerIndex % 2 == 0) leftIndex else leftIndex + 1
        while (leftIndex >= 0 && rightIndex < s.length && s[leftIndex] == s[rightIndex]) {
            leftIndex--
            rightIndex++
        }
        ans = s.substring(++leftIndex..--rightIndex).let {
            if (it.length > ans.length) {
                it
            } else {
                ans
            }

            
        }
        centerIndex++
    }
    println("origin str: $s, longest palindrome: $ans")
}

fun longestPalindromeInClipString(s: String) {
    var centerIndex = 0
    var ans = ""
    var ansRange: IntRange? = null

    while (centerIndex < s.length * 2) { // abc
        //向左搜索
        //中心扩展方法的左右指针起始位置
        var leftIndex = centerIndex / 2
        var rightIndex = if (centerIndex % 2 == 0) leftIndex else leftIndex + 1
        var clipRange: IntRange? = null
        while (leftIndex >= 0 && rightIndex < s.length) {
            //向左 or 向右 寻找可以组成回文子串的下一个字符
            if (s[leftIndex] != s[rightIndex]) {
                if (clipRange == null) {
                    clipRange = clipLeft(leftIndex, rightIndex, s)
                    if (clipRange == null) {
                        //向左搜索不到可以继续组成回文字串的字符
                        break
                    } else {
                        //搜索到了下一个可以继续组成回文字符串的字符，继续寻找最长的回文子串
                        leftIndex = clipRange.start - 1
                    }
                } else {
                    //已经搜索过一次，直接跳出获取答案
                    break
                }
            }
            leftIndex--
            rightIndex++
        }
        s.substring(++leftIndex..--rightIndex).let {
            if (clipRange == null) {
                it
            } else {
                it.replaceFirst(s.substring(clipRange!!), "")
            }
        }.let {
            //println("searchLeft --> centerI: $centerIndex, subStr:[${s.substring(leftIndex..rightIndex)}], ans: [$it], clipRange: $clipRange")
            if (it.length > ans.length) {
                ansRange = clipRange
                ans = it
            }
        }

        //向右搜索
        //中心扩展方法的左右指针起始位置
        leftIndex = centerIndex / 2
        rightIndex = if (centerIndex % 2 == 0) leftIndex else leftIndex + 1
        clipRange = null
        while (leftIndex >= 0 && rightIndex < s.length) {
            //向左 or 向右 寻找可以组成回文子串的下一个字符
            if (s[leftIndex] != s[rightIndex]) {
                if (clipRange == null) {
                    clipRange = clipRight(leftIndex, rightIndex, s)
                    if (clipRange == null) {
                        //向右搜索不到可以继续组成回文字串的字符
                        break
                    } else {
                        //搜索到了下一个可以继续组成回文字符串的字符，继续寻找最长的回文子串
                        rightIndex = clipRange.endInclusive + 1
                    }
                } else {
                    //已经搜索过一次，直接跳出获取答案
                    break
                }
            }
            leftIndex--
            rightIndex++
        }
        s.substring(++leftIndex..--rightIndex).let {
            if (clipRange == null) {
                it
            } else {
                it.replaceFirst(s.substring(clipRange), "")
            }
        }.let {
            println("searchRight--> centerI: $centerIndex, subStr:[${s.substring(leftIndex..rightIndex)}], ans: [$it], clipRange: $clipRange")
            if (it.length > ans.length) {
                ansRange = clipRange
                ans = it
            }
        }
        centerIndex++
    }
    println("origin str: $s, longest palindrome: $ans, clip range is $ansRange")
}

fun clipLeft(leftIndex: Int, rightIndex: Int, s: String): IntRange? {
    if (s[rightIndex] == s[leftIndex]) {
        throw Exception("could not need clip")
    }
    for (left in leftIndex - 1 downTo 0) {
        if (s[left] == s[rightIndex]) {
            return left + 1..leftIndex
        }
    }
    return null
}

fun clipRight(leftIndex: Int, rightIndex: Int, s: String): IntRange? {
    if (s[leftIndex] == s[rightIndex]) {
        throw Exception("could not need clip")
    }
    for (right in rightIndex + 1 until s.length) {
        if (s[right] == s[leftIndex]) {
            return rightIndex until right
        }
    }
    return null
}

fun main(args: Array<String>) {
    longestPalindromeInClipString("ababcabacba")
}