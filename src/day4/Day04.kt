package day4

import readInput

// https://adventofcode.com/2021/day/4

fun main() {
    fun evaluateScore(board: List<List<String>>, calledNumber: Int): Int {
        var counter = 0
        board.forEach { row ->
            row.forEach { number ->
                if (number != "X") {
                    counter += number.toInt()
                }
            }
        }
        return counter * calledNumber
    }

    fun evaluateWin(line: List<String>): Boolean {
        return line.joinToString("") == "XXXXX"
    }

    fun part1(input: List<String>): Int {
        val drawnNumbers = input[0].split(",")
        var boards = input.subList(2, input.size).chunked(6).map { it.subList(0, 5) }.map { board ->
            board.map { row ->
                row.trim().replace("  ", " ").split(" ")
            }
        }
        drawnNumbers.forEach { drawnNumber ->
            // update boards
            boards = boards.map { board ->
                board.map { row ->
                    row.map { box ->
                        if (box == drawnNumber) "X" else box
                    }
                }
            }
            boards.forEach { board ->
                // evaluate rows
                board.forEach { row ->
                    if (evaluateWin(row)) {
                        return evaluateScore(board, drawnNumber.toInt())
                    }
                }
                // evaluate columns
                repeat(board.size) { columnIndex ->
                    val column = mutableListOf<String>()
                    repeat(board.size) { rowIndex ->
                        column.add(board[rowIndex][columnIndex])
                    }
                    if (evaluateWin(column)) {
                        return evaluateScore(board, drawnNumber.toInt())
                    }
                }
            }
        }
        return -1
    }

    fun part2(input: List<String>): Int {
        val drawnNumbers = input[0].split(",")
        var boards = input.subList(2, input.size).chunked(6).map { it.subList(0, 5) }.map { board ->
            board.map { row ->
                row.trim().replace("  ", " ").split(" ")
            }
        }
        drawnNumbers.forEach { drawnNumber ->
            // update boards
            boards = boards.map { board ->
                board.map { row ->
                    row.map { box ->
                        if (box == drawnNumber) "X" else box
                    }
                }
            }.filter { board ->
                // evaluate rows
                board.forEach { row ->
                    if (evaluateWin(row)) {
                        if (boards.size == 1) {
                            return evaluateScore(board, drawnNumber.toInt())
                        }
                        return@filter false
                    }
                }
                // evaluate columns
                repeat(board.size) { columnIndex ->
                    val column = mutableListOf<String>()
                    repeat(board.size) { rowIndex ->
                        column.add(board[rowIndex][columnIndex])
                    }
                    if (evaluateWin(column)) {
                        if (boards.size == 1) {
                            return evaluateScore(board, drawnNumber.toInt())
                        }
                        return@filter false
                    }
                }
                true
            }
        }
        return -2
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day4/test")
    val input = readInput("day4/input")
    println(part1(testInput))
    println(part1(input))
    println(part2(input))
}
