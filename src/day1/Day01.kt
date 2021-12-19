package day1

import readInput

//https://adventofcode.com/2021/day/1

fun main() {
    fun part1(input: List<String>): Int {
        var previous = input[0].toInt()
        return input
                .map {
                    it.toInt()
                }
                .drop(1)
                .foldIndexed(0) { index, acc, depth ->
                    if (depth > previous) {
                        acc + 1
                    } else {
                        acc
                    }.also {
                        previous = depth
                    }
                }
    }

    fun part2(input: List<String>): Int {
        fun tripleDepthIndex(index: Int): Int {
            return input[index].toInt() + input[index + 1].toInt() + input[index + 2].toInt()
        }

        var previous: Int = tripleDepthIndex(0)
        return input
                .drop(1)
                .dropLast(1)
                .foldIndexed(0) { index, acc, depth ->
                    val tripleDepth = tripleDepthIndex(index)
                    if (tripleDepth > previous) {
                        acc + 1
                    } else {
                        acc
                    }.also {
                        previous = tripleDepth
                    }
                }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day1/Day01_test")
    val out = part1(testInput)
    check(out == 7)

    val input = readInput("day1/Day01")
    println(part1(input))
    println(part2(input))
}
