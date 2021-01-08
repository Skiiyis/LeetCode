object QuickSort {

    @JvmStatic
    fun sortArray(arr: IntArray, start: Int = 0, end: Int = arr.size - 1): IntArray {
        if (start >= end) return arr
        var i = start
        var j = end
        var rightToLeft = true
        while (true) {
            if (rightToLeft) {
                //从右到左
                while (arr[j] >= arr[i] && j != i) {
                    j--
                }
                if (i == j) break
                swap(arr, i, j)
                rightToLeft = false
            } else {
                //从左到右
                while (arr[j] >= arr[i] && j != i) {
                    i++
                }
                if (i == j) break
                swap(arr, i, j)
                rightToLeft = true
            }
        }
        sortArray(arr, start, i - 1)
        sortArray(arr, i + 1, end)
        return arr
    }

    private fun swap(arr: IntArray, a: Int, b: Int) {
        val temp = arr[a]
        arr[a] = arr[b]
        arr[b] = temp
    }
}