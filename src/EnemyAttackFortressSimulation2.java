
import java.util.*;

public class  EnemyAttackFortressSimulation2{
    private Map<Integer, List<Integer>> adjacencyList;

    public EnemyAttackFortressSimulation2() {
        adjacencyList = new HashMap<>();
    }

    public void addEdge(int source, int destination) {
        List<Integer> neighbors = adjacencyList.getOrDefault(source, new ArrayList<>());
        neighbors.add(destination);
        adjacencyList.put(source, neighbors);
    }
    public double getTravelTime(int source, int destination, String terrain, String unit) {
        // Define the speeds for each unit and terrain
        Map<String, Double> speedMap = new HashMap<>();
        speedMap.put("Cavalry:flat", 2.0 * 3);
        speedMap.put("Cavalry:forest", 2.0 * 0.8);
        speedMap.put("Cavalry:swamp", 2.0 * 0.3);
        speedMap.put("Cavalry:plank", 2.0 * 0.5);
        speedMap.put("Archer:flat", 1.0 * 2);
        speedMap.put("Archer:forest", 1.0 * 1);
        speedMap.put("Archer:swamp", 1.0 * 2.5);
        speedMap.put("Archer:plank", 1.0 * 0.5);
        speedMap.put("Infantry:flat", 1.0 * 2);
        speedMap.put("Infantry:forest", 1.0 * 2.5);
        speedMap.put("Infantry:swamp", 1.0 * 1);
        speedMap.put("Infantry:plank", 1.0 * 0.5);

        // Calculate the travel time based on the unit and terrain
        double speed = speedMap.get(unit + ":" + terrain);
        double distance = 1.0; // Assuming all edges have a distance of 1 unit
        return distance / speed;
    }

    public List<List<Integer>> breadthFirstSearch(int start, int target, String unit) {
        Queue<Pair<Integer, List<Integer>>> queue = new LinkedList<>();
        queue.offer(new Pair<>(start, new ArrayList<>(Collections.singletonList(start))));

        List<List<Integer>> paths = new ArrayList<>();
        double shortestTravelTime = Double.MAX_VALUE; // Variable to track the shortest travel time

        while (!queue.isEmpty()) {
            Pair<Integer, List<Integer>> current = queue.poll();
            int node = current.getKey();
            List<Integer> path = current.getValue();

            if (node == target) {
                paths.add(path);
                double travelTime = calculateTravelTime(path, unit); // Calculate the travel time for the path
                shortestTravelTime = Math.min(shortestTravelTime, travelTime); // Update the shortest travel time
            }

            if (calculateTravelTime(path, unit) > shortestTravelTime) {
                // Skip exploring paths longer than the current shortest travel time
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

    private double calculateTravelTime(List<Integer> path, String unit) {
        double travelTime = 0.0;
        for (int i = 0; i < path.size() - 1; i++) {
            int source = path.get(i);
            int destination = path.get(i + 1);
            String terrain = getTerrain(source, destination);
            travelTime += getTravelTime(source, destination, terrain, unit);
        }
        return travelTime;
    }

    private String getTerrain(int source, int destination) {
        if ((source == 1 && destination == 2) || (source == 5 && destination == 7) || (source == 6 && destination == 7) || (source == 8 && destination == 10))
            return "forest";
        else if ((source == 2 && destination == 4) || (source == 3 && destination == 4) || (source == 4 && destination == 5) || (source == 8 && destination == 9))
            return "swamp";
        else if ((source == 3 && destination == 7) || (source == 6 && destination == 8))
            return "plank";
        else
            return "flat";
    }

    public static void main(String[] args) {
        System.out.println("Enter the base camp for the enemy base camp: ");
        EnemyAttackFortressSimulation2 graph = new EnemyAttackFortressSimulation2();

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

        List<List<Integer>> paths = graph.breadthFirstSearch(1, enemyBaseCamp, "Cavalry"); // Modify the unit type here

        double shortestTravelTime = Double.MAX_VALUE;
        List<List<Integer>> shortestPaths = new ArrayList<>();

        // Find the shortest travel time
        for (List<Integer> path : paths) {
            double travelTime = graph.calculateTravelTime(path, "Cavalry"); // Modify the unit type here
            if (travelTime < shortestTravelTime) {
                shortestTravelTime = travelTime;
            }
        }

        // Filter out paths that have the shortest travel time
        for (List<Integer> path : paths) {
            double travelTime = graph.calculateTravelTime(path, "Cavalry"); // Modify the unit type here
            if (travelTime == shortestTravelTime) {
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
