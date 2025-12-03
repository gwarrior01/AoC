package tech.relialab.y2025.day1

import java.io.File

fun main() {
    println("Part1: ${part1()}")
    println("Part2: ${part2()}")
}

fun part1(): Int {
    var start = 50
    return File("/Users/great_warrior/IdeaProjects/AoC/src/main/resources/y2025/day1/input").useLines {
        it.count { line ->
            when (line.first()) {
                'L' -> start -= line.drop(1).toInt()
                'R' -> start += line.drop(1).toInt()
            }
            start = start.mod(100)
            start == 0
        }
    }
}

fun part2(): Int {
    var start = 50
    var pass = 0
    File("/Users/great_warrior/IdeaProjects/AoC/src/main/resources/y2025/day1/input").useLines {
        it.forEach { line ->
            val init = start
            var count = line.drop(1).toInt()
            if (count > 99) {
                pass += count / 100
            }
            count %= 100
            when (line.first()) {
                'L' -> start -= count
                'R' -> start += count
            }
            if (start !in 1..99 && init != 0) {
                pass += 1
            }

            start = start.mod(100)
        }
    }
    return pass
}