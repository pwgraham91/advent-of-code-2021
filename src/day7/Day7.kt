package day7

import readInput
import kotlin.math.abs

// https://adventofcode.com/2021/day/7

fun main() {
    fun moveToColumn(crabPositions: List<Int>, position: Int): Int {
        return crabPositions.fold(0) { acc, i ->
            acc + abs(i - position)
        }
    }

    fun moveToColumnRisingFuel(crabPositions: List<Int>, position: Int): Int {
        return crabPositions.fold(0) { acc, i ->
            var crabFuel = 0
            val moves = abs(i - position) - 1
            for (move in 0 .. moves) {
                crabFuel += move + 1

            }
            acc + crabFuel
        }
    }

    fun part1(input: List<String>): Int {
        val crabPositions = input[0].split(",").map { it.toInt() }
        val minCrabPosition = crabPositions.minOrNull() ?: 0
        val maxCrabPosition = crabPositions.maxOrNull() ?: 0
        var minFuel: Int? = null
        for (position in  minCrabPosition .. maxCrabPosition) {
            val fuelUsed = moveToColumn(crabPositions, position)
            if (minFuel == null) {
                minFuel = fuelUsed
            } else if (fuelUsed < minFuel) {
                minFuel = fuelUsed
            }
        }
        return minFuel!!
    }

    fun part2(input: List<String>): Int {
        val crabPositions = input[0].split(",").map { it.toInt() }
        val minCrabPosition = crabPositions.minOrNull() ?: 0
        val maxCrabPosition = crabPositions.maxOrNull() ?: 0
        var minFuel2: Int? = null
        for (position in  minCrabPosition .. maxCrabPosition) {
            val fuelUsed = moveToColumnRisingFuel(crabPositions, position)
            if (minFuel2 == null) {
                minFuel2 = fuelUsed
            } else if (fuelUsed < minFuel2) {
                minFuel2 = fuelUsed
            }
        }
        return minFuel2!!
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("day7/test")
    val input = readInput("day7/input")
    println(part2(input))
}
