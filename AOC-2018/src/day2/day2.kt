package day2

import java.io.File

fun main(args: Array<String>) {
    val inputLines = File("src/day2/day2_input.txt").readLines()
    println(day2First(inputLines))
    println(day2Second(inputLines))
}

/*
    To make sure you didn't miss any, you scan the likely candidate boxes again,
    counting the number that have an ID containing exactly two of any letter and
    then separately counting those with exactly three of any letter.
    You can multiply those two counts together to get a rudimentary checksum
    and compare it to what your device predicts.
 */
fun day2First(inputLines: List<String>): Int {
    var twos = 0
    var threes = 0
    inputLines.forEach {
        val frequencies = it.groupingBy { it -> it }.eachCount()
        twos += frequencies.containsValue(2).int
        threes += frequencies.containsValue(3).int
    }
    return twos * threes
}

/*
    Confident that your list of box IDs is complete,
    you're ready to find the boxes full of prototype fabric.

    The boxes will have IDs which differ by exactly
    one character at the same position in both strings.
 */
fun day2Second(inputLines: List<String>): String {
    val solutionLength = inputLines.get(0).length - 1
    inputLines.forEachIndexed { index, first ->
        inputLines.subList(index + 1, inputLines.size).forEach {
            val mergeWord = first.commonPrefixWith(it) + first.commonSuffixWith(it)
            if (mergeWord.length == solutionLength) {
                return mergeWord
            }
        }
    }
    return "I'm gonna fail"
}