package tech.relialab

import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.max
import kotlin.math.min

fun grid(path: String): List<MutableList<Char>> = buildCharGrid(readInput(path))

fun readInput(name: String) = Path("src/main/resources/$name/input").readLines()

fun readTestInput(name: String) = Path("src/main/resources/$name/test").readLines()

private fun buildCharGrid(lines: List<String>): List<MutableList<Char>> {
    return lines.map { it.toMutableList() }
}

fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

fun Any?.println() = println(this)

fun mergeRanges(ranges: List<LongRange>): List<LongRange>  {
    val sorted = ranges.sortedBy { it.first() }
    val acc = mutableListOf<LongRange>()
    acc.add(sorted.first())
    for (range in sorted) {
        val prev = acc.last()
        if (max(prev.first(), range.first()) <= min(prev.last(), range.last())) {
            acc[acc.lastIndex] = LongRange(
                min(prev.first(), range.first()),
                max(prev.last(), range.last())
            )
        } else {
            acc.add(range)
        }
    }
    return acc
}