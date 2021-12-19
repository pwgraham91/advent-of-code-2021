package day3

import readInput

// https://adventofcode.com/2021/day/3

fun main() {
    fun rateListToBinary(rateList: List<String>): Int {
        return Integer.parseInt(rateList.joinToString(""), 2)
    }
    fun part1(input: List<String>): Int {
        fun rateListToBinary(rateList: List<String>): Int {
            return Integer.parseInt(rateList.joinToString(""), 2)
        }

        val gammaRateList = input.fold(MutableList(input[0].length) { 0 }) { acc, bitList ->
            bitList.forEachIndexed { index, c ->
                if (c == '1') {
                    acc[index] += 1
                }
            }
            acc
        }.map {
            if (it >= input.size / 2) {
                "1"
            } else {
                "0"
            }
        }
        val gammaBinaryValue = rateListToBinary(gammaRateList)
        val epsilonBinaryValue = rateListToBinary(gammaRateList.map {
            if (it == "1") "0" else "1"
        })
        return gammaBinaryValue * epsilonBinaryValue
    }

    fun part2(input: List<String>): Int {
        fun commonBit2(remainingInputList: List<String>, index: Int, oxygen: Boolean): String {
            val common = remainingInputList.fold(0) { acc, bitList ->
                if (bitList[index] == '1') {
                    acc + 1
                } else acc
            }.let {
                if (it >= remainingInputList.size / 2.0) {
                    if (oxygen) "1" else "0"
                } else {
                    if (oxygen) "0" else "1"
                }
            }
            val remainingBitLists = remainingInputList.filter {
                it[index].toString() == common
            }
            return if (remainingBitLists.size == 1) {
                remainingBitLists.first()
            } else {
                commonBit2(remainingBitLists, index + 1, oxygen)
            }
        }

        val oBits = commonBit2(input, 0, true)
        val co2Bits = commonBit2(input, 0, false)
        val oBinary = Integer.parseInt(oBits, 2)
        val co2Binary = Integer.parseInt(co2Bits, 2)
        return oBinary * co2Binary
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day3/Day03_test")
    val input = readInput("day3/Day03")
    println(part1(testInput))
    println(part1(input))
    println(part2(input))
}
