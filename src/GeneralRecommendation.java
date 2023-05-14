import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

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
        return Arrays.binarySearch(generals, new WuKingdomNode("", 0, 0, 0, 0, 0) {
            @Override
            public int getLeadership() {
                return key;
            }
        }, (a, b) -> b.getLeadership() - a.getLeadership());
    }

    public int binarySearchGeneralsByStrength(int key) {
        return Arrays.binarySearch(generals, new WuKingdomNode("", key, 0, 0, 0, 0), (a, b) -> b.getStrength() - a.getStrength());
    }

    public int binarySearchGeneralsByIntelligence(int key) {
        return Arrays.binarySearch(generals, new WuKingdomNode("", 0, key, 0, 0, 0), (a, b) -> b.getIntelligence() - a.getIntelligence());
    }

    public int binarySearchGeneralsByPolitic(int key) {
        return Arrays.binarySearch(generals, new WuKingdomNode("", 0, 0, 0, 0, 0) {
            @Override
            public int getPolitic() {
                return key;
            }
        }, (a, b) -> b.getPolitic() - a.getPolitic());
    }

    public int binarySearchGeneralsByHitPoint(int key) {
        return Arrays.binarySearch(generals, new WuKingdomNode("", 0, 0, 0, 0, 0) {
            @Override
            public int getHitPoint() {
                return key;
            }
        }, (a, b) -> b.getHitPoint() - a.getHitPoint());
    }

    public WuKingdomNode[] getRecommendedGeneralsByPolitic(char level) {
        int sum = level == 'S' ? 250 : level == 'A' ? 220 : level == 'B' ? 190 : 0;
        return getRecommendedGenerals(sum, node -> node.getPolitic() > 0);
    }

    public WuKingdomNode[] getRecommendedGeneralsByLeadership(char level) {
        int sum = level == 'S' ? 250 : level == 'A' ? 220 : level == 'B' ? 190 : 0;
        return getRecommendedGenerals(sum, node -> node.getLeadership() > 0);
    }

    public WuKingdomNode[] getRecommendedGeneralsByStrength(char level) {
        int sum = level == 'S' ? 250 : level == 'A' ? 220 : level == 'B' ? 190 : 0;
        return getRecommendedGenerals(sum, node -> node.getStrength() > 0);
    }

    public WuKingdomNode[] getRecommendedGeneralsByIntelligence(char level) {
        int sum = level == 'S' ? 250 : level == 'A' ? 220 : level == 'B' ? 190 : 0;
        return getRecommendedGenerals(sum, node -> node.getIntelligence() > 0);
    }

    public WuKingdomNode[] getRecommendedGenerals(int sum, Predicate<WuKingdomNode> predicate) {
        List<WuKingdomNode> candidates = new ArrayList<>();
        Consumer<WuKingdomNode> action = (node) -> {
            if (predicate.test(node) && node.getAbilitySum() >= sum) {
                candidates.add(node);
            }
        };
        traverseInOrder(action);
        Collections.sort(candidates, Comparator.comparingInt(WuKingdomNode::getAbilitySum).reversed());
        if (candidates.size() >= 3) {
            return new WuKingdomNode[]{candidates.get(0), candidates.get(1), candidates.get(2)};
        } else {
            return candidates.toArray(new WuKingdomNode[0]);
        }
    }
}
