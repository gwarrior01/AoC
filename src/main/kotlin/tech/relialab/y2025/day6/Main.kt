package tech.relialab.y2025.day6

import tech.relialab.grid
import tech.relialab.readInput

fun main() {
    val input = readInput("y2025/day6")
    val numbers = input.subList(0, input.lastIndex)
        .map { it.split(" ") }.map { row -> row.filter { it.isNotBlank() }.map { it.toInt()} }
    val operations = input.last().split(" ").filter { it.isNotBlank() }

    println("Part1: ${part1(numbers, operations)}")
    println("Part2: ${part2(numbers, operations)}")
}

fun part1(numbers: List<List<Int>>, operations: List<String>): Long {
    var total = 0L
    for (problem in operations.indices) {
        var sum = 0L
        var mul = 1L
        for (row in numbers) {
            if (operations[problem] == "+") sum += row[problem] else mul *= row[problem]
        }
        total += if (operations[problem] == "+") sum else mul
    }
    return total
}

fun part2(numbers: List<List<Int>>, operations: List<String>): Long {
    var total = 0L
    val problemWidths = numbers.first().indices.map { col ->
        numbers.maxOf { it[col] }.toString().length
    }
    val grid = grid("y2025/day6")
    var currProblemStart = 0
    for (problem in problemWidths.indices) {
        var sum = 0L
        var mul = 1L
        val width = problemWidths[problem]
        for (offset in 0 until width) {
            val value = buildString {
                for (row in 0 until grid.size - 1) {
                    val col = currProblemStart + offset
                    if (col >= grid[row].size) continue
                    val curr = grid[row][col]
                    if (curr.isDigit()) append(curr)
                }
            }.toLong()
            if (operations[problem] == "+") sum += value else mul *= value
        }
        total += if (operations[problem] == "+") sum else mul
        currProblemStart += width + 1
    }
    return total
}