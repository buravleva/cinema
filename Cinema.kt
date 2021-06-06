package cinema
var cinema = arrayOf<Array<String>>()
var tickets10 = 0
var tickets8 = 0
var currentIncome = 0

fun main() {
    println("Enter the number of rows:")
    val rows = readLine()!!.toInt()
    println("Enter the number of seats in each row:")
    val seats = readLine()!!.toInt()

    for (i in 1..rows) {
        var r = arrayOf<String>()
        for (j in 1..seats) {
            r += "S"
        }
        cinema += r
    }
    loop@ while (true) {
        println()
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")

        val action = readLine()!!.toInt()

        when (action) {
            1 -> {
                println("Cinema:")
                printCinema(cinema, rows, seats)
                println()
            }
            2 -> println(buyTicket(rows, seats))
            3 -> stat(rows, seats)
            0 -> break@loop
        }
    }
}

fun buyTicket(rows: Int, seats: Int): String {
    var error = false
    var message = ""
    do {
        println()
        println("Enter a row number:")
        val row = readLine()!!.toInt()
        println("Enter a seat number in that row:")
        val seat = readLine()!!.toInt()
        println()

        if (row > rows || seat > seats) {
            error = true
            println("Wrong input!")
        } else if (cinema[row - 1][seat - 1] == "B") {
            error = true
            println("That ticket has already been purchased!")
        } else {
            //Book the seat
            cinema[row - 1][seat - 1] = "B"

            //Count and output ticket price
            message = "Ticket price:$${price(rows, seats, row)}"
            error = false
        }
    } while (error)
    return message
}

fun price(rows: Int, seats: Int, row: Int): Int{
    var result = 0
    if (rows * seats <= 60 || rows / 2 >= row) {
        result = 10
        tickets10++
        currentIncome += 10
    } else  {
        result = 8
        tickets8++
        currentIncome += 8
    }
    return result
}

fun printCinema(cinema: Array<Array<String>>, rows: Int, seats: Int) {
    var r1 = 1
    print("  ")
    for (s1 in 1..seats) {
       print("$s1 ")
    }
    println()
    for (array in cinema) {
        print("$r1 ")
        r1 ++
        for (value in array) {
            print("$value ")
        }
        println()
    }
}

fun stat(rows: Int, seats: Int) {
    val totalSeats: Int = rows * seats
    var totalIncome: Int = 0
    val percentage: Double = ((tickets10 + tickets8) * 100.0 / totalSeats)
    if (totalSeats <= 60 ) {
        totalIncome = totalSeats * 10
    } else {
        totalIncome = rows / 2 * seats * 10 + (rows - rows / 2) * seats * 8
    }
    println()
    println("Number of purchased tickets: ${tickets10 + tickets8}")
    println("Percentage: ${"%.2f".format(percentage)}%")
    println("Current income: $$currentIncome")
    println("Total income: $$totalIncome")
}
