import java.util.*;
import java.lang.*;
class General implements Comparable<General> {
    private String name;
    private String title;
    private String armyType;
    private int strength;
    private int leadership;
    private int intelligence;
    private int politic;
    private int hitPoint;
    private General right;
    private General left;

    public General(String name, String title, String armyType, int strength, int leadership, int intelligence,
                   int politic, int hitPoint) {
        this.name = name;
        this.title = title;
        this.armyType = armyType;
        this.strength = strength;
        this.leadership = leadership;
        this.intelligence = intelligence;
        this.politic = politic;
        this.hitPoint = hitPoint;
        this.right = null;
        this.left = null;
    }
    // putting getters here
    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getArmyType() {
        return armyType;
    }

    public int getStrength() {
        return strength;
    }

    public int getLeadership() {
        return leadership;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getPolitic() {
        return politic;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public General getRight() {
        return right;
    }
    public General getLeft() {
        return left;
    }
    // putting setters here

    public void setRight(General right) {
        this.right = right;
    }

    public void setLeft(General right) {
        this.left = left;
    }

    public int getTotalAbility() {
        return politic + leadership + strength + intelligence;
    }

    @Override
    public int compareTo(General other) {
        return Integer.compare(this.leadership, other.leadership);
    }
}
class WuKingdomNode {
    public General general;
    public WuKingdomNode parent;
    public List<WuKingdomNode> children;

    public WuKingdomNode(General general) {
        this.general = general;
        this.parent = null;
        this.children = new ArrayList<>();
    }

    public General getGeneral() {
        return general;
    }

    public WuKingdomNode getParent() {
        return parent;
    }

    public void setParent(WuKingdomNode parent) {
        this.parent = parent;
    }

    public List<WuKingdomNode> getChildren() {
        return children;
    }

    public void addChild(WuKingdomNode child) {
        child.setParent(this);
        this.children.add(child);
    }

    public void displayWuKingdom() {
        displayNode(this, "");
    }

    private void displayNode(WuKingdomNode node, String indent) {
        General generic = node.getGeneral();
        System.out.printf("\n" + indent + generic.getName() + "\t |" + generic.getArmyType() + "|");

        for (WuKingdomNode child : node.getChildren()) {
            displayNode(child, indent + "  ");
        }
    }
}
