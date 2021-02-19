package day4

import org.junit.Test
import java.io.File
import kotlin.test.assertEquals

class Day4Test {
    /*
        In the example above, Guard #10 spent the most minutes asleep,
        a total of 50 minutes (20+25+5), while Guard #99 only slept for a total of 30 minutes (10+10+10).
        Guard #10 was asleep most during minute 24 (on two days, whereas any
        other minute the guard was asleep was only seen on one day).
    */
    @Test
    fun `test1`() {
        // test input's rows got randomized in the txt
        assertEquals(day4First(File("src/day4/day4_test_input.txt").readLines()), 240)
    }

//    @Test
//    fun `test2`() {
//        assertEquals(day4Second(listOf("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2")), 3)
//    }
}