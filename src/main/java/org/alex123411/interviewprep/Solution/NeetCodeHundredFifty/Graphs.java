package org.alex123411.interviewprep.Solution.NeetCodeHundredFifty;


import org.alex123411.interviewprep.Solution.Structures.Node;

import java.util.LinkedList;
import java.util.*;

public class Graphs {

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

    // 133. Clone Graph
    // https://leetcode.com/problems/clone-graph/
    public Node cloneGraph(Node node) {
        if (node == null) return null;

        Node res = new Node(node.val);
        Map<Integer, Node> map = new HashMap<>();

        cloneGraphBFS(node, res, map);

        return res;
    }

    public void cloneGraphBFS(Node node, Node res, Map<Integer, Node> map) {
        Queue<Node> queue = new LinkedList<>();

        queue.add(node);

        map.put(res.val, res);

        while (!queue.isEmpty()) {

            Node current = queue.poll();
            res = map.get(current.val);

            for (Node n : current.neighbors) {
                if (!map.containsKey(n.val)) {
                    Node newNode = new Node(n.val, new ArrayList<>());
                    map.put(n.val, newNode);
                    queue.add(n);
                }
                res.neighbors.add(map.get(n.val));
            }
        }
    }

    // 695. Max Area of Island
    // https://leetcode.com/problems/max-area-of-island/
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null) return 0;
        int rows = grid.length;
        int cols = grid[0].length;

        int max = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    max = Math.max(max, maxAreaOfIsland(grid, i, j));
                }
            }
        }
        return max;
    }

    public int maxAreaOfIsland(int[][] grid, int i, int j) {

        if (i < 0 || j < 0 || i > grid.length - 1 || j > grid[0].length - 1 || grid[i][j] == 0)
            return 0;

        grid[i][j] = 0;


        return (1 + maxAreaOfIsland(grid, i + 1, j) +
                maxAreaOfIsland(grid, i - 1, j) +
                maxAreaOfIsland(grid, i, j + 1) +
                maxAreaOfIsland(grid, i, j - 1));
    }

    // 417. Pacific Atlantic Water Flow
    // https://leetcode.com/problems/pacific-atlantic-water-flow/
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();

        return res;
    }
}