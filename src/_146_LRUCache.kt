/**
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。

获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。

 

进阶:

你是否可以在 O(1) 时间复杂度内完成这两种操作？

 

示例:

LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // 返回  1
cache.put(3, 3);    // 该操作会使得关键字 2 作废
cache.get(2);       // 返回 -1 (未找到)
cache.put(4, 4);    // 该操作会使得关键字 1 作废
cache.get(1);       // 返回 -1 (未找到)
cache.get(3);       // 返回  3
cache.get(4);       // 返回  4
 */
fun main(args: Array<String>) {
    /*val cache = LRUCache(1)
    cache.put(2, 1)
    println(
        cache.get(2)
    )
    cache.put(3, 2)
    println(
        cache.get(2)
    )
    println(
        cache.get(3)
    )*/
    val cache = LRUCache(2)
    cache.put(2, 1)
    cache.put(3, 2)
    println(
        cache.get(3)
    )
    println(
        cache.get(2)
    )
    cache.put(4, 3)
    println(
        cache.get(2)
    )
    println(
        cache.get(3)
    )
    println(
        cache.get(4)
    )
    /*val cache = LRUCache(2)
    cache.put(1, 1)
    cache.put(2, 2)
    println(
        cache.get(1)
    )
    cache.put(3, 3)
    println(
        cache.get(2)
    )
    cache.put(4, 4)
    println(
        cache.get(1)
    )
    println(
        cache.get(3)
    )
    println(
        cache.get(4)
    )*/
}

//put需要O(1)的时间并且需要排序需要使用hash表和双向链表
//简单解法使用LinkedHashMap
class LRUCache(val capacity: Int) {

    class TwoWayLinkNode(val key: Int, var value: Int) {
        var next: TwoWayLinkNode? = null
        var prev: TwoWayLinkNode? = null
    }

    var size = 0
    var cache = HashMap<Int, TwoWayLinkNode>()
    var head: TwoWayLinkNode? = null
    var tail: TwoWayLinkNode? = null

    fun get(key: Int): Int {
        return getNode(key)?.value ?: -1
    }

    fun getNode(key: Int): TwoWayLinkNode? {
        val node = cache.getOrElse(key) { null }
        if (node != null) {
            if (node == head) return node
            //node从链表中丢弃自己并放到链表头部，即更新自己的访问时间
            val prev = node.prev
            val next = node.next
            if (next == null && prev != null) {
                tail = prev
            }
            //从链表中移除本节点
            prev?.next = next
            next?.prev = prev

            //提升自己到头节点
            head?.prev = node
            node.next = head
            node.prev = null
            head = node
        }
        return node
    }

    fun put(key: Int, value: Int) {
        val node = getNode(key)
        if (node != null) {
            node.value = value
        } else {
            TwoWayLinkNode(key, value).also {
                //新放入的节点放入到链表头部
                it.next = head
                head?.prev = it
                head = it
                if (tail == null) {
                    tail = it
                }
                cache[key] = it
            }
            size++
            if (size > capacity) {
                //超出容量，丢弃最久不使用的节点，即链表尾
                cache.remove(tail?.key)
                tail = tail?.prev
                tail?.next = null
                size--
            }
        }
    }
}

