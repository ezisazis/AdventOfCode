package day8

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day8Test {

    val input = listOf(
        "nop +0",
        "acc +1",
        "jmp +4",
        "acc +3",
        "jmp -3",
        "acc -99",
        "acc +1",
        "jmp -4",
        "acc +6"
    )

    @Test
    fun test1() {
        assertEquals(day8(input),5)
    }

    @Test
    fun test2() {
        assertEquals(day82(input),8)
    }

}