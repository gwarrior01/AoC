package tech.relialab.y2025.day5

import tech.relialab.mergeRanges
import tech.relialab.readInput

fun main() {
    val input = readInput("y2025/day5")
    val ranges = input.takeWhile { it.isNotBlank() }
        .map {
            val boundaries = it.split("-")
            LongRange(boundaries[0].toLong(), boundaries[1].toLong())
        }
    val ingredients = input.takeLastWhile { it.isNotBlank() }.map { it.toLong() }
    val merged = mergeRanges(ranges)

    val res1 = ingredients.count { id -> merged.any { range -> id in range } }
    val res2 = merged.sumOf { it.last - it.first + 1 }

    println("Part1: $res1")
    println("Part1: $res2")
}