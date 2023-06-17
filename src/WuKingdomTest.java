import javax.swing.tree.DefaultMutableTreeNode;
import java.util.*;

public class WuKingdomTest {
    public static void main(String[] args) {
        General sunQuan = new General("Sun Quan", "sunWu.png", "Emperor", "Cavalry", 96, 98, 72, 77, 95);
        WuKingdomNode sunQuanNode = new WuKingdomNode(sunQuan);

        General zhouYu = new General("Zhou Yu", "zhouYu.png", "Chief of Military", "Cavalry", 80, 86, 97, 80, 90);
        WuKingdomNode zhouYuNode = new WuKingdomNode(zhouYu);

        General zhangZhao = new General("Zhang Zhao", "zhangZhao.png", "Chief of Management", "Archer", 22, 80, 89, 99, 60);
        WuKingdomNode zhangZhaoNode = new WuKingdomNode(zhangZhao);

        sunQuanNode.addChild(zhouYuNode);
        sunQuanNode.addChild(zhangZhaoNode);

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

        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Sun Quan");

        DefaultMutableTreeNode zhouYuNode1 = new DefaultMutableTreeNode("Zhou Yu");
        rootNode.add(zhouYuNode1);

        DefaultMutableTreeNode zhangZhaoNode1 = new DefaultMutableTreeNode("Zhang Zhao");
        rootNode.add(zhangZhaoNode1);

        for (General general : generals) {
            DefaultMutableTreeNode generalNode1 = new DefaultMutableTreeNode(general.getName() );
            WuKingdomNode generalNode = new WuKingdomNode(general);
//            System.out.println(general.getAbilitySum() + " " + general.getName());
            if (general.getIntelligence() > general.getStrength()) {
                zhangZhaoNode.addChild(generalNode);
                zhangZhaoNode1.add(generalNode1);
            } else {
                zhouYuNode.addChild(generalNode);
                zhouYuNode1.add(generalNode1);

            }
        }
        sunQuanNode.displayWuKingdom();
        System.out.println();

        Comparator<General> abilityComparator = Comparator.comparingInt(General::getTotalAbility);
        Collections.sort(generals, abilityComparator);

        int targetAbility = 294;
        int index = binarySearch(generals, targetAbility);
        if (index != -1) {
            General targetGeneral = generals.get(index);
            System.out.println("General found with ability " + targetAbility + ": " + targetGeneral.getName());
        } else {
            System.out.println("General not found with ability " + targetAbility);
        }

        // Sort the generals by each field
        Comparator<General> politicComparator = Comparator.comparingInt(General::getPolitic).reversed();
        Comparator<General> leadershipComparator = Comparator.comparingInt(General::getLeadership).reversed();
        Comparator<General> strengthComparator = Comparator.comparingInt(General::getStrength).reversed();
        Comparator<General> intelligenceComparator = Comparator.comparingInt(General::getIntelligence).reversed();

        Collections.sort(generals, politicComparator);
        List<General> politicTeam = formTeam(generals, "politic", 3);

        Set<General> selectedGenerals = new HashSet<>(politicTeam); // Track selected generals

        Collections.sort(generals, leadershipComparator);
        List<General> leadershipTeam = formTeam(generals, "leadership", 3, selectedGenerals);

        selectedGenerals.addAll(leadershipTeam);

        Collections.sort(generals, strengthComparator);
        List<General> strengthTeam = formTeam(generals, "strength", 3, selectedGenerals);

        selectedGenerals.addAll(strengthTeam);

        Collections.sort(generals, intelligenceComparator);
        List<General> intelligenceTeam = formTeam(generals, "intelligence", 3, selectedGenerals);

        // Display the teams
        System.out.println("Politic Team:");
        displayTeam(politicTeam);

        System.out.println("\nLeadership Team:");
        displayTeam(leadershipTeam);

        System.out.println("\nStrength Team:");
        displayTeam(strengthTeam);

        System.out.println("\nIntelligence Team:");
        displayTeam(intelligenceTeam);

    }

    private static List<General> formTeam(List<General> generals, String field, int count) {
        List<General> team = new ArrayList<>();
        int remainingCount = count;

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

    private static List<General> formTeam(List<General> generals, String field, int count, Set<General> excludedGenerals) {
        List<General> team = new ArrayList<>();
        int remainingCount = count;

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
            case "politic":
                stat = general.getPolitic();
                break;
            case "leadership":
                stat = general.getLeadership();
                break;
            case "strength":
                stat = general.getStrength();
                break;
            case "intelligence":
                stat = general.getIntelligence();
                break;
            default:
                return false;
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
                    ", Leadership: " + general.getLeadership() +
                    ", Strength: " + general.getStrength() +
                    ", Intelligence: " + general.getIntelligence() +
                    ", Ability Sum: " + general.getTotalAbility());
        }
    }

    private static int binarySearch(ArrayList<General> generals, int targetAbility) {
        int left = 0;
        int right = generals.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int ability = generals.get(mid).getTotalAbility();

            if (ability == targetAbility) {
                return mid;
            } else if (ability < targetAbility) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -(left + 1);
    }

}