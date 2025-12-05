package tech.relialab.y2025.day4

import tech.relialab.grid

fun main() {
    val grid = grid("y2025/day4")
    println("Part1: ${part1(grid)}")
    println("Part1: ${part2(grid)}")
}

fun part1(grid: List<MutableList<Char>>) = iteration(grid)

fun part2(grid: List<MutableList<Char>>): Int {
    var res = 0
    while (true) {
        val curr = iteration(grid, true)
        res += curr
        if (curr == 0) break
    }
    return res
}

fun iteration(grid: List<MutableList<Char>>, removeForklift: Boolean = false): Int {
    var res = 0
    for (row in grid.indices) {
        for (col in grid[row].indices) {
            if (check(grid, row, col)) {
                res += 1
                if (removeForklift) grid[row][col] = '.'
            }
        }
    }
    return res
}

fun check(grid: List<MutableList<Char>>, row: Int, col: Int): Boolean {
    if (grid[row][col] == '.') return false
    var res = 0
    res += checkPosition(grid, row - 1, col)
    res += checkPosition(grid, row - 1, col - 1)
    res += checkPosition(grid, row - 1, col + 1)
    res += checkPosition(grid, row, col - 1)
    res += checkPosition(grid, row, col + 1)
    res += checkPosition(grid, row + 1, col)
    res += checkPosition(grid, row + 1, col - 1)
    res += checkPosition(grid, row + 1, col + 1)
    return res < 4
}

fun checkPosition(grid: List<MutableList<Char>>, row: Int, col: Int): Int {
    if (row < 0 || row >= grid.size || col < 0 || col >= grid[row].size) return 0
    return if (grid[row][col] == '@') 1 else 0
}