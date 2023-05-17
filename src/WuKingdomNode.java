public class WuKingdomNode {
    private final String name;
    private final int strength;
    private final int intelligence;
    private final int leadership;
    private final int politic;
    private final int hitPoint;
    private final WuKingdomNode[] children;

    public WuKingdomNode(String name, int strength, int intelligence, int leadership, int politic, int hitPoint) {
        this.name = name;
        this.strength = strength;
        this.intelligence = intelligence;
        this.leadership = leadership;
        this.politic = politic;
        this.hitPoint = hitPoint;
        this.children = new WuKingdomNode[2]; // Two children: one for military department, one for management department
    }
    public String getName() {
        return name;
    }
    public int getStrength() {
        return strength;
    }
    public int getIntelligence() {
        return intelligence;
    }
    public int getLeadership() {
        return leadership;
    }
    public int getPolitic() {
        return politic;
    }
    public int getHitPoint() {
        return hitPoint;
    }
    public int getTotalAbility() {
        return strength + intelligence + leadership + politic + hitPoint;
    }
    public WuKingdomNode[] getChildren() {
        return children;
    }
}