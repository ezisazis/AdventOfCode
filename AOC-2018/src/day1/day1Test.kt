package day1

import org.junit.Test
import kotlin.test.assertEquals

class Day1Test {
    /*
    Here are other example situations:

    +1, +1, +1 results in  3
    +1, +1, -2 results in  0
    -1, -2, -3 results in -6
    */
    @Test
    fun `test1`() {
        assertEquals(day1First(listOf("+1", "+1", "+1")), 3)
    }

    @Test
    fun `test2`() {
        assertEquals(day1First(listOf("+1", "+1", "-2")), 0)
    }

    @Test
    fun `test3`() {
        assertEquals(day1First(listOf("-1", "-2", "-3")), -6)
    }

    /*
        Here are other examples:

        +1, -1 first reaches 0 twice.
        +3, +3, +4, -2, -4 first reaches 10 twice.
        -6, +3, +8, +5, -6 first reaches 5 twice.
        +7, +7, -2, -7, -4 first reaches 14 twice.
    */
    @Test
    fun `test01`() {
        assertEquals(day1Second(listOf("+1", "-1")), 0)
    }

    @Test
    fun `test02`() {
        assertEquals(day1Second(listOf("+3", "+3", "+4", "-2", "-4")), 10)
    }

    @Test
    fun `test03`() {
        assertEquals(day1Second(listOf("-6", "+3", "+8", "+5", "-6")), 5)
    }

    @Test
    fun `test04`() {
        assertEquals(day1Second(listOf("+7", "+7", "-2", "-7", "-4")), 14)
    }
}