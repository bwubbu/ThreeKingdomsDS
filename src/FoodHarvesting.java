import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FoodHarvesting extends EnemyAttackFortressSimulation{
    private final int numNodes;
    private final List<Integer>[] adjacencyList;
    private boolean[] visited;

    public FoodHarvesting(int numNodes) {
        this.numNodes = numNodes;
        adjacencyList = new List[numNodes + 1];
        for (int i = 1; i <= numNodes; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
        visited = new boolean[numNodes + 1]; // Initialize the visited array
    }

    public void addEdge(int from, int to) {
        adjacencyList[from].add(to);
        adjacencyList[to].add(from);
    }

    private boolean DFS(int currentNode, List<Integer> noFoodNodes, List<Integer> path, boolean includeNoFoodNode) {
        visited[currentNode] = true;
        path.add(currentNode);

        // Check if all nodes have been visited (excluding the noFoodNodes)
        boolean allVisited = true;
        for (int i = 2; i <= numNodes; i++) {
            if (!noFoodNodes.contains(i) && !visited[i]) {
                allVisited = false;
                break;
            }
        }

        // If all nodes have been visited (excluding noFoodNodes), return true to complete the cycle
        if (allVisited) {
            return true;
        }

        for (int neighbor : adjacencyList[currentNode]) {
            if (!visited[neighbor]) {
                // Skip the neighbor if it is one of the noFoodNodes (unless it is essential for connectivity)
                if (noFoodNodes.contains(neighbor) && !includeNoFoodNode) {
                    continue;
                }

                if (DFS(neighbor, noFoodNodes, path, includeNoFoodNode)) {
                    return true;
                }
            }
        }

        // Backtrack if no path is found
        visited[currentNode] = false;
        path.remove(path.size() - 1);
        return false;
    }

    public List<Integer> findPath(List<Integer> noFoodNodes) {
        List<Integer> path = new ArrayList<>();

        // Start the search from Node 1 without including any node with no food
        boolean pathFound = DFS(1, noFoodNodes, path, false);

        // If a path is not found without including any node with no food, try again including the node(s) with no food
        if (!pathFound && noFoodNodes.size() > 0) {
            visited = new boolean[numNodes + 1]; // Reset visited array
            path.clear(); // Clear the path
            pathFound = DFS(1, noFoodNodes, path, true);
        }

        // Add Node 1 to complete the cycle
        path.add(1);

        return pathFound ? path : new ArrayList<>(); // Return an empty list if a path is not found
    }

    public static void main(String[] args) {
        FoodHarvesting graph = new FoodHarvesting(10);
        graph.addEdgesToGraph();
        
        System.out.println("Nodes with no food (enter '0' to finish):");
        Scanner scanner = new Scanner(System.in);
        List<Integer> noFoodNodes = new ArrayList<>();
        int noFoodNode;
        while ((noFoodNode = scanner.nextInt()) != 0) {
            noFoodNodes.add(noFoodNode);
        }

        List<Integer> path = graph.findPath(noFoodNodes);

        // Example output
        System.out.println("Path:");
        for (int node : path) {
            System.out.print(node + " -> ");
        }
        System.out.println();
    }
}
