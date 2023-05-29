import java.util.ArrayList;
import java.util.List;

public class PathFinder {
    private static int[][] maze = {
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

    private static List<int[]> path = new ArrayList<>();

    public static void main(String[] args) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        int[] start = findStartPoint(maze);

        if (start != null) {
            boolean foundPath = solveMaze(start[0], start[1], visited);

            if (foundPath) {
                System.out.println("Path to exit:");
                printMazeWithPath();
            } else {
                System.out.println("No path to exit found.");
            }
        } else {
            System.out.println("No starting point found.");
        }
    }

    private static boolean solveMaze(int row, int col, boolean[][] visited) {
        // Check if current position is out of bounds or is a wall
        if (row < 0 || row >= maze.length || col < 0 || col >= maze[0].length || maze[row][col] == 1 || visited[row][col]) {
            return false;
        }

        // Mark current position as visited
        visited[row][col] = true;

        // Check if the exit is found
        if (maze[row][col] == 3) {
            path.add(new int[]{row, col});
            return true;
        }

        // Explore neighboring positions in a depth-first manner
        if (solveMaze(row - 1, col, visited) ||   // Up
                solveMaze(row + 1, col, visited) ||   // Down
                solveMaze(row, col - 1, visited) ||   // Left
                solveMaze(row, col + 1, visited)) {   // Right
            path.add(new int[]{row, col});
            return true;
        }

        return false;
    }

    private static int[] findStartPoint(int[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == 2) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    private static void printMazeWithPath() {
        System.out.println("note: 9 indicates the path to exit");
        System.out.println("");
        int[][] pathMaze = new int[maze.length][maze[0].length];

        // Copy the maze to the pathMaze
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                pathMaze[i][j] = maze[i][j];
            }
        }

        // Mark the path with '9'
        for (int[] point : path) {
            pathMaze[point[0]][point[1]] = 9;
        }

        // Print the pathMaze
        for (int i = 0; i < pathMaze.length; i++) {
            for (int j = 0; j < pathMaze[i].length; j++) {
                System.out.print(pathMaze[i][j] + " ");
            }
            System.out.println();
        }
    }
}

