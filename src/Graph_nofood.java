import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Graph2 {
    private int numNodes;
    private List<Integer>[] adjacencyList;

    public Graph2(int numNodes) {
        this.numNodes = numNodes;
        adjacencyList = new List[numNodes + 1];
        for (int i = 1; i <= numNodes; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
    }

    public void addEdge(int from, int to) {
        adjacencyList[from].add(to);
        adjacencyList[to].add(from);
    }

    public List<Integer> findPath(int noFoodNode) {
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[numNodes + 1];

        // Start the search from Node 1
        dfs(1, noFoodNode, visited, path);

        // Add Node 1 to complete the cycle
        path.add(1);

        return path;
    }

    private boolean dfs(int currentNode, int noFoodNode, boolean[] visited, List<Integer> path) {
        visited[currentNode] = true;
        path.add(currentNode);

        // Check if all nodes have been visited (except the noFoodNode)
        boolean allVisited = true;
        for (int i = 2; i <= numNodes; i++) {
            if (i != noFoodNode && !visited[i]) {
                allVisited = false;
                break;
            }
        }

        // If all nodes have been visited (except noFoodNode), return true to complete the cycle
        if (allVisited) {
            return true;
        }

        for (int neighbor : adjacencyList[currentNode]) {
            if (!visited[neighbor]) {
                // If the neighbor is the noFoodNode, skip it
                if (neighbor == noFoodNode) {
                    continue;
                }

                if (dfs(neighbor, noFoodNode, visited, path)) {
                    return true;
                }
            }
        }

        // Backtrack if no path is found
        visited[currentNode] = false;
        path.remove(path.size() - 1);
        return false;
    }

    public static void main(String[] args) {
        Graph2 graph = new Graph2(10);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 6);
        graph.addEdge(1, 10);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(3, 7);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);
        graph.addEdge(6, 8);
        graph.addEdge(7, 8);
        graph.addEdge(7, 9);
        graph.addEdge(8, 9);
        graph.addEdge(8, 10);
        graph.addEdge(9, 10);
        
        System.out.println("Node with no foods:");
        Scanner scanner = new Scanner(System.in);
        int noFoodNode = scanner.nextInt();

        List<Integer> path = graph.findPath(noFoodNode);

        // Example output
        System.out.println("Path:");
        for (int node : path) {
            System.out.print(node + " -> ");
        }
        System.out.println();
    }
}
