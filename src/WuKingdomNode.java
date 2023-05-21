import java.util.ArrayList;
import java.util.List;

public class WuKingdomNode {
    public final String name;

    public final String title;
    public final String armyType;
    public final int strength;
    public final int intelligence;
    public final int leadership;
    public final int politic;
    public final int hitPoint;
    public final List<WuKingdomNode> children;

    public WuKingdomNode(String name, String title, String armyType, int strength, int intelligence, int leadership, int politic, int hitPoint) {
        this.name = name;
        this.title = title;
        this.armyType = armyType;
        this.strength = strength;
        this.intelligence = intelligence;
        this.leadership = leadership;
        this.politic = politic;
        this.hitPoint = hitPoint;
        this.children = new ArrayList<>();
    }

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

    public List<WuKingdomNode> getChildren() {
        return children;
    }

    public void addChild(WuKingdomNode child) {
        children.add(child);
    }

    public void assignGeneralsToDepartments(General[] generals) {
        for (General general : generals) {
            if (general.getIntelligence() > general.getStrength()) {
                WuKingdomNode managementDepartment = new WuKingdomNode("Management Department", general.getTitle(), general.getArmyType(),
                        general.getStrength(), general.getIntelligence(), 0, 0, 0);
                this.addChild(managementDepartment);
            } else {
                WuKingdomNode militaryDepartment = new WuKingdomNode("Military Department", general.getTitle(), general.getArmyType(),
                        general.getStrength(), general.getIntelligence(), 0, 0, 0);
                this.addChild(militaryDepartment);
            }
        }
    }
}
