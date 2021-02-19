package day4

import java.security.MessageDigest
import javax.xml.bind.DatatypeConverter

/**
 * Created by leventejonas on 22/07/17.
 */

/*
    To do this, he needs to find MD5 hashes which, in hexadecimal, start with at least five zeroes. The input to the
    MD5 hash is some secret key (your puzzle input, given below) followed by a number in decimal. To mine AdventCoins,
    you must find Santa the lowest positive number (no leading zeroes: 1, 2, 3, ...) that produces such a hash.

    For example:

        If your secret key is abcdef, the answer is 609043, because the MD5 hash of abcdef609043 starts with five
        zeroes (000001dbbfa...), and it is the lowest such number to do so.

        If your secret key is pqrstuv, the lowest number it combines with to make an MD5 hash starting with five
        zeroes is 1048970; that is, the MD5 hash of pqrstuv1048970 looks like 000006136ef....
 */


val input = "iwrupvqb"
val md5 = MessageDigest.getInstance("MD5")

fun main(args: Array<String>) {
    println("Solution for 4/1: ${findLowestNumber("00000")}")
    println("Solution for 4/2: ${findLowestNumber("000000")}")
}

fun findLowestNumber(precondition: String): Int {
    var count = 0
    var hash: String
    do {
        count++
        hash = calculateHash(input + count)
    } while (!hash.startsWith(precondition, true))
    return count
}

fun calculateHash(candidate: String): String {
    val digest = md5.digest(candidate.toByteArray())
    return DatatypeConverter.printHexBinary(digest)
}
