package day4

import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main(args: Array<String>) {
    val inputLines = File("src/day4/day4_input.txt").readLines()
    println(day4First(inputLines))
    println(day4Second(inputLines))
}

/*

    Timestamps are written using year-month-day hour:minute format.
    The guard falling asleep or waking up is always the one whose shift
    most recently started. Because all asleep/awake times are during the m
    idnight hour (00:00 - 00:59), only the minute portion (00 - 59) is relevant for those events.


    Strategy 1: Find the guard that has the most minutes asleep.
    What minute does that guard spend asleep the most?

    While this example listed the entries in chronological order,
    your entries are in the order you found them. You'll need to organize them before they can be analyzed.

    What is the ID of the guard you chose multiplied by the minute you chose?
 */
var guardActions = mutableListOf<GuardAction>()
var guards = mutableMapOf<Int, MutableMap<Int, Int>>()

fun day4First(inputLines: List<String>): Int {
    val sortedLines = inputLines.sorted()
    var currentId = 0

    sortedLines.forEach {
        val parts = it.split(" ")
        var state: GuardState

        when {
            it.contains("begins") -> {
                currentId = parts[3].drop(1).toInt()
                state = GuardState.START
            }
            it.contains("wakes") -> state = GuardState.WAKE_UP
            else -> state = GuardState.SLEEP
        }

        val guardAction = GuardAction(currentId, LocalDateTime.parse(
            parts[0].drop(1) + " " + parts[1].dropLast(1),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
            state)

        if (!it.contains("begins")) {
            guardActions.add(guardAction)
        }
    }

    guardActions
        .forEachIndexed { index, guardAction ->
            if ( index % 2 == 0) {
                for (i in guardAction.date.minute until guardActions[index + 1].date.minute) { //UNTIL
                    if (!guards.containsKey(guardAction.id)) {
                        guards.put(guardAction.id, hashMapOf(i to 1))
                    } else {
                        if (guards.get(guardAction.id)!!.containsKey(i)) {
                            guards.get(guardAction.id)!!.put(i, guards.get(guardAction.id)!!.getValue(i) + 1)
                        } else {
                            guards.get(guardAction.id)!!.put(i, 1)
                        }
                    }
                }
            }
    }

    var (maxId, maxMinutes) = Pair(0 , 0)
    guards.forEach {
        var currentMinutes = 0
        it.value.forEach {
            currentMinutes += it.value
        }
        if (currentMinutes > maxMinutes) {
            maxId = it.key
            maxMinutes = currentMinutes
        }
    }

    var minute = guards.get(maxId)!!.maxBy { it.value }!!.key

    return maxId * minute
}


enum class GuardState {
    START,
    SLEEP,
    WAKE_UP
}

data class GuardAction(var id: Int, var date: LocalDateTime, var state: GuardState)

fun day4Second(inputLines: List<String>): Int {
    return 0
}

