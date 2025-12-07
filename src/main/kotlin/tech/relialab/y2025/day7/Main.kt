package tech.relialab.y2025.day7

import tech.relialab.grid

fun main() {
    val grid = grid("y2025/day7")
    val startRow = grid.indexOfFirst { row -> 'S' in row }
    val startCol = grid[startRow].indexOf('S')

    println("Part1: ${part1(grid, startRow, startCol)}")
    println("Part2: ${part2(grid, startRow, startCol)}")
}

fun part1(grid: List<List<Char>>, startRow: Int, startCol: Int): Int {
    val visited = Array(grid.size) { r ->
        Array(grid[r].size) { c -> grid[r][c] }
    }
    visited[startRow + 1][startCol] = '|'
    return (2 until visited.size).sumOf { row -> split(visited, row) }
}

fun split(visited: Array<Array<Char>>, row: Int): Int {
    var count = 0
    for (col in visited[row].indices) {
        if (visited[row - 1][col] == '|' && visited[row][col] == '^') {
            count += 1
            if (col > 0) visited[row][col - 1] = '|'
            if (col < visited[row].size - 1) visited[row][col + 1] = '|'
        } else if (visited[row - 1][col] == '|') {
            visited[row][col] = '|'
        }
    }
    return count
}

fun part2(grid: List<List<Char>>, startRow: Int, startCol: Int) =
    dfs(grid, startRow + 2, startCol, Array(grid.size + 1) { Array(grid[0].size + 1) { 0L } })

fun dfs(grid: List<List<Char>>, row: Int, col: Int, cache: Array<Array<Long>>): Long {
    if (row == grid.size) return 1
    if (col < 0 || col == grid[row].size) return 0
    if (cache[row][col] != 0L) return cache[row][col]
    return if (grid[row][col] == '^') {
        val left = dfs(grid, row + 1, col - 1, cache)
        cache[row + 1][col - 1] = left
        val right = dfs(grid, row + 1, col + 1, cache)
        cache[row + 1][col + 1] = right

        left + right
    } else {
        val down = dfs(grid, row + 1, col, cache)
        cache[row + 1][col] = down

        down
    }
}
