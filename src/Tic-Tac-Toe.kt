import java.util.Scanner

fun print(cells: Array<CharArray>) {

    println("---------")
    for (i in 0 until 3) {
        print("|")
        for (j in 0 until 3) {
            print("  ")
            cells[i][j] = ' '
        }
        println(" |")
    }
    println("---------")
}

fun stateGame(cells: Array<CharArray>): String? {
    var countRows = 0
    var countColumns = 0
    var countMainDiagonal = 0
    var countDiagonal = 0
    var isX = false
    var isO = false
    var k = cells.size - 1
    var countX = 0
    var countO = 0

    for (i in cells.indices) {
        for (j in cells.indices) {
            countRows += cells[i][j].toInt()
            countColumns += cells[j][i].toInt()

            if (cells[i][j] == 'X') countX++ else if (cells[i][j] == 'O') countO++
        }
        countMainDiagonal += cells[i][i].toInt()
        countDiagonal += cells[k][i].toInt()

        if (countRows == 264 || countColumns == 264) {
            isX = true
        } else if (countRows == 237 || countColumns == 237) {
            isO = true
        }

        countRows = 0
        countColumns = 0
        k--
    }

    if (countMainDiagonal == 264 || countDiagonal == 264) {
        isX = true
    } else if (countMainDiagonal == 237 || countDiagonal == 237) {
        isO = true
    }

    val answer: String
    answer = when {
        isX -> "X wins"
        isO -> "O wins"
        countX + countO == 9 -> "Draw"
        else -> ""
    }
    return answer
}

fun currentCells(cells: Array<CharArray>, coordinates1: Char, coordinates2: Char, a: Int) {

    println("---------")
    for (i in 0 until 3) {
        print("|")
        for (j in 0 until 3) {
            if (i == coordinates1 - '1' && j == coordinates2 - '1' && a % 2 == 1) {
                print(" X")
                cells[i][j] = 'X'
            } else if (i == coordinates1 - '1' && j == coordinates2 - '1' && a % 2 == 0) {
                print(" O")
                cells[i][j] = 'O'
            } else {
                print(" ${cells[i][j]}")
            }
        }
        println(" |")
    }
    println("---------")
}

fun playingGame(cells: Array<CharArray>) {
    val scanner = Scanner(System.`in`)
    var a = 1

    while (scanner.hasNext()) {
        val coordinates = scanner.nextLine()!!.split(" ")
        val coordinates1 = coordinates[0][0]
        val coordinates2 = coordinates[1][0]

        if (coordinates1.isDigit() && coordinates2.isDigit()) {
            if (coordinates1 <= '3' && coordinates2 <= '3') {
                if (cells[coordinates1 - '1'][coordinates2 - '1'] == ' ') {
                    currentCells(cells, coordinates1, coordinates2, a)
                    if (stateGame(cells) != "") {
                        println(stateGame(cells))
                        break
                    }
                    a++
                } else {
                    println("This cell is occupied! Choose another one!")
                }
            } else {
                println("Coordinates should be from 1 to 3!")
            }
        } else {
            println("You should enter numbers!")
        }
        print("Enter the coordinates: ")
    }
}

fun main() {
    val cells = Array(3) { CharArray(3) }
    print(cells)

    print("Enter the coordinates: ")
    playingGame(cells)
}
