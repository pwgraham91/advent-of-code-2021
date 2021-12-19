package day6

import readInput
import java.lang.Integer.min
import kotlin.math.abs
import kotlin.math.max

// https://adventofcode.com/2021/day/6

fun main() {
    fun part1(input: List<String>): Int {
        var lanternFish = input[0].split(",").map { it.toInt() }.toMutableList()
        repeat(80) {
            val newLanternFish = mutableListOf<Int>()
            lanternFish = (lanternFish.map {
                if (it == 0) {
                    newLanternFish.add(8)
                    6
                } else {
                    it - 1
                }
            } + newLanternFish).toMutableList()
            println(it)
        }
        return lanternFish.size
    }

    fun part2(input: List<String>): Long {
        val fishCensus: MutableList<Long> = MutableList(9) { 0L }
        input[0].split(",").map { it.toInt() }.forEach {
            fishCensus[it] += 1L
        }

        repeat(256) {
            var spawningFish = 0L
            fishCensus.forEachIndexed { index, numFish ->
                if (index == 0) {
                    spawningFish = numFish
                }
                if (index <= 7) {
                    fishCensus[index] = fishCensus[index + 1]
                } else {
                    fishCensus[index] = 0L
                }
            }
            fishCensus[6] += spawningFish
            fishCensus[8] = spawningFish
        }
        return fishCensus.fold(0L) { acc, i ->
            acc + i

        }
    }


// test if implementation meets criteria from the description, like:
        val testInput = readInput("day6/test")
        val input = readInput("day6/input")
        println(part2(input))
}
