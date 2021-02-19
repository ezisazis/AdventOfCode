package day5

import java.io.File

/**
 * Created by leventejonas on 22/07/17.
 */

fun main(args: Array<String>) {
    val input = File("src/day5/day_five_input.txt").readLines()

    /*
        A nice string is one with all of the following properties:

        It contains at least three vowels (aeiou only), like aei, xazegov, or aeiouaeiouaeiou.
        It contains at least one letter that appears twice in a row, like xx, abcdde (dd), or aabbccdd
            (aa, bb, cc, or dd).
        It does not contain the strings ab, cd, pq, or xy, even if they are part of one of the other requirements.

        How many strings are nice?
     */
    println("Count of nice kids: ${niceKidCount(input)}")

    /*
        Now, a nice string is one with all of the following properties:

        It contains a pair of any two letters that appears at least twice in the string without overlapping, like
            xyxy (xy) or aabcdefgaa (aa), but not like aaa (aa, but it overlaps).

        It contains at least one letter which repeats with exactly one letter between them, like xyx, abcdefeghi (efe),
            or even aaa.
     */
    println("Count of the best kids: ${bestKidCount(input)}")
}

val vowels: Set<Char> = setOf('a','e','i','o','u')
val exceptions: Set<String> = setOf("ab", "cd", "pq", "xy")
fun niceKidCount(input: List<String>): Int {
    var count = 0
    outer@ for (line in input) {
        var isThereDoubleChar = false
        var vowelCount = 0

        for ((index, char) in line.withIndex()) {
            vowelCount += if (char in vowels) 1 else 0
            if (index != line.length - 1) {
                val slice = "$char${line[index+1]}"
                if (exceptions.contains(slice)) {
                    continue@outer
                }
                if (char.equals(line[index+1])) {
                    isThereDoubleChar = true
                }
            }
        }
        if (isThereDoubleChar && vowelCount > 2) {
            count++
        }
    }
    return count
}



fun bestKidCount(input: List<String>): Int {
    var count = 0
    outer@ for (line in input) {
        var isThereDoubleChars = false
        var duplications: MutableSet<String> = mutableSetOf()
        for (i in 0..line.length-2) {
            if (duplications.contains("${line[i]}${line[i+1]}")) {
                if (!"${line[i]}${line[i+1]}".equals("${line[i-1]}${line[i]}")) {
                    isThereDoubleChars = true
                }
            } else {
                duplications.add("${line[i]}${line[i + 1]}")
            }
        }

        val isThereRepeat = (0..line.length-3).any { line[it].equals(line[it +2]) }

        if (isThereDoubleChars && isThereRepeat) {
            count++
        }
    }
    return count
}