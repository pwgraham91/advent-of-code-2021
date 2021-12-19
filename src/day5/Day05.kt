package day5

import readInput
import java.lang.Integer.min
import kotlin.math.abs
import kotlin.math.max

// https://adventofcode.com/2021/day/5

fun main() {
    fun part1(input: List<String>): Int {
        val grid = mutableMapOf<Int, MutableMap<Int, Int>>()
        input.map {
            it.split(" -> ")
        }.forEach {
            val from = it[0].split(",")
            val x1 = from[0].toInt()
            val y1 = from[1].toInt()

            val to = it[1].split(",")
            val x2 = to[0].toInt()
            val y2 = to[1].toInt()

            var fromX = x1
            var fromY = y1
            var toX = x2
            var toY = y2
            if (x1 == x2) {
                fromY = min(y1, y2)
                toY = max(y1, y2)

            } else if (y1 == y2) {
                fromX = min(x1, x2)
                toX = max(x1, x2)

            } else {
                // diagonal
                return@forEach
            }


            for (y in fromY..toY) {
                for (x in fromX..toX) {
                    if (grid[y] == null) {
                        grid[y] = mutableMapOf()
                    }
                    val value = grid[y]?.get(x)
                    if (value == null) {
                        grid[y]!![x] = 1
                    } else {
                        grid[y]!![x] = value + 1
                    }
                }
            }
        }
        var counter = 0
        for ((rowIndex, row) in grid.entries) {
            for ((columnIndex, cross) in row.entries) {
                if (cross >= 2) {
                    counter += 1
                }
            }
        }
        return counter
    }

    fun part2(input: List<String>): Int {
        val grid = mutableMapOf<Int, MutableMap<Int, Int>>()
        input.map {
            it.split(" -> ")
        }.forEach {
            val from = it[0].split(",")
            val x1 = from[0].toInt()
            val y1 = from[1].toInt()

            val to = it[1].split(",")
            val x2 = to[0].toInt()
            val y2 = to[1].toInt()

            var fromX = x1
            var fromY = y1
            var toX = x2
            var toY = y2

            if (x1 == x2 || y1 == y2) {
                if (x1 == x2) {
                    fromY = min(y1, y2)
                    toY = max(y1, y2)

                } else if (y1 == y2) {
                    fromX = min(x1, x2)
                    toX = max(x1, x2)
                }

                for (y in fromY..toY) {
                    for (x in fromX..toX) {
                        if (grid[y] == null) {
                            grid[y] = mutableMapOf()
                        }
                        val value = grid[y]?.get(x)
                        if (value == null) {
                            grid[y]!![x] = 1
                        } else {
                            grid[y]!![x] = value + 1
                        }
                    }
                }

            } else {
                fromX = x1
                fromY = y1

                repeat(abs(x2 - x1) + 1) {
                    val x = fromX + (it * if (x2 > x1) 1 else -1)
                    val y = fromY + (it * if (y2 > y1) 1 else -1)
                    if (grid[y] == null) {
                        grid[y] = mutableMapOf()
                    }
                    val value = grid[y]?.get(x)
                    if (value == null) {
                        grid[y]!![x] = 1
                    } else {
                        grid[y]!![x] = value + 1
                    }
                }
            }
        }

        var counter = 0
        for ((rowIndex, row) in grid.entries) {
            for ((columnIndex, cross) in row.entries) {
                if (cross >= 2) {
                    counter += 1
                }
            }
        }
        return counter
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("day5/test")
    val input = readInput("day5/input")
    println(part2(input))
}
