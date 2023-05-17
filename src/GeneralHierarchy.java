import java.util.Arrays;
import java.util.Comparator;

public class GeneralHierarchy {

        private final WuKingdomNode root;

        public GeneralHierarchy(WuKingdomNode root) {
            this.root = root;
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
            int cmp = comparator.compare(generals[mid], new WuKingdomNode(null, 0, key, 0, 0, 0));

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

        private WuKingdomNode[] getGeneralsArray() {
            WuKingdomNode[] generals = new WuKingdomNode[2];
            generals[0] = root.getChildren()[0];
            generals[1] = root.getChildren()[1];
            return generals;
        }
}