package day3

import java.io.File

fun main() {
    val inputLines = File("src/main/kotlin/day3/day3_input.txt").readLines()
    println(day3(inputLines))
    println(day3Second(inputLines))
}

val move = Pair(3,1)
const val tree = '#'
var position = Position(0,0)
val moves = listOf(
    Pair(1,1),
    Pair(3,1),
    Pair(5,1),
    Pair(7,1),
    Pair(1,2)
)

data class Position(var x: Int, var y: Int)

fun day3(inputLines: List<String>): Int {
    val lastLine = inputLines.size-1
    var count = 0
    while (position.y != lastLine) {
        position.x += move.first
        if (position.x > inputLines[0].length-1) {
            position.x -= inputLines[0].length
        }
        position.y += move.second

        if (inputLines[position.y][position.x] == tree) {
            count++
        }
    }
    return count
}

fun day3Second(inputLines: List<String>): Float {
    val lastLine = inputLines.size-1
    var count = 1F
    moves.forEach {
        position = Position(0,0)
        var tmpCount = 0
        while (position.y != lastLine) {
            position.x += it.first
            if (position.x > inputLines[0].length-1) {
                position.x -= inputLines[0].length
            }
            position.y += it.second

            if (inputLines[position.y][position.x] == tree) {
                tmpCount++
            }
        }
        count *= tmpCount
    }
    return count
}