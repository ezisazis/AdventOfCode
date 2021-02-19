package day4

import java.io.File

fun main() {
    val inputLines = File("src/main/kotlin/day4/day4_input.txt").readLines()
    println(day4(inputLines))
    println(day4Second(inputLines))
}

val requiredFields = setOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")//"cid"

fun day4(inputLines: List<String>): Int {
    var fields = mutableSetOf<String>()
    var result = 0
    inputLines.forEach fore@{
        if (it.isEmpty()) {
            if (fields == requiredFields) {
                result++
            }
            fields = mutableSetOf()
            return@fore
        }
        it.split(" ").forEach {
            val key = it.split(":")[0]
            if (key != "cid") {
                fields.add(key)
            }
        }
    }
    return result
}

fun day4Second(inputLines: List<String>): Int {
    var fields = mutableSetOf<String>()
    var result = 0
    inputLines.forEach fore@{
        if (it.isEmpty()) {
            if (fields == requiredFields) {
                result++
            }
            fields = mutableSetOf()
            return@fore
        }
        it.split(" ").forEach fork@{
            val current = it.split(":")
            when {
                current[0] == "byr" -> {
                    //four digits; at least 1920 and at most 2002.
                    if (current[1].toInt() in 1920..2002) {
                        fields.add(current[0])
                    }
                }
                current[0] == "iyr" -> {
                    // four digits; at least 2010 and at most 2020.
                    if (current[1].toInt() in 2010..2020) {
                        fields.add(current[0])
                    }
                }
                current[0] == "eyr" -> {
                    //four digits; at least 2020 and at most 2030.
                    if (current[1].toInt() in 2020..2030) {
                        fields.add(current[0])
                    }
                }
                current[0] == "hgt" -> {
                    //a number followed by either cm or in:
                    //If cm, the number must be at least 150 and at most 193.
                    //If in, the number must be at least 59 and at most 76.
                    when {
                        current[1].takeLast(2) == "cm" -> {
                            if (current[1].removeSuffix("cm").toInt() in 150..193) {
                                fields.add(current[0])
                            }
                        }
                        current[1].takeLast(2) == "in" -> {
                            if (current[1].removeSuffix("in").toInt() in 59..76) {
                                fields.add(current[0])
                            }
                        }
                    }
                }
                current[0] == "hcl" -> {
                    //a # followed by exactly six characters 0-9 or a-f.
                    val regex = "#([0-9,a-f]{6})".toRegex()
                    if (regex.matches(current[1])) {
                        fields.add(current[0])
                    }
                }
                current[0] == "ecl" -> {
                    //exactly one of: amb blu brn gry grn hzl oth.
                    val ecl = setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
                    if (ecl.contains(current[1])) {
                        fields.add(current[0])
                    }
                }
                current[0] == "pid" -> {
                    //a nine-digit number, including leading zeroes.
                    val regex = "([0-9]{9})".toRegex()
                    if (regex.matches(current[1])) {
                        fields.add(current[0])
                    }
                }
                current[0] == "cid" -> {
                    return@fork
                }
            }
        }
    }
    return result
}