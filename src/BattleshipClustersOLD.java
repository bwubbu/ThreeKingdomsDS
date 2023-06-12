
    public class BattleshipClustersOLD {
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 1, 0, 0},
            {1, 0, 0, 0},
            {1, 0, 1, 1},
            {1, 0, 0, 0}
        };

        int clusters = findBattleshipClusters(matrix);
        System.out.println(clusters + " cluster");
    }

    private static int findBattleshipClusters(int[][] matrix) {
        int clusters = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 1 && !visited[i][j]) {
                    exploreCluster(matrix, visited, i, j);
                    clusters++;
                }
            }
        }

        return clusters;
    }

    private static void exploreCluster(int[][] matrix, boolean[][] visited, int row, int col) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        if (row < 0 || row >= rows || col < 0 || col >= cols || visited[row][col] || matrix[row][col] == 0) {
            return;
        }

        visited[row][col] = true;

        exploreCluster(matrix, visited, row - 1, col); // Up
        exploreCluster(matrix, visited, row + 1, col); // Down
        exploreCluster(matrix, visited, row, col - 1); // Left
        exploreCluster(matrix, visited, row, col + 1); // Right
    }
}

