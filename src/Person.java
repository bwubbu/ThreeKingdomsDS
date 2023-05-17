import java.util.ArrayList;
import java.util.List;

public class Person {
    String name;
    String title;
    String armyType;
    int strength;
    int leadership;
    int intelligence;
    int politic;
    int hitPoint;

    public Person(String name, String title, String armyType, int strength, int leadership, int intelligence,
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
class General extends Person {
    public General(String name, String title, String armyType, int strength, int leadership, int intelligence,
                   int politic, int hitPoint) {
        super(name, title, armyType, strength, leadership, intelligence, politic, hitPoint);
    }
}
class ChiefOfMilitary extends Person {
    List<General> generals;

    public ChiefOfMilitary(String name, String title, String armyType, int strength, int leadership,
                           int intelligence, int politic, int hitPoint) {
        super(name, title, armyType, strength, leadership, intelligence, politic, hitPoint);
        this.generals = new ArrayList<>();
    }

    public void addGeneral(General general) {
        generals.add(general);
    }
}
class ChiefOfManagement extends Person {
    List<General> generals;

    public ChiefOfManagement(String name, String title, String armyType, int strength, int leadership,
                             int intelligence, int politic, int hitPoint) {
        super(name, title, armyType, strength, leadership, intelligence, politic, hitPoint);
        this.generals = new ArrayList<>();
    }
    public void addGeneral(General general) {
        generals.add(general);
    }
}
class Emperor extends Person {
    ChiefOfMilitary chiefOfMilitary;
    ChiefOfManagement chiefOfManagement;
    public Emperor(String name, String title, String armyType, int strength, int leadership, int intelligence,
                   int politic, int hitPoint, ChiefOfMilitary chiefOfMilitary, ChiefOfManagement chiefOfManagement) {
        super(name, title, armyType, strength, leadership, intelligence, politic, hitPoint);
        this.chiefOfMilitary = chiefOfMilitary;
        this.chiefOfManagement = chiefOfManagement;
    }
    public void assignGeneralsToDepartments(General[] generals) {
        Characters characters = new Characters();
        for (General general : generals) {
            if (general.intelligence > general.strength) {
                chiefOfManagement.addGeneral(general);
            } else {
                chiefOfMilitary.addGeneral(general);
            }
        }
    }
}