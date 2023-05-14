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

    public void sortGeneralByStrength() {
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
}