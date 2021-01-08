import java.lang.Math.max

object HeapSort {

    //大顶堆：arr[i] >= arr[2i+1] && arr[i] >= arr[2i+2]

    @JvmStatic
    fun sortArray(nums: IntArray): IntArray {
        for (i in nums.size / 2 - 1 downTo 0) {
            var rootIndex = i
            while (true) {
                val swapIndex = adjustHeap(nums, rootIndex)
                if (swapIndex == rootIndex) break
                else rootIndex = swapIndex
            }
        }
        //println("list: ${nums.toList()}")
        for (end in nums.size - 1 downTo 0) {
            swap(nums, 0, end)
            var rootIndex = 0
            while (true) {
                val swapIndex = adjustHeap(nums, rootIndex, end - 1)
                if (swapIndex == rootIndex) break
                else rootIndex = swapIndex
            }
        }
        return nums
    }

    private fun adjustHeap(nums: IntArray, rootIndex: Int, endIndex: Int = nums.size - 1): Int {
        val root = nums[rootIndex]
        val left = if (rootIndex * 2 + 1 > endIndex) null else nums[rootIndex * 2 + 1]
        val right = if (rootIndex * 2 + 2 > endIndex) null else nums[rootIndex * 2 + 2]
        val max = max(max(root, left ?: root), right ?: root)
        return when (max) {
            left -> {
                swap(nums, rootIndex, rootIndex * 2 + 1)
                rootIndex * 2 + 1
            }
            right -> {
                swap(nums, rootIndex, rootIndex * 2 + 2)
                rootIndex * 2 + 2
            }
            else -> rootIndex
        }
    }

    fun swap(nums: IntArray, a: Int, b: Int) {
        val temp = nums[a]
        nums[a] = nums[b]
        nums[b] = temp
    }

}

fun main(args: Array<String>) {
    println(
        HeapSort.sortArray(
            intArrayOf(-4, 0, 7, 4, 9, -5, -1, 0, -7, -1)
        ).toList()
    )
}