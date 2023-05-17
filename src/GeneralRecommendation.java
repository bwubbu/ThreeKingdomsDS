import java.util.*;

public class GeneralRecommendation {
    private final WuKingdomNode[] generals;

    public GeneralRecommendation(WuKingdomNode[] generals) {
        this.generals = generals;
    }

    public void sortGeneralsByLeadership() {
        Arrays.sort(generals, (a, b) -> b.getLeadership() - a.getLeadership());
    }

    public void sortGeneralsByStrength() {
        Arrays.sort(generals, (a, b) -> b.getStrength() - a.getStrength());
    }

    public void sortGeneralsByIntelligence() {
        Arrays.sort(generals, (a, b) -> b.getIntelligence() - a.getIntelligence());
    }

    public void sortGeneralsByPolitic() {
        Arrays.sort(generals, (a, b) -> b.getPolitic() - a.getPolitic());
    }

    public void sortGeneralsByHitPoint() {
        Arrays.sort(generals, (a, b) -> b.getHitPoint() - a.getHitPoint());
    }

    public int binarySearchGeneralsByLeadership(int key) {
        return Arrays.binarySearch(generals, new WuKingdomNode("", key, 0, 0, 0, 0), (a, b) -> b.getLeadership() - a.getLeadership());
    }

    public int binarySearchGeneralsByStrength(int key) {
        return Arrays.binarySearch(generals, new WuKingdomNode("", 0, key, 0, 0, 0), (a, b) -> b.getStrength() - a.getStrength());
    }

    public int binarySearchGeneralsByIntelligence(int key) {
        return Arrays.binarySearch(generals, new WuKingdomNode("", 0, 0, key, 0, 0), (a, b) -> b.getIntelligence() - a.getIntelligence());
    }

    public int binarySearchGeneralsByPolitic(int key) {
        return Arrays.binarySearch(generals, new WuKingdomNode("", 0, 0, 0, key, 0), (a, b) -> b.getPolitic() - a.getPolitic());
    }

    public int binarySearchGeneralsByHitPoint(int key) {
        return Arrays.binarySearch(generals, new WuKingdomNode("", 0, 0, 0, 0, key), (a, b) -> b.getHitPoint() - a.getHitPoint());
    }
    private class GeneralComparator implements Comparator<WuKingdomNode> {
        private final String attribute;

        public GeneralComparator(String attribute) {
            this.attribute = attribute;
        }

        public int compare(WuKingdomNode g1, WuKingdomNode g2) {
            switch (attribute) {
                case "leadership":
                    return g2.getLeadership() - g1.getLeadership();
                case "strength":
                    return g2.getStrength() - g1.getStrength();
                case "intelligence":
                    return g2.getIntelligence() - g1.getIntelligence();
                case "politic":
                    return g2.getPolitic() - g1.getPolitic();
                case "hitPoint":
                    return g2.getHitPoint() - g1.getHitPoint();
                default:
                    throw new IllegalArgumentException("Invalid attribute");
            }
        }
    }
    public List<WuKingdomNode> findGeneralsByLevel(String attribute, String level, int minAbility) {
        GeneralComparator comparator = new GeneralComparator(attribute);
        List<WuKingdomNode> qualifiedGenerals = new ArrayList<>();
        int sum = 0;

        for (WuKingdomNode general : generals) {
            if (comparator.compare(general, new WuKingdomNode("", 0, 0, 0, 0, minAbility)) >= 0) {
                qualifiedGenerals.add(general);
                sum += general.getTotalAbility();

                if (level.equals("S") && sum >= 250) {
                    break;
                } else if (level.equals("A") && sum >= 220) {
                    break;
                } else if (level.equals("B") && sum >= 190) {
                    break;
                } else if (level.equals("C") && sum >= 150) {
                    break;
                }
            }
        }
        return qualifiedGenerals;
    }
}