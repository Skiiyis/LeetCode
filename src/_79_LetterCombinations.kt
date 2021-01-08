import java.lang.StringBuilder

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。


示例:

输入："23"
输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
说明:
尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
fun main(args: Array<String>) {
    println(letterCombinations(""))
}

//回溯算法
fun letterCombinations(digits: String): List<String> {
    if (digits.isEmpty()){
        return emptyList()
    }
    val letterMap = arrayOf(
        arrayOf(),
        arrayOf("a", "b", "c"),
        arrayOf("d", "e", "f"),
        arrayOf("g", "h", "i"),
        arrayOf("j", "k", "l"),
        arrayOf("m", "n", "o"),
        arrayOf("p", "q", "r", "s"),
        arrayOf("t", "u", "v"),
        arrayOf("w", "x", "y", "z")
    )
    val ans = ArrayList<String>()
    _79_letterCombinations(digits = digits, letterMap = letterMap, ans = ans)
    return ans
}

fun _79_letterCombinations(
    letterIndex: Int = 0,
    digits: String,
    letterMap: Array<Array<String>>,
    ans: ArrayList<String>,
    selected: StringBuilder = StringBuilder()
) {
    if (letterIndex == digits.length) {
        ans.add(selected.toString())
    }
    val letterStrings = letterMap.getOrElse(digits.getOrElse(letterIndex) { '1' } - '1') { emptyArray() }
    letterStrings.forEach {
        selected.append(it)
        _79_letterCombinations(
            letterIndex + 1,
            digits,
            letterMap,
            ans,
            selected
        )
        selected.deleteCharAt(selected.length - 1)
    }
}