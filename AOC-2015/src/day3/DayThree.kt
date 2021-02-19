package day3

import java.io.File

/**
 * Created by leventejonas on 10/07/17.
 */

/*
    He begins by delivering a present to the house at his starting location, and then an elf at the North Pole calls
    him via radio and tells him where to move next. Moves are always exactly one house to the north (^), south (v),
    east (>), or west (<). After each move, he delivers another present to the house at his new location.

    However, the elf back at the north pole has had a little too much eggnog, and so his directions are a little off,
     and Santa ends up visiting some houses more than once. How many houses receive at least one present?

    For example:

        > delivers presents to 2 houses: one at the starting location, and one to the east.
        ^>v< delivers presents to 4 houses in a square, including twice to the house at his starting/ending location.
        ^v^v^v^v^v delivers a bunch of presents to some very lucky children at only 2 houses.
 */

data class Coordinate(val x: Int, val y: Int)

fun main(args: Array<String>) {
    val input = File("src/day3/day_three_input.txt").readText()
    // Right, right it's shitty but at least both of the tasks running
    var x = 0
    var y = 0
    var xSanta = 0
    var ySanta = 0
    var xRobot = 0
    var yRobot = 0
    var coordinates: MutableSet<Coordinate> = mutableSetOf(Coordinate(x,y))
    var nextYearCoordinates: MutableSet<Coordinate> = mutableSetOf(Coordinate(x,y))
    input.mapIndexed { index, c ->
        if (index % 2 == 0) {
            when (c) {
                '^' -> {
                    y += 1
                    ySanta += 1
                }
                'v' -> {
                    y -= 1
                    ySanta -= 1
                }
                '<' -> {
                    x -= 1
                    xSanta -= 1
                }
                '>' -> {
                    x += 1
                    xSanta += 1
                }
            }
            nextYearCoordinates.add(Coordinate(xSanta, ySanta))
        } else {
            when (c) {
                '^' -> {
                    y += 1
                    yRobot += 1
                }
                'v' -> {
                    y -= 1
                    yRobot -= 1
                }
                '<' -> {
                    x -= 1
                    xRobot -= 1
                }
                '>' -> {
                    x += 1
                    xRobot += 1
                }
            }
            nextYearCoordinates.add(Coordinate(xRobot, yRobot))
        }
        coordinates.add(Coordinate(x, y))
    }
    print("Count of visited houses: ${coordinates.size}")

    /* Santa and Robo-Santa start at the same location (delivering two presents to the same starting house), then take
       turns moving based on instructions from the elf, who is eggnoggedly reading from the same script as the previous
       year.

        This year, how many houses receive at least one present?

        For example:

            ^v delivers presents to 3 houses, because Santa goes north, and then Robo-Santa goes south.
            ^>v< now delivers presents to 3 houses, and Santa and Robo-Santa end up back where they started.
            ^v^v^v^v^v now delivers presents to 11 houses, with Santa going one direction and Robo-Santa going the other.
    */
    print("Count of next year's visited houses: ${nextYearCoordinates.size}")
}
