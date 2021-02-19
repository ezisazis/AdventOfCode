package day3

import org.junit.Test
import kotlin.test.assertEquals

class Day3Test {
    /*
    The problem is that many of the claims overlap, causing two or more claims to cover part of the same areas.
    For example, consider the following claims:

    #1 @ 1,3: 4x4
    #2 @ 3,1: 4x4
    #3 @ 5,5: 2x2
    Visually, these claim the following areas:

    ........
    ...2222.
    ...2222.
    .11XX22.
    .11XX22.
    .111133.
    .111133.
    ........

    */
    @Test
    fun `test1`() {
        assertEquals(day3First(listOf("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2")), 4)
    }

    /*
        Amidst the chaos, you notice that exactly one claim doesn't overlap by even a
        single square inch of fabric with any other claim. If you can somehow draw attention to it,
        maybe the Elves will be able to make Santa's suit after all!

        For example, in the claims above, only claim 3 is intact after all claims are made.
     */
    @Test
    fun `test2`() {
        assertEquals(day3Second(listOf("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2")), 3)
    }
}