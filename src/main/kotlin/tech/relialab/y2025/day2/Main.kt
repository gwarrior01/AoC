package tech.relialab.y2025.day2

import tech.relialab.readInput

fun main() {
    var sum1 = 0L
    var sum2 = 0L
    val input = readInput("y2025/day2")

    input.first().split(",").forEach {
        val ranges = it.split("-")
        for (curr in ranges.first().toLong()..ranges.last().toLong()) {
            if (repeatedTwice(curr.toString())) {
                sum1 += curr
            }
            if (repeatedAtLeatTwice(curr.toString())) {
                sum2 += curr
            }
        }
    }
    println("Part1: $sum1")
    println("Part2: $sum2")
}

private fun repeatedTwice(s: String): Boolean {
    if (s.length % 2 == 1) return false
    val first = s.take(s.length / 2)
    val second = s.drop(s.length / 2)
    return first == second
}

private fun repeatedAtLeatTwice(s: String): Boolean {
    val length = s.length

    for (i in 1..(length / 2)) {
        if (length % i == 0) {
            val block = s.take(i)
            val repeats = length / i

            val candidate = buildString {
                repeat(repeats) { append(block) }
            }

            if (candidate == s) {
                return true
            }
        }
    }
    return false
}