import java.util.LinkedList;
import java.util.Queue;
//using bfs
class BattleshipClusters {
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 1, 0, 0},
            {1, 0, 0, 0},
            {1, 0, 1, 1},
            {1, 0, 0, 0}
        };

        int clusters = findBattleshipClusters(matrix);
        System.out.println(clusters + " cluster(s)");
    }

    private static int findBattleshipClusters(int[][] matrix) {
        int clusters = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 1 && !visited[i][j]) {
                    bfs(matrix, visited, i, j);
                    clusters++;
                }
            }
        }

        return clusters;
    }

    private static void bfs(int[][] matrix, boolean[][] visited, int startRow, int startCol) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] rowOffsets = { -1, 1, 0, 0 }; // Up, Down, Left, Right
        int[] colOffsets = { 0, 0, -1, 1 }; // Up, Down, Left, Right

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { startRow, startCol });
        visited[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];

            for (int i = 0; i < 4; i++) {
                int newRow = row + rowOffsets[i];
                int newCol = col + colOffsets[i];

                if (isValidPosition(newRow, newCol, rows, cols) && !visited[newRow][newCol] && matrix[newRow][newCol] == 1) {
                    queue.offer(new int[] { newRow, newCol });
                    visited[newRow][newCol] = true;
                }
            }
        }
    }

    private static boolean isValidPosition(int row, int col, int rows, int cols) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
}
