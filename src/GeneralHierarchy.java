import java.util.*;

public class GeneralHierarchy {
    public void TeamInfo() {
        General sunWu = new General("Sun Quan", "sunWu.png", "Emperor", "Cavalry", 96, 98, 72, 77, 95);
        WuKingdomNode sunWuN = new WuKingdomNode(sunWu);

        General zhouYu = new General("Zhou Yu", "zhouYu.png", "Chief of Military", "Cavalry", 80, 86, 97, 80, 90);
        WuKingdomNode zhouYuN = new WuKingdomNode(zhouYu);

        General zhangZhao = new General("Zhang Zhao", "zhangZhao.png", "Chief of Management", "Archer", 22, 80, 89, 99, 60);
        WuKingdomNode zhangZhaoN = new WuKingdomNode(zhangZhao);

        sunWuN.addChild(zhouYuN);
        sunWuN.addChild(zhangZhaoN);

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

        for (General general : generals) {
            WuKingdomNode generalNode = new WuKingdomNode(general);
            int totalAbility = general.getTotalAbility();

            if (general.getIntelligence() > general.getStrength()) {
                zhangZhaoN.addChild(generalNode);
            } else {
                zhouYuN.addChild(generalNode);
            }
        }
        sunWuN.displayWuKingdom();
        System.out.println();

        Comparator<General> abilityComparator = Comparator.comparingInt(General::getTotalAbility);
        generals.sort(abilityComparator);

        // binary search starts from here
        int targetAbility = 294;
        int index = binarySearch(generals, targetAbility);
        if (index != -1) {
            General targetGeneral = generals.get(index);
            System.out.println("General found with ability " + targetAbility + ": " + targetGeneral.getName());
        } else {
            System.out.println("General not found with ability " + targetAbility);
        }

        Comparator<General> politicComparator = Comparator.comparingInt(General::getPolitic).reversed();
        Comparator<General> leadershipComparator = Comparator.comparingInt(General::getLeadership).reversed();
        Comparator<General> strengthComparator = Comparator.comparingInt(General::getStrength).reversed();
        Comparator<General> intelligenceComparator = Comparator.comparingInt(General::getIntelligence).reversed();

        generals.sort(politicComparator);
        List<General> politicTeam = formTeam(generals, "politic");
        Set<General> selectedGenerals = new HashSet<>(politicTeam);
        generals.sort(leadershipComparator);
        List<General> leadershipTeam = formTeam(generals, "leadership", selectedGenerals);
        selectedGenerals.addAll(leadershipTeam);
        generals.sort(strengthComparator);
        List<General> strengthTeam = formTeam(generals, "strength", selectedGenerals);
        selectedGenerals.addAll(strengthTeam);
        generals.sort(intelligenceComparator);
        List<General> intelligenceTeam = formTeam(generals, "intelligence", selectedGenerals);

        System.out.println("Politic Team:");
        displayTeam(politicTeam);

        System.out.println("\nLeadership Team:");
        displayTeam(leadershipTeam);

        System.out.println("\nStrength Team:");
        displayTeam(strengthTeam);

        System.out.println("\nIntelligence Team:");
        displayTeam(intelligenceTeam);
    }

    //so that there won't be a 4th parameter
    private static List<General> formTeam(List<General> generals, String field) {
        List<General> team = new ArrayList<>();
        int remainingCount = 3;

        for (General general : generals) {
            if (remainingCount == 0) {
                break;
            }

            if (isPositiveStat(general, field)) {
                team.add(general);
                remainingCount--;
            }
        }

        return team;
    }

    // for excluding the same repeating generals
    private static List<General> formTeam(List<General> generals, String field, Set<General> excludedGenerals) {
        List<General> team = new ArrayList<>();
        int remainingCount = 3;

        for (General general : generals) {
            if (remainingCount == 0) {
                break;
            }

            if (!excludedGenerals.contains(general) && isPositiveStat(general, field)) {
                boolean canAddToTeam = true;

                // Check if the general's ability sum is higher than other stats
                for (General selectedGeneral : team) {
                    if (getTotalAbility(general) <= getTotalAbility(selectedGeneral)) {
                        canAddToTeam = false;
                        break;
                    }
                }

                if (canAddToTeam) {
                    team.add(general);
                    excludedGenerals.add(general);
                    remainingCount--;
                }
            }
        }

        return team;
    }

    private static int getTotalAbility(General general) {
        return general.getLeadership() + general.getStrength() + general.getIntelligence() + general.getPolitic();
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

    public static void displayTeam(List<General> team) {
        for (General general : team) {
            System.out.println(general.getName());
        }
    }

    private static int binarySearch(List<General> generals, int targetAbility) {
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