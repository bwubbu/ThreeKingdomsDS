import java.util.*;

public class EnemyAttackForetressSimulation {
    private Map<Integer, List<Integer>> adjacencyList;

    public EnemyAttackForetressSimulation() {
        adjacencyList = new HashMap<>();
    }

    public void addEdge(int source, int destination) {
        List<Integer> neighbors = adjacencyList.getOrDefault(source, new ArrayList<>());
        neighbors.add(destination);
        adjacencyList.put(source, neighbors);
    }

    public List<List<Integer>> breadthFirstSearch(int start, int target) {
        Queue<Pair<Integer, List<Integer>>> queue = new LinkedList<>();
        queue.offer(new Pair<>(start, new ArrayList<>(Collections.singletonList(start))));

        List<List<Integer>> paths = new ArrayList<>();
        int shortestPathLength = Integer.MAX_VALUE; // Variable to track the length of the shortest path

        while (!queue.isEmpty()) {
            Pair<Integer, List<Integer>> current = queue.poll();
            int node = current.getKey();
            List<Integer> path = current.getValue();

            if (node == target) {
                paths.add(path);
                shortestPathLength = path.size(); // Update the length of the shortest path
            }

            if (path.size() > shortestPathLength) {
                // Skip exploring paths longer than the current shortest path length
                continue;
            }

            List<Integer> neighbors = adjacencyList.getOrDefault(node, new ArrayList<>());
            for (int neighbor : neighbors) {
                if (!path.contains(neighbor)) {
                    List<Integer> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.offer(new Pair<>(neighbor, newPath));
                }
            }
        }

        return paths;
    }

    public static void main(String[] args) {
        System.out.println("Enter the base camp for the enemy base camp: ");
        EnemyAttackForetressSimulation graph = new EnemyAttackForetressSimulation();

        // Adding edges to the graph
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 6);
        graph.addEdge(1, 10);
        graph.addEdge(2, 1);
        graph.addEdge(2, 4);
        graph.addEdge(3, 1);
        graph.addEdge(3, 4);
        graph.addEdge(3, 7);
        graph.addEdge(4, 2);
        graph.addEdge(4, 3);
        graph.addEdge(4, 5);
        graph.addEdge(5, 4);
        graph.addEdge(5, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 1);
        graph.addEdge(6, 5);
        graph.addEdge(6, 7);
        graph.addEdge(6, 8);
        graph.addEdge(7, 5);
        graph.addEdge(7, 6);
        graph.addEdge(7, 8);
        graph.addEdge(7, 9);
        graph.addEdge(8, 6);
        graph.addEdge(8, 7);
        graph.addEdge(8, 9);
        graph.addEdge(8, 10);
        graph.addEdge(9, 7);
        graph.addEdge(9, 8);
        graph.addEdge(9, 10);
        graph.addEdge(10, 1);
        graph.addEdge(10, 8);
        graph.addEdge(10, 9);


        Scanner scanner = new Scanner(System.in);
        int enemyBaseCamp = scanner.nextInt();

        List<List<Integer>> paths = graph.breadthFirstSearch(1, enemyBaseCamp);

        int shortestPathLength = Integer.MAX_VALUE;
        List<List<Integer>> shortestPaths = new ArrayList<>();

        // Find the shortest path length
        for (List<Integer> path : paths) {
            if (path.size() < shortestPathLength) {
                shortestPathLength = path.size();
            }
        }

        // Filter out paths that are not the shortest path
        for (List<Integer> path : paths) {
            if (path.size() == shortestPathLength) {
                shortestPaths.add(path);
            }
        }

        // Display the best paths
        System.out.println("Best paths:");
        for (List<Integer> path : shortestPaths) {
            StringBuilder sb = new StringBuilder();
            sb.append("1");
            for (int i = 1; i < path.size(); i++) {
                sb.append(" -> ").append(path.get(i));
            }
            System.out.println(sb.toString());
        }
    }
}
