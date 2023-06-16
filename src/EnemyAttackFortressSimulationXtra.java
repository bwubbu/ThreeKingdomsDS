import java.util.*;

public class EnemyAttackFortressSimulationXtra extends EnemyAttackFortressSimulation {

    private String getTerrain(int source, int destination) {
        // Define the terrain for each edge based on the given geographical conditions
        Map<String, List<String>> terrainMap = new HashMap<>();
        terrainMap.put("1:2", Collections.singletonList("flat"));
        terrainMap.put("1:3", Collections.singletonList("flat"));
        terrainMap.put("1:6", Collections.singletonList("flat"));
        terrainMap.put("1:10", Collections.singletonList("flat"));
        terrainMap.put("2:1", Collections.singletonList("forest"));
        terrainMap.put("2:4", Collections.singletonList("swamp"));
        terrainMap.put("3:1", Collections.singletonList("forest"));
        terrainMap.put("3:4", Collections.singletonList("swamp"));
        terrainMap.put("3:7", Collections.singletonList("plank"));
        terrainMap.put("4:2", Collections.singletonList("swamp"));
        terrainMap.put("4:3", Collections.singletonList("swamp"));
        terrainMap.put("4:5", Collections.singletonList("swamp"));
        terrainMap.put("5:4", Collections.singletonList("swamp"));
        terrainMap.put("5:6", Collections.singletonList("flat"));
        terrainMap.put("5:7", Collections.singletonList("flat"));
        terrainMap.put("6:1", Collections.singletonList("flat"));
        terrainMap.put("6:5", Collections.singletonList("flat"));
        terrainMap.put("6:7", Collections.singletonList("flat"));
        terrainMap.put("6:8", Collections.singletonList("flat"));
        terrainMap.put("7:5", Collections.singletonList("flat"));
        terrainMap.put("7:6", Collections.singletonList("flat"));
        terrainMap.put("7:8", Collections.singletonList("flat"));
        terrainMap.put("7:9", Collections.singletonList("flat"));
        terrainMap.put("8:6", Collections.singletonList("flat"));
        terrainMap.put("8:7", Collections.singletonList("flat"));
        terrainMap.put("8:9", Collections.singletonList("flat"));
        terrainMap.put("8:10", Collections.singletonList("flat"));
        terrainMap.put("9:7", Collections.singletonList("flat"));
        terrainMap.put("9:8", Collections.singletonList("flat"));
        terrainMap.put("9:10", Collections.singletonList("flat"));
        terrainMap.put("10:1", Collections.singletonList("flat"));
        terrainMap.put("10:8", Collections.singletonList("flat"));
        terrainMap.put("10:9", Collections.singletonList("flat"));

        String key = source + ":" + destination;
        return terrainMap.getOrDefault(key, Collections.singletonList("flat")).get(0);
    }

    private double getDistance(int source, int destination) {
        // Define the distance between nodes based on the given geographical conditions
        Map<String, Double> distanceMap = new HashMap<>();
        distanceMap.put("1:2", 10.0);
        distanceMap.put("1:3", 18.0);
        distanceMap.put("1:6", 20.0);
        distanceMap.put("1:10", 16.0);
        distanceMap.put("2:1", 10.0);
        distanceMap.put("2:4", 10.0);
        distanceMap.put("3:1", 18.0);
        distanceMap.put("3:4", 12.0);
        distanceMap.put("3:7", 28.0);
        distanceMap.put("4:2", 10.0);
        distanceMap.put("4:3", 12.0);
        distanceMap.put("4:5", 12.0);
        distanceMap.put("5:4", 12.0);
        distanceMap.put("5:6", 17.0);
        distanceMap.put("5:7", 10.0);
        distanceMap.put("6:1", 20.0);
        distanceMap.put("6:5", 17.0);
        distanceMap.put("6:7", 23.0);
        distanceMap.put("6:8", 35.0);
        distanceMap.put("7:5", 10.0);
        distanceMap.put("7:6", 23.0);
        distanceMap.put("7:8", 19.0);
        distanceMap.put("7:9", 17.0);
        distanceMap.put("8:6", 35.0);
        distanceMap.put("8:7", 19.0);
        distanceMap.put("8:9", 7.0);
        distanceMap.put("8:10", 12.0);
        distanceMap.put("9:7", 17.0);
        distanceMap.put("9:8", 7.0);
        distanceMap.put("9:10", 18.0);
        distanceMap.put("10:1", 16.0);
        distanceMap.put("10:8", 12.0);
        distanceMap.put("10:9", 18.0);

        String key = source + ":" + destination;
        return distanceMap.getOrDefault(key, Double.MAX_VALUE);
    }
    public double calculateTravelTime(List<Integer> path, String unit) {
        double travelTime = 0.0;

        for (int i = 0; i < path.size() - 1; i++) {
            int source = path.get(i);
            int destination = path.get(i + 1);

            String terrain = getTerrain(source, destination);
            double distance = getDistance(source, destination);

            travelTime += calculateTime(distance, unit, terrain);
        }

        return travelTime;
    }
    public double calculateTime(double distance, String unit, String terrain) {
        double speed;
        double terrainFactor;

        switch (unit) {
            case "Cavalry" -> {
                speed = 2.0;
                terrainFactor = switch (terrain) {
                    case "flat" -> 3.0;
                    case "forest" -> 0.8;
                    case "swamp" -> 0.3;
                    case "plank" -> 0.5;
                    default -> 1.0;
                };
            }
            case "Archer" -> {
                speed = 1.0;
                terrainFactor = switch (terrain) {
                    case "flat" -> 2.0;
                    case "forest" -> 1.0;
                    case "swamp" -> 2.5;
                    case "plank" -> 0.5;
                    default -> 1.0;
                };
            }
            case "Infantry" -> {
                speed = 1.0;
                terrainFactor = switch (terrain) {
                    case "flat" -> 2.0;
                    case "forest" -> 2.5;
                    case "swamp" -> 1.0;
                    case "plank" -> 0.5;
                    default -> 1.0;
                };
            }
            default -> {
                speed = 1.0;
                terrainFactor = 1.0;
            }
        }

        return distance / (speed * terrainFactor);
    }



    public static void main(String[] args) {
        System.out.println("Enter the base camp for the enemy base camp: ");
        EnemyAttackFortressSimulationXtra graph = new EnemyAttackFortressSimulationXtra();
        graph.addEdgesToGraph();

        Scanner scanner = new Scanner(System.in);
        int enemyBaseCamp = scanner.nextInt();

        List<List<Integer>> paths = BFS(1, enemyBaseCamp);

        String[] units = {"Cavalry", "Archer", "Infantry"};

        // Find the best path for each unit type
        for (String unit : units) {
            double shortestTravelTime = Double.MAX_VALUE;
            List<Integer> bestPath = null;

            for (List<Integer> path : paths) {
                double travelTime = graph.calculateTravelTime(path, unit);
                if (travelTime < shortestTravelTime) {
                    shortestTravelTime = travelTime;
                    bestPath = path;
                }
            }

            // Display the best path for the current unit type
            System.out.println("Best path for " + unit + ":");
            StringBuilder sb = new StringBuilder();
            sb.append("1");
            for (int i = 1; i < bestPath.size(); i++) {
                sb.append(" -> ").append(bestPath.get(i));
            }
            System.out.println(sb);
            System.out.println("Travel time: " + shortestTravelTime + " hours");
            System.out.println();
        }
    }

}

