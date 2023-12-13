package org.alex123411.interviewprep.Solution;

public class Graphs {

    public void dfs(int[][] graph, int i, int j) {
        // i -> rows
        // j -> cols
        if (i < 0 || i > graph.length ||
                j < 0 || j > graph[0].length) return;

        // can add some condition hear to return whenever wae want;

        dfs(graph,i,j+1);
        dfs(graph,i,j-1);
        dfs(graph,i-1,j);
        dfs(graph,i+1,j);
    }

    public void bfs(int[][] graph, int i, int j) {

    }

    // 200. Number of Islands
    // https://leetcode.com/problems/number-of-islands/
    public int numIslands(char[][] grid) {
        if (grid == null) return 0;
        int rows = grid.length;
        int cols = grid[0].length;

        int res = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    numIslandsDFS(grid, r, c);
                    res++;
                }
            }
        }

        return res;
    }

    public void numIslandsDFS(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 ||
                i >= grid.length ||
                j >= grid[0].length ||
                grid[i][j] == '0'
        ) return;
        grid[i][j] = '0';
        numIslandsDFS(grid, i + 1, j);
        numIslandsDFS(grid, i - 1, j);
        numIslandsDFS(grid, i, j + 1);
        numIslandsDFS(grid, i, j - 1);
    }

}
