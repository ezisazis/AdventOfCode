package day3

import java.io.File

fun main(args: Array<String>) {
    val inputLines = File("src/day3/day3_input.txt").readLines()
    println(day3First(inputLines))
    println(day3Second(inputLines))
}

/*
    The whole piece of fabric they're working on is a very large square - at least 1000 inches on each side.


    Each Elf has made a claim about which area of fabric would be ideal for Santa's suit. All claims have an ID and
    consist of a single rectangle with edges parallel to the edges of the fabric. Each claim's rectangle is defined
    as follows:

    The number of inches between the left edge of the fabric and the left edge of the rectangle.
    The number of inches between the top edge of the fabric and the top edge of the rectangle.
    The width of the rectangle in inches.
    The height of the rectangle in inches.

    A claim like
    #123 @ 3,2: 5x4
    means that claim ID 123 specifies a rectangle 3 inches from the left edge, 2 inches from the top edge,
    5 inches wide, and 4 inches tall. Visually, it claims the square inches of fabric represented by #

    If the Elves all proceed with their own plans, none of them will have enough fabric.
    How many square inches of fabric are within two or more claims?
*/
fun day3First(inputLines: List<String>): Int {
    val coordinates : MutableList<Coordinate> = arrayListOf()

    inputLines.forEach {
        val parts = it.split(" ")
        val cd = parts[2].split(",")
        val size = parts[3].split("x")
        val claim = Claim(Coordinate(cd[0].toInt(), cd[1].removeSuffix(":").toInt()), size[0].toInt(), size[1].toInt())
        (claim.coordinate.x until claim.coordinate.x + claim.width).forEachIndexed { index, i ->
            (claim.coordinate.y until claim.coordinate.y + claim.height).forEachIndexed { indexj, j ->
                coordinates.add(Coordinate(i, j))
            }
        }
    }
    return coordinates.asSequence().groupingBy { it }.eachCount().filter { it.value > 1 }.count()
}

/*
    What is the ID of the only claim that doesn't overlap?
 */
fun day3Second(inputLines: List<String>): Int {
    val claims : MutableList<Claim> = arrayListOf()
    val coordinates : MutableList<Coordinate> = arrayListOf()

    inputLines.forEach {
        val parts = it.split(" ")
        val cd = parts[2].split(",")
        val size = parts[3].split("x")
        val claim = Claim(Coordinate(cd[0].toInt(), cd[1].removeSuffix(":").toInt()), size[0].toInt(), size[1].toInt(), parts[0].removePrefix("#").toInt())
        claims.add(claim)
        (claim.coordinate.x until claim.coordinate.x + claim.width).forEachIndexed { index, i ->
            (claim.coordinate.y until claim.coordinate.y + claim.height).forEachIndexed { indexj, j ->
                coordinates.add(Coordinate(i, j))
            }
        }
    }

    claims.forEach {
        var found = true
        (it.coordinate.x until it.coordinate.x + it.width).forEachIndexed outer@ { index, i ->
            (it.coordinate.y until it.coordinate.y + it.height).forEachIndexed inner@ { indexj, j ->
                val count = coordinates.filter { coordinate ->  coordinate.x == i && coordinate.y == j}.count()
                if (count > 1) {
                    found = false
                }
            }
        }
        if (found) return it.id
    }
    return 0
}

data class Claim(val coordinate: Coordinate, val width: Int, val height: Int, val id : Int = 0)

data class Coordinate(val x: Int, val y: Int)
