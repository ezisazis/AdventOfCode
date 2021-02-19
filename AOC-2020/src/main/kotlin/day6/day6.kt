package day6

import day4.requiredFields
import java.io.File

fun main() {
    val inputLines = File("src/main/kotlin/day6/day6_input.txt").readLines()
    println(day6(inputLines))
    println(day62(inputLines))
}

fun day6(inputLines: List<String>): Int {
    var fields = mutableSetOf<Char>()
    var result = 0
    inputLines.forEach fore@{
        if (it.isEmpty()) {
            result += fields.size
            fields.clear()
            return@fore
        }
        it.forEach {
            fields.add(it)
        }
    }
    return result
}

fun day62(inputLines: List<String>): Int {
    val fields = mutableListOf<Set<Char>>()
    var result = 0
    inputLines.forEach fore@{
        val answers = mutableSetOf<Char>()
        if (it.isEmpty()) {
            if (fields.size == 1) {
                result += fields[0].size
            } else {
                val tmp = mutableMapOf<Char, Int>()
                fields.forEach {
                    it.forEach {
                        if (tmp.containsKey(it)) {
                            tmp.put(it, tmp[it]?.plus(1)!!)
                        } else {
                            tmp.put(it, 1)
                        }
                    }
                }
                tmp.forEach {
                    if (it.value == fields.size) {
                        result += 1
                    }
                }
            }
            fields.clear()
            return@fore
        }
        it.forEach {
            answers.add(it)
        }
        fields.add(answers)
    }
    return result
}