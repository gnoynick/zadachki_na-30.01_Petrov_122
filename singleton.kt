// 1
class Database private constructor() {
    companion object {
        @Volatile
        private var instance: Database? = null

        fun getInstance(): Database{
            return instance ?: synchronized(this){
                instance ?: Database().also {
                    instance = it
                    println("Подключение к базе данных создано.")
                }
            }
        }
    }
}

// 2
class Logger private constructor() {
    companion object {
        @Volatile
        private var instance: Logger? = null

        fun getInstance(): Logger{
            return instance ?: synchronized(this){
                instance ?: Logger().also {
                    instance = it
                }
            }
        }
    }

    private val logs = mutableListOf<String>()

    fun addLog(message: String){
        logs.add(message)
    }

    fun printLogs() {
        logs.forEach { println(it) }
    }
}

// 3
enum class OrderStatus{
    NEW,
    IN_PROGRESS,
    DELIVERED,
    CANCELLED
}

class Order(val orderId: Int){
    private var status: OrderStatus = OrderStatus.NEW

    fun getStatus(): OrderStatus{
        return status
    }

    fun changeStatus(newStatus: OrderStatus){
        when (status){
            OrderStatus.NEW -> {
                if (newStatus == OrderStatus.IN_PROGRESS || newStatus == OrderStatus.CANCELLED) {
                    status = newStatus
                } else {
                    println("Невозможно изменить статус на $newStatus из текущего состояния $status")
                }
            }
            OrderStatus.IN_PROGRESS -> {
                if(newStatus == OrderStatus.DELIVERED || newStatus == OrderStatus.CANCELLED){
                    status = newStatus
                }
                else{
                    println("Невозможно изменить статус на $newStatus из текущего состояния $status")
                }
            }
            OrderStatus.DELIVERED -> {
                println("Невозможно изменить статус уже доставленного заказа")
            }
            OrderStatus.CANCELLED ->{
                println("Невозможно изменить статус отменённого заказа")
            }
        }
    }

    fun displayStatus(){
        println("Текущий статус заказа: $status")
    }
}

// 4
enum class Season{
    WINTER,
    SPRING,
    SUMMER_LOVE,
    AUTISM
}

fun getSeasonName(season: Season): String{
    when(season){
        Season.WINTER -> return "зима"
        Season.SPRING -> return  "весна"
        Season.SUMMER_LOVE -> return "лето"
        Season.AUTISM -> return "осень"
    }
}

fun main() {
    while (true) {
        print("Выберите задание (1-4, 0 - выход): ")
        when (readLine()?.toInt()) {
            0 -> {
                print("Завершение работы")
                return
            }
            1 -> {
                val db1 = Database.getInstance()
                val db2 = Database.getInstance()
                println(db1 === db2)
            }
            2 -> {
                val logger = Logger.getInstance()
                logger.addLog("Log message 1")
                logger.addLog("Log message 2")
                logger.printLogs()
            }
            3 -> {
                val order = Order(1)
                order.displayStatus()
                order.changeStatus(OrderStatus.IN_PROGRESS)
                order.displayStatus()
                order.changeStatus(OrderStatus.DELIVERED)
                order.displayStatus()
                order.changeStatus(OrderStatus.CANCELLED)
                order.displayStatus()
            }
            4 -> {
                val season1 = Season.WINTER
                val season2 = Season.SPRING
                val season3 = Season.SUMMER_LOVE
                val season4 = Season.AUTISM
                println(getSeasonName(season1))
                println(getSeasonName(season2))
                println(getSeasonName(season3))
                println(getSeasonName(season4))
            }
            else -> println("Неверный номер задания!")
        }
    }
}