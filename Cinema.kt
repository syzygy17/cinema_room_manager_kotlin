package cinema

fun showSeats(cinema: Array<Array<Char>>, rows: Int, seats: Int) {
    println("Cinema:")
    print(" ")
    for (i in 1..seats) {
        if (i == seats)
            print("$i\n")
        else
            print("$i ")
    }
    for (i in 1..rows) {
        print("$i ")
        for (j in 1..seats) {
            if (j == seats)
                print("${cinema[i - 1][j - 1]}\n")
            else
                print("${cinema[i - 1][j - 1]} ")
        }
    }
}

fun printMenu() {
    println("1. Show the seats")
    println("2. Buy a ticket")
    println("3. Statistics")
    println("0. Exit")
}

fun showStatistics(tickets: Int, income: Int, totalIncome: Int, all: Int) {
    val percentage = tickets.toDouble() / all * 100
    println("Number of purchased tickets: $tickets")
    println("Percentage: ${String.format("%.2f", percentage)}%")
    println("Current income: \$$income")
    println("Total income: \$$totalIncome")
}

fun main() {
    println("Enter the number of rows:")
    val rows = readLine()!!.toInt()
    println("Enter the number of seats in each row:")
    val seats = readLine()!!.toInt()

    var tickets = 0
    var income = 0
    val all = rows * seats
    val totalIncome = if (all < 60) {
        rows * seats * 10
    } else {
        val firstHalf = rows / 2
        val secondHalf = rows - firstHalf
        (firstHalf * 10 * seats) + (secondHalf * 8 * seats)
    }
    val cinemaArray = Array(rows) { Array(seats) { 'S' } }

    while (true) {
        printMenu()
        when (readLine()!!.toInt()) {
            1 -> showSeats(cinemaArray, rows, seats)
            2 -> {
                while (true) {
                    val ticketPrice: Int
                    println("Enter a row number:")
                    val rowNumber = readLine()!!.toInt()
                    println("Enter a seat number in that row:")
                    val seatNumber = readLine()!!.toInt()
                    if (rowNumber > rows || seatNumber > seats) {
                        println("Wrong input!")
                    } else if (cinemaArray[rowNumber - 1][seatNumber - 1] == 'B') {
                        println("That ticket has already been purchased!")
                    } else {
                        cinemaArray[rowNumber - 1][seatNumber - 1] = 'B'
                        ticketPrice = if (all < 60) {
                            10
                        } else {
                            if (rowNumber > 4)
                                8
                            else
                                10
                        }
                        income += ticketPrice
                        println("Ticket price: \$$ticketPrice")
                        tickets++
                        break
                    }
                }
            }
            3 -> showStatistics(tickets, income, totalIncome, all)
            0 -> break
        }
    }
}