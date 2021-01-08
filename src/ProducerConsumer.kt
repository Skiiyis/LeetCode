import java.lang.Exception

fun main(args: Array<String>) {
    val consumer = Consumer<String>().also { it.start() }
    for (i in 1..20) {
        object : Thread() {
            override fun run() {
                consumer.produceData("I'm a data @ $i")
            }
        }.start()
    }
}

class Consumer<T> : Thread() {

    private val dataList = ArrayList<T>()
    private var exit = false
    private val lock = dataList as java.lang.Object

    fun produceData(data: T) {
        try {
            synchronized(lock) {
                dataList.add(data)
                lock.notify()
            }
        } catch (e: Exception) {
            //println("exception at produce")
            e.printStackTrace()
        }
    }

    fun exit() {
        try {
            synchronized(lock) {
                this.exit = true
                lock.notify()
            }
        } catch (e: Exception) {
            //println("exception at exit")
            e.printStackTrace()
        }
    }

    override fun run() {
        while (!exit) {
            println("loop to consume data")
            try {
                synchronized(lock) {
                    while (dataList.isNotEmpty()) {
                        val data = dataList.removeAt(0)
                        //consume data
                        println("consume data: $data")
                    }
                    println("no data could consume")
                    lock.wait()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        println("exit consumer")
    }
}