package tech.relialab

import java.io.File

fun grid(path: String): List<MutableList<Char>> {
    val list = File(path)
        .readLines()
        .toList()
    return buildCharGrid(list)
}

private fun buildCharGrid(lines: List<String>): List<MutableList<Char>> {
    return lines.map { it.toMutableList() }
}