package day2

import java.io.File

fun main() {
    val inputLines = File("src/main/kotlin/day2/day2_input.txt").readLines()
    println(day2(inputLines))
    println(day2Second(inputLines))
}

fun day2(inputLines: List<String>): Int {
    var result = 0
    inputLines.forEach { it ->
        val parts = it.split(" ")
        val task = Task(parts[0].split("-")[0].toInt(),
            parts[0].split("-")[1].toInt(),
            parts[1].take(1),
            parts[2])
        val count = task.password.count{
            task.char.contains(it)
        }
        if (count.between(task.minOccurrence, task.maxOccurrence)) {
            result++
        }
    }
    return result
}

fun day2Second(inputLines: List<String>): Int {
    var result = 0
    inputLines.forEach {
        val parts = it.split(" ")
        val task = Task(parts[0].split("-")[0].toInt(),
            parts[0].split("-")[1].toInt(),
            parts[1].take(1),
            parts[2])
        var count = 0
        if (task.password.getOrNull(task.minOccurrence - 1) == task.char.single()) {
            count++
        }
        if (task.password.getOrNull(task.maxOccurrence - 1) == task.char.single()) {
            count++
        }
        if (count == 1) {
            result++
        }
    }
    return result
}

fun Int.between(a: Int, b: Int): Boolean {
    return when {
        a<b -> (this in a..b)
        else -> (this in b..a)
    }
}

data class Task (val minOccurrence: Int, val maxOccurrence: Int, val char: String, val password: String)