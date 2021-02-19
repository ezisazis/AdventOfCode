package day2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day2Test {

    private val input = listOf("1-3 a: abcde", "1-3 b: cdefg", "2-9 c: ccccccccc")

    @Test
    fun test1() {
        assertEquals(day2(input), 2)
    }

    @Test
    fun test2() {
        assertEquals(day2Second(input), 1)
    }
}