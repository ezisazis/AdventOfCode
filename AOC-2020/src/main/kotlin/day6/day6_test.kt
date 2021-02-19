package day6

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day6Test {

    val input = listOf("abc", "", "a", "b", "c", "", "ab", "ac", "", "a", "a", "a", "a", "", "b", "")

    @Test
    fun test1() {
        assertEquals(day6(input),11)
    }

    @Test
    fun test2() {
        assertEquals(day62(input),6)
    }
}