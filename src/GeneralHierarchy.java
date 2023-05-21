import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class GeneralHierarchy {
    private final WuKingdomNode root;
    private final Characters characters;

    public GeneralHierarchy(WuKingdomNode root, Characters characters) {
        this.root = root;
        this.characters = characters;
        }

        public void sortGeneralsByLeadership() {
            sortGeneralsByAttribute(Comparator.comparingInt(WuKingdomNode::getLeadership).reversed());
        }

        public void sortGeneralsByStrength() {
            sortGeneralsByAttribute(Comparator.comparingInt(WuKingdomNode::getStrength).reversed());
        }

        public void sortGeneralsByIntelligence() {
            sortGeneralsByAttribute(Comparator.comparingInt(WuKingdomNode::getIntelligence).reversed());
        }

        public void sortGeneralsByPolitic() {
            sortGeneralsByAttribute(Comparator.comparingInt(WuKingdomNode::getPolitic).reversed());
        }

        public void sortGeneralsByHitPoint() {
            sortGeneralsByAttribute(Comparator.comparingInt(WuKingdomNode::getHitPoint).reversed());
        }

        public int binarySearchGeneralsByLeadership(int key) {
            return binarySearchGeneralsByAttribute(key, Comparator.comparingInt(WuKingdomNode::getLeadership).reversed());
        }

        public int binarySearchGeneralsByStrength(int key) {
            return binarySearchGeneralsByAttribute(key, Comparator.comparingInt(WuKingdomNode::getStrength).reversed());
        }

        public int binarySearchGeneralsByIntelligence(int key) {
            return binarySearchGeneralsByAttribute(key, Comparator.comparingInt(WuKingdomNode::getIntelligence).reversed());
        }

        public int binarySearchGeneralsByPolitic(int key) {
            return binarySearchGeneralsByAttribute(key, Comparator.comparingInt(WuKingdomNode::getPolitic).reversed());
        }

        public int binarySearchGeneralsByHitPoint(int key) {
            return binarySearchGeneralsByAttribute(key, Comparator.comparingInt(WuKingdomNode::getHitPoint).reversed());
        }

        private void sortGeneralsByAttribute(Comparator<WuKingdomNode> comparator) {
            WuKingdomNode[] generals = getGeneralsArray();
            Arrays.sort(generals, comparator);
        }

        private int binarySearchGeneralsByAttribute(int key, Comparator<WuKingdomNode> comparator) {
            WuKingdomNode[] generals = getGeneralsArray();
            int low = 0;
            int high = generals.length - 1;

            while (low <= high) {
                int mid = (low + high) / 2;
                int cmp = comparator.compare(generals[mid], new WuKingdomNode(null, null, null, key, 0, 0, 0 ,0));

                if (cmp == 0) {
                    return mid; // Found the key at index mid
                } else if (cmp < 0) {
                    high = mid - 1; // Key is in the lower half
                } else {
                    low = mid + 1; // Key is in the upper half
                }
            }

            return -1; // Key not found
        }

    WuKingdomNode[] getGeneralsArray() {
        General[] generals = characters.getAllGenerals();
        WuKingdomNode[] nodes = new WuKingdomNode[generals.length];

        for (int i = 0; i < generals.length; i++) {
            General general = generals[i];
            WuKingdomNode node = new WuKingdomNode(general.getName(), general.getTitle(), general.getArmyType(), general.getStrength(),
                    general.getIntelligence(), general.getLeadership(), general.getPolitic(), general.getHitPoint());
            nodes[i] = node;
        }

        return nodes;
    }
        public List<WuKingdomNode> suggestGeneralsByAttributeLevel(String attribute, int minimumAbility) {
            WuKingdomNode[] generals = getGeneralsArray();
            List<WuKingdomNode> suggestedGenerals = new ArrayList<>();

            // Sort generals by the specified attribute in descending order
            switch (attribute) {
                case "politic":
                    sortGeneralsByPolitic();
                    break;
                case "leadership":
                    sortGeneralsByLeadership();
                    break;
                case "strength":
                    sortGeneralsByStrength();
                    break;
                case "intelligence":
                    sortGeneralsByIntelligence();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid attribute: " + attribute);
            }

            // Find generals that meet the minimum ability requirements
            int sum = 0;
            for (WuKingdomNode general : generals) {
                sum += general.getTotalAbility();
                if (sum >= minimumAbility) {
                    suggestedGenerals.add(general);
                    sum = 0;
                }
            }

            return suggestedGenerals;
        }
    public Characters getCharacters() {
        return characters;
    }
}