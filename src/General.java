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

