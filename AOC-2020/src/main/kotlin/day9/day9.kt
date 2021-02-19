package day9

import java.io.File

fun main() {
    val inputLines = File("src/main/kotlin/day9/day9_input.txt").readLines()
    println(day9(inputLines, 25))
    println(day92(inputLines, day9(inputLines, 25).toInt()))
}

fun day9(inputLines: List<String>, limit: Int): String {
    i@for (i in limit until inputLines.size) {
        var found = false
        j@for (j in i-limit until i) {
            k@for (k in j+1 until i) {
                if (inputLines[j].toLong() + inputLines[k].toLong() == inputLines[i].toLong()
                    && inputLines[j].toLong() != inputLines[k].toLong()) {
                    found = true
                    continue@i
                }
            }
        }
        if (!found) {
            return inputLines[i]
        }
    }
    return ""
}

fun day92(inputLines: List<String>, searchFor: Int): Long? {
    i@for (i in inputLines.indices) {
        var sum = 0L
        val numbers = mutableListOf<Long>()
        for (j in i until inputLines.size) {
            numbers.add(inputLines[j].toLong())
            sum += inputLines[j].toLong()
            if (sum == searchFor.toLong()) {
                return numbers.min()!!.plus(numbers.max()!!)
            } else if (sum > searchFor ) {
                continue@i
            }
        }
    }
    return 0
}
