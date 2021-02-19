package day8

import java.io.File

fun main() {
    val inputLines = File("src/main/kotlin/day8/day8_input.txt").readLines()
    println(day8(inputLines))
    println(day82(inputLines))
}

data class Row(val command: String, val sign: Char, val number: Int)
fun day8(inputLines: List<String>): Int {
    var result = 0
    val executedIndexes = mutableSetOf<Int>()
    var index = 0
    while (!executedIndexes.contains(index)) {
        executedIndexes.add(index)
        val splitted = inputLines[index].split(" ")
        val row = Row(splitted[0], splitted[1].first(), splitted[1].substring(1).toInt())
        when (row.command) {
            "acc" -> {
                if (row.sign == '+') {
                    result += row.number
                } else {
                    result -= row.number
                }
                index += 1
            }
            "jmp" -> {
                if (row.sign == '+') {
                    index += row.number
                } else {
                    index -= row.number
                }
            }
            "nop" -> {
                index += 1
            }
        }
    }
    return result
}

fun day82(inputLines: List<String>): Int {
    val rows = inputToRows(inputLines)
    rows.forEachIndexed { index, element ->
        if (element.command == "jmp") {
            val copy = mutableListOf<Row>()
            copy.addAll(rows)
            copy[index] = Row("nop", copy[index].sign, copy[index].number)
            val sum = task(copy)
            if (sum != 0) {
                return sum
            }
        } else if (element.command == "nop" && element.number != 0) {
            val copy = mutableListOf<Row>()
            copy.addAll(rows)
            copy[index] = Row("jmp", copy[index].sign, copy[index].number)
            val sum = task(copy)
            if (sum != 0) {
                return sum
            }
        }
    }
    return 0
}

fun task(rows: List<Row>): Int {
    var result = 0
    val executedIndexes = mutableSetOf<Int>()
    var index = 0
    while (!executedIndexes.contains(index) && index < rows.size) {
        executedIndexes.add(index)
        val row = rows[index]
        when (row.command) {
            "acc" -> {
                if (row.sign == '+') {
                    result += row.number
                } else {
                    result -= row.number
                }
                index += 1
            }
            "jmp" -> {
                if (row.sign == '+') {
                    index += row.number
                } else {
                    index -= row.number
                }
            }
            "nop" -> {
                index += 1
            }
        }
    }
    return if (index >= rows.size) result else 0
}

fun inputToRows(inputLines: List<String>): List<Row> {
    val rows = mutableListOf<Row>()
    inputLines.forEachIndexed { index, element ->
        val splitted = inputLines[index].split(" ")
        rows.add(Row(splitted[0], splitted[1].first(), splitted[1].substring(1).toInt()))
    }
    return rows
}