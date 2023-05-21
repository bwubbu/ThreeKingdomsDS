import java.util.ArrayList;
import java.util.List;

public class General {
    private final String name;
    private final String title;
    private final String armyType;
    private final int strength;
    private final int leadership;
    private final int intelligence;
    private final int politic;
    private final int hitPoint;

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
}

class Emperor extends WuKingdomNode {
    public Emperor(String name, String title, String armyType, int strength, int intelligence, int leadership, int politic, int hitPoint) {
        super(name, title, armyType, strength, intelligence, leadership, politic, hitPoint);
    }
}

class ChiefOfMilitary extends WuKingdomNode {
    public ChiefOfMilitary(String name, String title, String armyType, int strength, int intelligence, int leadership, int politic, int hitPoint) {
        super(name, title, armyType, strength, intelligence, leadership, politic, hitPoint);
    }
}

class ChiefOfManagement extends WuKingdomNode {
    public ChiefOfManagement(String name, String title, String armyType, int strength, int intelligence, int leadership, int politic, int hitPoint) {
        super(name, title, armyType, strength, intelligence, leadership, politic, hitPoint);
    }
}

