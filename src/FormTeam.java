import java.util.*;

public class FormTeam {
    private static List<General> formTeam(List<General> generals, String field) {
        List<General> team = new ArrayList<>();
        int remainingCount = 3;

        for (General general : generals) {
            if (remainingCount == 0) {
                break;
            }

            // Exclude generals with negative stats
            if (isPositiveStat(general, field)) {
                team.add(general);
                remainingCount--;
            }
        }

        return team;
    }

    private static List<General> formTeam(List<General> generals, String field, Set<General> excludedGenerals) {
        List<General> team = new ArrayList<>();
        int remainingCount = 3;

        for (General general : generals) {
            if (remainingCount == 0) {
                break;
            }

            // Exclude already selected generals and those with negative stats
            if (!excludedGenerals.contains(general) && isPositiveStat(general, field)) {
                team.add(general);
                excludedGenerals.add(general);
                remainingCount--;
            }
        }

        return team;
    }

    private static boolean isPositiveStat(General general, String field) {
        int stat;
        switch (field) {
            case "politic" -> stat = general.getPolitic();
            case "leadership" -> stat = general.getLeadership();
            case "strength" -> stat = general.getStrength();
            case "intelligence" -> stat = general.getIntelligence();
            default -> {
                return false;
            }
        }
        return stat >= 0;
    }

    private static void displayTeam(List<General> team) {
        int sum = team.stream().mapToInt(General::getTotalAbility).sum();
        String abilityLevel;
        if (sum >= 250) {
            abilityLevel = "S";
        } else if (sum >= 220) {
            abilityLevel = "A";
        } else if (sum >= 190) {
            abilityLevel = "B";
        } else {
            abilityLevel = "C";
        }

        System.out.println("Team Ability Level: " + abilityLevel);
        for (General general : team) {
            System.out.println("Name: " + general.getName() +
                    ", Politic: " + general.getPolitic() +
                    "\tLeadership: " + general.getLeadership() +
                    "\tStrength: " + general.getStrength() +
                    "\tIntelligence: " + general.getIntelligence() +
                    "\tTotal ability: " + general.getTotalAbility());
        }
    }

    private static int binarySearch(List<General> generals, String attribute, int targetValue) {
        int left = 0;
        int right = generals.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            General general = generals.get(mid);
            int attributeValue = getStatByName(general, attribute);

            if (attributeValue == targetValue) {
                return mid;
            } else if (attributeValue < targetValue) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }

    private static int getStatByName(General general, String attribute) {
        switch (attribute) {
            case "politic":
                return general.getPolitic();
            case "leadership":
                return general.getLeadership();
            case "strength":
                return general.getStrength();
            case "intelligence":
                return general.getIntelligence();
            default:
                throw new IllegalArgumentException("Invalid attribute name: " + attribute);
        }
    }

