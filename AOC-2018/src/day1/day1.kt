package day1

import java.io.File


fun main(args: Array<String>) {
    val inputLines = File("src/day1/day1_input.txt").readLines()
    println(day1First(inputLines))
    println(day1Second(inputLines))
}

/*
    If the device displays frequency changes of +1, -2, +3, +1,
    then starting from a frequency of zero, the following changes would occur:

    Current frequency  0, change of +1; resulting frequency  1.
    Current frequency  1, change of -2; resulting frequency -1.
    Current frequency -1, change of +3; resulting frequency  2.
    Current frequency  2, change of +1; resulting frequency  3.
    In this example, the resulting frequency is 3.
*/
fun day1First(inputLines: List<String>): Int {
    return inputLines.sumBy { it.toInt() }
}

/*
    You notice that the device repeats the same frequency change list over and over. To calibrate the device, you need to find the first frequency it reaches twice.

    For example, using the same list of changes above, the device would loop as follows:

    Current frequency  0, change of +1; resulting frequency  1.
    Current frequency  1, change of -2; resulting frequency -1.
    Current frequency -1, change of +3; resulting frequency  2.
    Current frequency  2, change of +1; resulting frequency  3.
    (At this point, the device continues from the start of the list.)
    Current frequency  3, change of +1; resulting frequency  4.
    Current frequency  4, change of -2; resulting frequency  2, which has already been seen.
 */
fun day1Second(inputLines: List<String>): Int {
    var sums = mutableListOf(0)
    var sum = 0

    while (true) {
        inputLines.forEach {
            sum += it.toInt()
            if (sums.contains(sum)) {
                return sum
            } else {
                sums.add(sum)
            }
        }
    }
}