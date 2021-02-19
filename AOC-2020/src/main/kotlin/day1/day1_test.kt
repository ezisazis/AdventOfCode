package day1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day1Test {

    private val input = listOf("1721", "979", "366", "299" ,"675", "1456")

    @Test
    fun test1() {
        assertEquals(day1(input), 514579)
    }

    @Test
    fun test2() {
        assertEquals(day1_2(input), 241861950)
    }
}