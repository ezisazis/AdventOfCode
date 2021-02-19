package day1

import java.io.File

/**
 * Created by leventejonas on 25/06/17.
 */

/*
    Santa is trying to deliver presents in a large apartment building, but he can't find the right floor -
    the directions he got are a little confusing. He starts on the ground floor (floor 0) and then follows the
    instructions one character at a time.

    An opening parenthesis, (, means he should go up one floor, and a closing parenthesis, ), means he should
    go down one floor.

    The apartment building is very tall, and the basement is very deep; he will never find the top or bottom floors.

    For example:

        (()) and ()() both result in floor 0.
        ((( and (()(()( both result in floor 3.
        ))((((( also results in floor 3.
        ()) and ))( both result in floor -1 (the first basement level).
        ))) and )())()) both result in floor -3.

    To what floor do the instructions take Santa?
 */
fun main(args: Array<String>) {
    val input = File("src/day1/day_one_input.txt").readText()
    var floor = 0
    var basementFound = false
    for ((index, character) in input.withIndex()) {
        floor += move(character)
        /*
            Now, given the same instructions, find the position of the first character that causes him to enter the
            basement (floor -1). The first character in the instructions has position 1, the second character has
            position 2, and so on.
        */
        if (floor == -1 && !basementFound) {
            println("Basement found at ${index+1}")
            basementFound = true
        }
    }
    println("Final floor is $floor")
}

fun move(character: Char): Int {
    when (character) {
        '(' -> return 1
        ')' -> return -1
        else -> return 0
    }
}