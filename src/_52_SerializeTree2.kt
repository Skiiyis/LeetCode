import java.lang.Math.*
import java.lang.StringBuilder

/**
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。

请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。

示例:

你可以将以下二叉树：

1
/ \
2   3
/ \
4  5

序列化为 "[1,2,3,null,null,4,5]"
提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。

说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
 */
fun main(args: Array<String>) {
    val node = TreeNode(1).also {
        it.left = TreeNode(2)
        it.right = TreeNode(3).also {
            it.left = TreeNode(4)
            it.right = TreeNode(5)
        }
    }
    val c = Codec2()
    println(c.serialize(node))
}

class Codec2 {
    /**
     * 层序遍历
     */
    fun serialize(root: TreeNode?): String {
        if (root == null) {
            return "[]"
        }
        val values: ArrayList<TreeNode?> = ArrayList()
        var index = 0
        var needContinue = true
        values.add(root)
        while (needContinue) {
            needContinue = false
            val nextIndex = values.size
            for (i in index until values.size) {
                val node = values[i]
                values.add(node?.left)
                values.add(node?.right)
                if (node?.left != null || node?.right != null) {
                    needContinue = true
                }
            }
            index = nextIndex
        }
        return values.subList(0, index).map { it?.`val` }.toString()
    }

    fun deserialize(data: String): TreeNode? {
        val treeValues = data.substring(1, data.length - 1)
            .split(",")
            .map { it.trim().toIntOrNull() }
            .toTypedArray()
        return deSerializeTree(treeValues = treeValues)
    }

    private fun deSerializeTree(index: Int = 0, treeValues: Array<Int?>): TreeNode? {
        val value = treeValues.getOrNull(index) ?: return null
        return TreeNode(value).also {
            it.left = deSerializeTree(index * 2 + 1, treeValues)
            it.right = deSerializeTree(index * 2 + 2, treeValues)
        }
    }
}