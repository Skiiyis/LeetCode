import java.util.concurrent.locks.ReentrantLock

object ProducerConsumer {

    val items = mutableListOf<Int>()
    val maxSize = 10
    val lock = ReentrantLock()
    val notFull = lock.newCondition()
    val notEmpty = lock.newCondition()

    class Producer {

        fun produce() {
            lock.lock()
            try {
                if (items.size < maxSize) {
                    items.add(0)
                    notEmpty.signal()
                    println("++ ${items.size}")
                }
                if (items.size == maxSize) {
                    notFull.await()
                }
            } finally {
                lock.unlock()
            }
        }
    }

    class Consumer {

        fun consume() {
            lock.lock()
            try {
                if (items.isNotEmpty()) {
                    items.removeAt(0)
                    notFull.signal()
                    println("-- ${items.size}")
                }
                if (items.isEmpty()) {
                    notEmpty.await()
                }
            } finally {
                lock.unlock()
            }
        }
    }
}

fun main(args: Array<String>) {
    Thread {
        val p = ProducerConsumer.Producer()
        while (true) {
            p.produce()
        }
    }.start()
    Thread {
        val c = ProducerConsumer.Consumer()
        while (true) {
            c.consume()
        }
    }.start()
}