import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.time.measureTime
import kotlin.random.Random
import kotlin.time.Duration
import java.time.LocalDateTime
import java.time.format.TextStyle
import java.util.Locale

// 1
fun task1(){
    val currentDate = LocalDate.now()
    val currentTime = LocalTime.now()
    val formattedDateTime = "${currentDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))} ${currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"))}"
    println(formattedDateTime)
}

// 2
fun task2(date1: LocalDate, date2: LocalDate){
    println(
        when{
            date1.isAfter(date2) -> "Первая дата больше второй"
            date1.isBefore(date2) -> "Вторая дата больше первой"
            else -> "Даты равны"
        }
    )
}

// 3
fun task3(){
    val currentDate = LocalDate.now()
    val newYear = LocalDate.of(currentDate.year + 1, 1, 1)
    val daysUntilNewYear = ChronoUnit.DAYS.between(currentDate, newYear)
    println("До Нового года осталось $daysUntilNewYear дней")
}

// 4
fun task4(year: Int) : Boolean{
    return Year.of(year).isLeap
}

// 5
fun task5(yearMonth: YearMonth) : Int{
    val weekends = (1..yearMonth.lengthOfMonth())
        .count{ day ->
            val date = yearMonth.atDay(day)
            date.dayOfWeek == DayOfWeek.SATURDAY || date.dayOfWeek == DayOfWeek.SUNDAY
        }
    return weekends
}

// 6
fun task6(){
    val time = measureTime {
        for (i in 1..1000000) {
        }
    }
    println("Время выполнения: ${time / 1000000}")
}

// 7
fun task7(dateStr: String){
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    val date = LocalDate.parse(dateStr, formatter)
    val newDate = date.plusDays(10)
    val outputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
    println("Дата + 10 дней: ${newDate.format(outputFormatter)}")
}

// 8
fun task8(dateTime: LocalDateTime): LocalDateTime {
    return dateTime.atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.of("Europe/Moscow")).toLocalDateTime()
}

// 9
fun task9(birthDate: LocalDate) : Int {
    return Period.between(birthDate, LocalDate.now()).years
}

// 10
fun task10(yearMonth: YearMonth){
    for (day in 1..yearMonth.lengthOfMonth()){
        val date = yearMonth.atDay(day)
        val isWeekend = date.dayOfWeek == DayOfWeek.SATURDAY || date.dayOfWeek == DayOfWeek.SUNDAY
        println("${date.dayOfMonth} - ${if (isWeekend) "Выходной" else "Рабочий день"}")
    }
}

// 11
fun task11(date1: LocalDate, date2: LocalDate) : LocalDate{
    val daysBetween = ChronoUnit.DAYS.between(date1, date2)
    return date1.plusDays(Random.nextLong(daysBetween + 1))
}

// 12
fun task12(dateTime: LocalDateTime) {
    val now = LocalDateTime.now()
    val hours = ChronoUnit.HOURS.between(now, dateTime)
    val minutes = ChronoUnit.MINUTES.between(now, dateTime) % 60
    val seconds = ChronoUnit.SECONDS.between(now, dateTime) % 60
    println("Осталось: $hours часов, $minutes минут, $seconds секунд")
}

// 13
fun task13(start: LocalDateTime, end: LocalDateTime) : Long{
    var workingHours = 0L
    var current = start
    while(current.isBefore(end)){
        if (current.dayOfWeek != DayOfWeek.SATURDAY && current.dayOfWeek != DayOfWeek.SUNDAY){
            workingHours++
        }
        current = current.plusHours(1)
    }
    return workingHours
}

// 14
fun task14(date: LocalDate, locale: Locale): String{
    return date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", locale))
}

// 15
fun task15(date: LocalDate): String{
    return date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale("ru"))
}

fun main() {
    while (true){
        print("Выберите задание (1-15, 0 - выход): ")
        when (readLine()?.toInt()){
            0 -> {
                print("Завершение работы")
                return
            }
            1 -> task1()
            2 -> {
                print("Введите первую дату в формате yyyy-MM-dd: ")
                val date1 = LocalDate.parse(readLine())
                print("Введите вторую дату в формате yyyy-MM-dd: ")
                val date2 = LocalDate.parse(readLine())
                task2(date1, date2)
            }
            3 -> task3()
            4 -> {
                print("Введите год: ")
                val year = readLine()?.toInt() ?: 0
                println(task4(year))
            }
            5 -> {
                print("Введите год и месяц (yyyy-MM): ")
                val yearMonth = YearMonth.parse(readLine())
                println(task5(yearMonth))
            }
            6 -> task6()
            7 -> {
                print("Введите дату в формате dd-MM-yyyy: ")
                val dateStr = readLine()!!
                task7(dateStr)
            }
            8 -> {
                print("Введите дату и время в формате yyyy-MM-dd HH:mm:ss (UTC): ")
                val utcDateTime = LocalDateTime.parse(readLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                println("Время в Москве: ${task8(utcDateTime)}")
            }
            9 -> {
                println("Введите дату рождения в формате yyyy-MM-dd:")
                val birthDate = LocalDate.parse(readLine())
                println("Возраст: ${task9(birthDate)} лет")
            }
            10 -> {
                print("Введите год и месяц (yyyy-MM): ")
                val yearMonth = YearMonth.parse(readLine())
                task10(yearMonth)
            }
            11 -> {
                print("Введите начальную дату в формате yyyy-MM-dd: ")
                val date1 = LocalDate.parse(readLine())
                print("Введите конечную дату в формате yyyy-MM-dd: ")
                val date2 = LocalDate.parse(readLine())
                println(task11(date1, date2))
            }
            12 -> {
                print("Введите дату и время события в формате yyyy-MM-dd HH:mm:ss: ")
                val dateTime = LocalDateTime.parse(readLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                task12(dateTime)
            }
            13 -> {
                print("Введите начало рабочего дня в формате yyyy-MM-dd HH:mm:ss: ")
                val start = LocalDateTime.parse(readLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                print("Введите конец рабочего дня в формате yyyy-MM-dd HH:mm:ss: ")
                val end = LocalDateTime.parse(readLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                println("Рабочих часов: ${task13(start, end)}")
            }
            14 -> {
                print("Введите  дату в формате yyyy-MM-dd: ")
                val date = LocalDate.parse(readLine())
                print("Введите локаль (пример: ru, en): ")
                val locale = Locale(readLine()!!)
                println("Дата с учётом локали: ${task14(date, locale)}")
            }
            15 -> {
                print("Введите  дату в формате yyyy-MM-dd: ")
                val date = LocalDate.parse(readLine())
                println("День недели: ${task15(date)}")
            }
            else -> "Неверный номер задания!"
        }
    }
}