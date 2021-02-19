package day9

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day9Test {

    val input = listOf(
        "35",
        "20",
        "15",
        "25",
        "47",
        "40",
        "62",
        "55",
        "65",
        "95",
        "102",
        "117",
        "150",
        "182",
        "127",
        "219",
        "299",
        "277",
        "309",
        "576"
    )

    @Test
    fun test1() {
        assertEquals(day9(input, 5),"127")
    }

    @Test
    fun test2() {
        assertEquals(day92(input, day9(input, 5).toInt()),62)
    }

}