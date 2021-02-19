package day1

import java.io.File

fun main() {
    val inputLines = File("src/main/kotlin/day1/day1_input.txt").readLines()
    println(day1(inputLines))
    println(day1_2(inputLines))
}

fun day1(inputLines: List<String>): Int {
    inputLines.forEachIndexed { index, element ->
        inputLines.filterIndexed { index2, _ -> index2 <= index }
            .forEachIndexed { _, element2 ->
                if (element.toInt() + element2.toInt() == 2020) {
                    return (element.toInt() * element2.toInt())
                }
            }
    }
    return 0
}

fun day1_2(inputLines: List<String>): Int {
    inputLines.forEachIndexed { index, element ->
        inputLines.filterIndexed { index2, _ -> index2 <= index }
            .forEachIndexed { index2, element2 ->
                inputLines.filterIndexed { index3, _ -> index3 <= index2 }
                    .forEachIndexed { _, element3 ->
                        if (element.toInt() + element2.toInt() + element3.toInt() == 2020) {
                            return (element.toInt() * element2.toInt() * element3.toInt())
                        }
                    }
            }
    }
    return 0
}