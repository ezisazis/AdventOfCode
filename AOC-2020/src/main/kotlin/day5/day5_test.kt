package day5

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day5Test {

    @Test
    fun test0_row() {
        assertEquals(calculateRow("FBFBBFFRLR"),44)
    }

    @Test
    fun test0_col() {
        assertEquals(calculateCol("FBFBBFFRLR"),5)
    }

    @Test
    fun test0_seat() {
        assertEquals(calculateSeat("FBFBBFFRLR"),357)
    }

    @Test
    fun test1_row() {
        assertEquals(calculateRow("BFFFBBFRRR"),70)
    }

    @Test
    fun test1_col() {
        assertEquals(calculateCol("BFFFBBFRRR"),7)
    }

    @Test
    fun test1_seat() {
        assertEquals(calculateSeat("BFFFBBFRRR"),567)
    }

    @Test
    fun test2_row() {
        assertEquals(calculateRow("FFFBBBFRRR"),14)
    }

    @Test
    fun test2_col() {
        assertEquals(calculateCol("FFFBBBFRRR"),7)
    }

    @Test
    fun test2_seat() {
        assertEquals(calculateSeat("FFFBBBFRRR"),119)
    }

    @Test
    fun test3_row() {
        assertEquals(calculateRow("BBFFBBFRLL"),102)
    }

    @Test
    fun test3_col() {
        assertEquals(calculateCol("BBFFBBFRLL"),4)
    }

    @Test
    fun test3_seat() {
        assertEquals(calculateSeat("BBFFBBFRLL"),820)
    }
}