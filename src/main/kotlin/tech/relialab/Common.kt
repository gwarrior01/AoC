package tech.relialab

import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines

fun grid(path: String): List<MutableList<Char>> = buildCharGrid(readInput(path))

fun readInput(name: String) = Path("src/main/resources/$name/input").readLines()

private fun buildCharGrid(lines: List<String>): List<MutableList<Char>> {
    return lines.map { it.toMutableList() }
}

fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

fun Any?.println() = println(this)