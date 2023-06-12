import java.util.*;

class MazeEscape {
    public static void main(String[] args) {
        int[][] maze = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {2, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 3},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };

        findPath(maze);
    }

    private static void findPath(int[][] maze) {
        int startRow = -1;
        int startCol = -1;

        // Find the starting position
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 2) {
                    startRow = i;
                    startCol = j;
                    break;
                }
            }
        }

        // Perform A* search to find the path
        if (startRow != -1 && startCol != -1) {
            List<Node> path = aStarSearch(maze, startRow, startCol);
            if (path != null) {
                System.out.println("Path found!");
                markPathInMaze(maze, path);
                printMaze(maze);
            } else {
                System.out.println("No path found.");
            }
        } else {
            System.out.println("Starting position not found.");
        }
    }

    private static List<Node> aStarSearch(int[][] maze, int startRow, int startCol) {
        int numRows = maze.length;
        int numCols = maze[0].length;
        int[][] distance = new int[numRows][numCols];
        boolean[][] visited = new boolean[numRows][numCols];
        Node[][] parent = new Node[numRows][numCols];
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.f));

        for (int i = 0; i < numRows; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }

        distance[startRow][startCol] = 0;
        queue.offer(new Node(startRow, startCol, 0, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            int row = current.row;
            int col = current.col;

            if (maze[row][col] == 3) {
                // Exit found, reconstruct the path
                List<Node> path = new ArrayList<>();
                while (parent[row][col] != null) {
                    path.add(0, parent[row][col]);
                    Node prev = parent[row][col];
                    row = prev.row;
                    col = prev.col;
                }
                return path;
            }

            visited[row][col] = true;

            // Explore neighboring positions
            exploreNeighbor(maze, distance, visited, parent, queue, row - 1, col, current, 1); // Up
            exploreNeighbor(maze, distance, visited, parent, queue, row + 1, col, current, 1); // Down
            exploreNeighbor(maze, distance, visited, parent, queue, row, col - 1, current, 1); // Left
            exploreNeighbor(maze, distance, visited, parent, queue, row, col + 1, current, 1); // Right
        }

        return null; // No path found
    }

    private static void exploreNeighbor(int[][] maze, int[][] distance, boolean[][] visited, Node[][] parent,
            PriorityQueue<Node> queue, int row, int col, Node current, int cost) {
        int numRows = maze.length;
        int numCols = maze[0].length;

        if (row >= 0 && row < numRows && col >= 0 && col < numCols && maze[row][col] != 1 && !visited[row][col]) {
            int newDistance = current.g + cost;

            if (newDistance < distance[row][col]) {
                distance[row][col] = newDistance;
                parent[row][col] = current;
                int h = calculateHeuristic(row, col, maze);
                queue.offer(new Node(row, col, newDistance, newDistance + h));
            }
        }
    }

    private static int calculateHeuristic(int row, int col, int[][] maze) {
        int exitRow = -1;
        int exitCol = -1;

        // Find the exit position
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 3) {
                    exitRow = i;
                    exitCol = j;
                    break;
                }
            }
        }

        // Calculate the Manhattan distance
        return Math.abs(row - exitRow) + Math.abs(col - exitCol);
    }

    private static void markPathInMaze(int[][] maze, List<Node> path) {
        for (Node node : path) {
            int row = node.row;
            int col = node.col;
            if (maze[row][col] != 2 && maze[row][col] != 3) {
                maze[row][col] = 9; // Mark the path with 9
            }
        }
    }

    private static void printMaze(int[][] maze) {
        for (int[] row : maze) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    private static class Node {
        int row;
        int col;
        int g; // Cost from the start node
        int f; // g + heuristic

        Node(int row, int col, int g, int f) {
            this.row = row;
            this.col = col;
            this.g = g;
            this.f = f;
        }
    }
}
