package day2

import readInput

//https://adventofcode.com/2021/day/2

fun main() {
    fun part1(input: List<String>): Int {
        var horizontalPosition = 0
        var depth = 0
        input.forEach {
            val instruction = it.split(" ")
            val direction = instruction[0]
            val quantity = instruction[1].toInt()
            when (direction) {
                "forward" -> {
                    horizontalPosition += quantity
                }
                "down" -> {
                    depth += quantity
                }
                "up" -> {
                    depth -= quantity
                }
            }
        }
        return horizontalPosition * depth
    }

    fun part2(input: List<String>): Int {
        var horizontalPosition = 0
        var depth = 0
        var aim = 0
        input.forEach {
            val instruction = it.split(" ")
            val direction = instruction[0]
            val quantity = instruction[1].toInt()
            when (direction) {
                "forward" -> {
                    horizontalPosition += quantity
                    depth += aim * quantity
                }
                "down" -> {
                    aim += quantity
                }
                "up" -> {
                    aim -= quantity
                }
            }
        }
        return horizontalPosition * depth
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day2/Day02_test")
    val out1 = part1(testInput)
    check(out1 == 150)

    val out2 = part2(testInput)
    check(out2 == 900)

    val input = readInput("day2/Day02")
    println(part1(input))
    println(part2(input))
}
