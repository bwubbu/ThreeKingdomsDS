public class BattleshipClustersDFS {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    public static int countBattleshipClusters(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 1 && !visited[i][j]) {
                    count++;
                    dfs(matrix, i, j, visited);
                }
            }
        }

        return count;
    }

    private static void dfs(int[][] matrix, int row, int col, boolean[][] visited) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        visited[row][col] = true;

        for (int[] direction : DIRECTIONS) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];

            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols
                    && matrix[newRow][newCol] == 1 && !visited[newRow][newCol]) {
                dfs(matrix, newRow, newCol, visited);
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 1, 0, 0},
                {1, 0, 0, 0},
                {1, 0, 1, 1},
                {1, 0, 0, 0}
        };

        int clusterCount = countBattleshipClusters(matrix);
        System.out.println(clusterCount + " cluster");
    }
}
