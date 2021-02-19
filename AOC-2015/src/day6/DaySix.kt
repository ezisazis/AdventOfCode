package day6

import java.io.File

/**
 * Created by leventejonas on 22/07/17.
 */

var lights = Array(1000, { BooleanArray(1000)})
var leds = Array(1000, { IntArray(1000)})

fun main(args: Array<String>) {
    val input = File("src/day6/input.txt").readLines()

    lights.forEach { it ->
        it.indices.forEach { i ->
            it[i] = false
        }
    }

    leds.forEach { it ->
        it.indices.forEach { i ->
            it[i] = 0
        }
    }

    for (line in input) {
        val parameters = line.split(" ")
        var xCoordinates :Coordinate
        var yCoordinates :Coordinate
        var action :Action
        when (parameters[1]) {
            "on" -> {
                action = Action.TURN_ON
                xCoordinates = Coordinate(parameters[2].split(",")[0].toInt(), parameters[4].split(",")[0].toInt())
                yCoordinates = Coordinate(parameters[2].split(",")[1].toInt(), parameters[4].split(",")[1].toInt())
            }
            "off" -> {
                action = Action.TURN_OFF
                xCoordinates = Coordinate(parameters[2].split(",")[0].toInt(), parameters[4].split(",")[0].toInt())
                yCoordinates = Coordinate(parameters[2].split(",")[1].toInt(), parameters[4].split(",")[1].toInt())
            }
            else -> {
                action = Action.TOGGLE
                xCoordinates = Coordinate(parameters[1].split(",")[0].toInt(), parameters[3].split(",")[0].toInt())
                yCoordinates = Coordinate(parameters[1].split(",")[1].toInt(), parameters[3].split(",")[1].toInt())
            }
        }
        for (i in xCoordinates.from.. xCoordinates.to) {
            for (j in yCoordinates.from.. yCoordinates.to) {
                when (action) {
                    Action.TURN_ON -> {
                        lights[i][j] = true
                        leds[i][j] += 1
                    }
                    Action.TURN_OFF -> {
                        lights[i][j] = false
                        leds[i][j] -= if (leds[i][j] == 0) 0 else 1
                    }
                    Action.TOGGLE -> {
                        lights[i][j] = !lights[i][j]
                        leds[i][j] += 2
                    }
                }
            }
        }
    }
    var count = 0
    lights.forEach { it ->
        it.indices.forEach { i ->
            count += if (it[i]) 1 else 0
        }
    }
    println("Count of ON lights:$count")

    var brightness = 0
    leds.forEach { it ->
        it.indices.forEach { i ->
            brightness += it[i]
        }
    }
    println("power of lights:$brightness")
}

data class Coordinate(val from: Int, val to: Int)

enum class Action {
    TOGGLE, TURN_ON, TURN_OFF
}