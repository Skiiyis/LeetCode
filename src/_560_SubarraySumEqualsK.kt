/**
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。

示例 1 :

输入:nums = [1,1,1], k = 2
输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
说明 :

数组的长度为 [1, 20,000]。
数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 */
fun main(args: Array<String>) {
    println(
        subarraySum2(
            intArrayOf(
                1, 2, 3
            ), 3
        )
    )
}

/**
 * 长度为l的数组总共有(1+l)*l/2的连续子数组，这些连续子数组求等于k的个数即可
 * 会超时
 */
fun subarraySum(nums: IntArray, k: Int): Int {
    val subArraySums = mutableListOf<Int>()
    val ans = HashMap<Int, Int>()
    nums.forEach { num ->
        subArraySums.map {
            (it + num).also { ans[it] = ans.getOrElse(it) { 0 } + 1 }
        }.also {
            subArraySums.clear()
            subArraySums.addAll(it)
        }
        subArraySums.add(num)
        ans[num] = ans.getOrElse(num) { 0 } + 1
    }
    return ans.getOrElse(k) { 0 }
}

/**
 * 前缀和
 * 假设pre[i]代表从[0,i]的前缀和, 可以推知若pre[j]-pre[i] == k,则从[i,j]的前缀和为k
 * ans代表前缀和出现的次数，kv分别为前缀和以及其出现的次数
 * 从左到右遍历，将每一次出现的前缀和放入表中，若出现pre[j]-k有值，则计算一次前缀和为k的连续子数组
 */
fun subarraySum2(nums: IntArray, k: Int): Int {
    val preMap = HashMap<Int, Int>()
    var pre = 0
    var count = 0
    preMap[0] = 1
    nums.forEach { num ->
        pre += num
        if (preMap.containsKey(pre - k)) {
            count += preMap.getOrDefault(pre - k, 0)
        }
        preMap[pre] = preMap.getOrDefault(pre, 0) + 1
    }
    return count
}

