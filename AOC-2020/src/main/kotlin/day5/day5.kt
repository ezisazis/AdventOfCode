package day5

import java.io.File

fun main() {
    val inputLines = File("src/main/kotlin/day5/day5_input.txt").readLines()
    println(day5(inputLines))
    println(day5Second(inputLines))
}

fun calculateRow(pass: String): Int {
    val row = pass.substring(0, 7)
    var bottom = 0
    var top = 127
    row.forEach {
        val difference = top - bottom
        if (it == 'F') {
            top = bottom + difference / 2
        } else {
            bottom = top - difference / 2
        }
    }
    return bottom
}

fun calculateCol(pass: String): Int {
    val col = pass.substring(7, 10)
    var bottom = 0
    var top = 7
    col.forEach {
        val difference = top - bottom
        if (it == 'L') {
            top = bottom + difference / 2
        } else {
            bottom = top - difference / 2
        }
    }
    return bottom
}

fun calculateSeat(pass: String): Int {
    return calculateRow(pass) * 8 + calculateCol(pass)
}


fun day5(inputLines: List<String>): Int {
    var max = 0
    inputLines.forEach {
        val seat = calculateSeat(it)
        if (seat > max) {
            max = seat
        }
    }
    return max
}

fun day5Second(inputLines: List<String>): Int {
    val seats = mutableListOf<Int>()
    inputLines.forEach {
        seats.add(calculateSeat(it))
    }
    seats.sort()
    seats.forEachIndexed{ index, it ->
        if (it + 1 != seats[index+1]) {
            return it + 1
        }
    }
    return 0
}
