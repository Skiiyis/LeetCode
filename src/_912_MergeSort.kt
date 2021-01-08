object MergeSort {

    @JvmStatic
    fun sortArray(nums: IntArray): IntArray {
        var arr1 = nums.sliceArray(0 until nums.size / 2)
        var arr2 = nums.sliceArray(nums.size / 2 until nums.size)
        println("arr1: ${arr1.toList()}, arr2: ${arr2.toList()}")
        arr1 = if (arr1.size <= 2) {
            sort(arr1)
        } else {
            sortArray(arr1)
        }
        arr2 = if (arr2.size <= 2) {
            sort(arr2)
        } else {
            sortArray(arr2)
        }
        println("arr1: ${arr1.toList()}, arr2: ${arr2.toList()}")
        return merge(arr1, arr2)
    }

    private fun sort(array: IntArray): IntArray {
        if (array.size == 2 && array[0] >= array[1]) {
            val temp = array[0]
            array[0] = array[1]
            array[1] = temp
        }
        return array
    }

    private fun merge(array1: IntArray, array2: IntArray): IntArray {
        val ans = IntArray(array1.size + array2.size)
        var index1 = 0
        var index2 = 0
        var aIndex = 0
        while (aIndex < ans.size) {
            val n1 = array1.getOrNull(index1)
            val n2 = array2.getOrNull(index2)
            when {
                n1 == null -> {
                    ans[aIndex] = n2!!
                    index2++
                }
                n2 == null -> {
                    ans[aIndex] = n1
                    index1++
                }
                n1 > n2 -> {
                    ans[aIndex] = n2
                    index2++
                }
                n1 <= n2 -> {
                    ans[aIndex] = n1
                    index1++
                }
            }
            aIndex++
        }
        println("ans: ${ans.toList()}")
        return ans
    }
}

fun main(args: Array<String>) {
    val test = intArrayOf(5, 2, 3, 1)
    println(MergeSort.sortArray(test).toList())
}