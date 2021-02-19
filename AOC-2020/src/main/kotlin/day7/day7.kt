package day7

import java.io.File

fun main() {
    val inputLines = File("src/main/kotlin/day7/day7_input.txt").readLines()
    println(day7(inputLines))
    println(day72(inputLines))
}

data class Bag(val name: String, val whatsInside: List<String> = arrayListOf())

val names = mutableSetOf<String>()

fun day7(inputLines: List<String>): Int {
    val bags = mutableListOf<Bag>()
    val shinyIndexes = mutableListOf<Int>()
    inputLines.filter { it ->
        !it.contains("contain no other bags.")
    }.forEach {
        val tmp = it.split(" bags contain ")
        val contains = tmp[1].split(", ")
        val bag = Bag(name = tmp[0], whatsInside = contains)
        contains.forEach {
            if (it.contains("shiny gold")) {
                shinyIndexes.add(bags.size)
            }
        }
        bags.add(bag)
    }
    shinyIndexes.forEach {
        calculateBags(bags[it].name, bags)
    }
    return names.size
}

fun calculateBags(name: String, bags: List<Bag>) {
    names.add(name)
    bags.forEach { bag ->
        bag.whatsInside.forEach {
            if (it.contains(name)) {
                calculateBags(bag.name, bags)
            }
        }
    }
}

fun day72(inputLines: List<String>): Int {
    val bags = mutableListOf<Bag>()
    var shinyIndex = 0
    inputLines.filter { it ->
        !it.contains("contain no other bags.")
    }.forEach {
        val tmp = it.split(" bags contain ")
        val contains = tmp[1].split(", ")
        val bag = Bag(name = tmp[0], whatsInside = contains)
        if (bag.name == "shiny gold") {
            shinyIndex = bags.size
        }
        bags.add(bag)
    }
    return calculateShiny(shinyIndex, bags) - 1
}

data class Part(val count: Int, val name: String)
fun calculateShiny(index: Int, bags: List<Bag>): Int{
    var tmp = 1
    val bag = bags[index]
    bag.whatsInside.forEach {
        val parts = it.split(" ")
        val part = Part(count = parts[0].toInt(), parts[1] + " " + parts[2])
        var tmp2 = part.count
        bags.forEachIndexed() { index, bag ->
            if (bag.name == part.name) {
                tmp2 *= calculateShiny(index, bags)
            }
        }
        tmp += tmp2
    }
    return tmp
}