    public static void main(String[] args) {
        General zhouYu = new General("Zhou Yu", "zhouYu.png", "Chief of Military", "Cavalry", 80, 86, 97, 80, 90);
        General zhangZhao = new General("Zhang Zhao", "zhangZhao.png", "Chief of Management", "Archer", 22, 80, 89, 99, 60);
        General xuSheng = new General("Xu Sheng", "xuSheng.png", "General", "Archer", 90, 78, 72, 40, 94);
        General zhuGeJin = new General("Zu Ge Jin", "zhuGeJin.png", "General", "Archer", 63, 61, 88, 82, 71);
        General luSu = new General("Lu Su", "luSu.png", "General", "Infantry", 43, 87, 84, 88, 53);
        General taiShiCi = new General("Tai Shi Ci", "taiShiCi.png", "General", "Cavalry", 96, 81, 43, 33, 97);
        General xiaoQiao = new General("Xiao Qiao", "xiaoQiao.png", "General", "Infantry", 42, 52, 89, 77, 34);
        General daQiao = new General("Da Qiao", "daQiao.png", "General", "Cavalry", 39, 62, 90, 62, 41);
        General zhouTai = new General("Zhou Tai", "zhouTai.png", "General", "Infantry", 92, 89, 72, 43, 99);
        General ganNing = new General("Gan Ning", "ganNing.png", "General", "Archer", 98, 92, 45, 23, 97);
        General luMeng = new General("Lu Meng", "luMeng.png", "General", "Cavalry", 70, 77, 93, 83, 88);
        General huangGai = new General("Huang Gai", "huangGai.png", "General", "Infantry", 83, 98, 72, 42, 89);

        ArrayList<General> generals = new ArrayList<>();
        generals.add(zhouYu);
        generals.add(zhangZhao);
        generals.add(xuSheng);
        generals.add(zhuGeJin);
        generals.add(luSu);
        generals.add(taiShiCi);
        generals.add(xiaoQiao);
        generals.add(daQiao);
        generals.add(zhouTai);
        generals.add(ganNing);
        generals.add(luMeng);
        generals.add(huangGai);

        Comparator<General> abilityComparator = Comparator.comparingInt(General::getTotalAbility);
        generals.sort(abilityComparator);

        // Sort the generals by each field
        Comparator<General> politicComparator = Comparator.comparingInt(General::getPolitic).reversed();
        Comparator<General> leadershipComparator = Comparator.comparingInt(General::getLeadership).reversed();
        Comparator<General> strengthComparator = Comparator.comparingInt(General::getStrength).reversed();
        Comparator<General> intelligenceComparator = Comparator.comparingInt(General::getIntelligence).reversed();

        generals.sort(politicComparator);
        List<General> politicTeam = formTeam(generals, "politic");

        Set<General> selectedGenerals = new HashSet<>(politicTeam); // Track selected generals

        generals.sort(leadershipComparator);
        List<General> leadershipTeam = formTeam(generals, "leadership", selectedGenerals);

        selectedGenerals.addAll(leadershipTeam);

        generals.sort(strengthComparator);
        List<General> strengthTeam = formTeam(generals, "strength", selectedGenerals);

        selectedGenerals.addAll(strengthTeam);

        generals.sort(intelligenceComparator);
        List<General> intelligenceTeam = formTeam(generals, "intelligence", selectedGenerals);

        // Display the teams
        System.out.println("Politic Team:");
        displayTeam(politicTeam);

        System.out.println("\nLeadership Team:");
        displayTeam(leadershipTeam);

        System.out.println("\nStrength Team:");
        displayTeam(strengthTeam);

        System.out.println("\nIntelligence Team:");
        displayTeam(intelligenceTeam);

        Scanner scanner = new Scanner(System.in);

// Prompt user for attribute name and value
        System.out.print("Enter the attribute name: ");
        String attribute = scanner.nextLine();

        System.out.print("Enter the attribute value: ");
        int value = scanner.nextInt();

// Perform binary search for generals with the specified attribute and value
        int index = binarySearch(generals, attribute, value);
        if (index != -1) {
            List<General> matchingGenerals = new ArrayList<>();

            for (int i = index; i < generals.size(); i++) {
                General general = generals.get(i);
                if (!isPositiveStat(general, attribute) || getStatByName(general, attribute) < value) {
                    break;
                }
                if (getStatByName(general, attribute) == value) {
                    matchingGenerals.add(general);
                }
            }
            if (!matchingGenerals.isEmpty()) {
                System.out.println("Generals found with attribute " + attribute + " and value " + value + ":");
                for (General general : matchingGenerals) {
                    System.out.println("Name: " + general.getName() +
                            ", Politic: " + general.getPolitic() +
                            "\tLeadership: " + general.getLeadership() +
                            "\tStrength: " + general.getStrength() +
                            "\tIntelligence: " + general.getIntelligence() +
                            "\tTotal ability: " + general.getTotalAbility());
                }
            }
            else {
                System.out.println("No generals found with attribute " + attribute + " and value " + value);
            }
        }
    }
}