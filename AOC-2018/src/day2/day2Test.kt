package day2

import org.junit.Test
import kotlin.test.assertEquals

class Day2Test {
    /*
    For example, if you see the following box IDs:

    abcdef contains no letters that appear exactly two or three times.
    bababc contains two a and three b, so it counts for both.
    abbcde contains two b, but no letter appears exactly three times.
    abcccd contains three c, but no letter appears exactly two times.
    aabcdd contains two a and two d, but it only counts once.
    abcdee contains two e.
    ababab contains three a and three b, but it only counts once.

    Of these box IDs, four of them contain a letter which appears exactly twice,
    and three of them contain a letter which appears exactly three times.
    Multiplying these together produces a checksum of 4 * 3 = 12.

    */
    @Test
    fun `test1`() {
        assertEquals(day2First(listOf("abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab")), 12)
    }

    /*
    abcde
    fghij
    klmno
    pqrst
    fguij
    axcye
    wvxyz

    The IDs abcde and axcye are close, but they differ by two characters (the second and fourth).
    However, the IDs fghij and fguij differ by exactly one character, the third (h and u).
    Those must be the correct boxes.

    What letters are common between the two correct box IDs?
    (In the example above, this is found by removing the differing character from either ID, producing fgij.)

     */
    @Test
    fun `test2`() {
        assertEquals(day2Second(listOf("abcde", "fghij", "klmno", "pqrst", "fguij", "axcye", "wvxyz")), "fgij")
    }
}