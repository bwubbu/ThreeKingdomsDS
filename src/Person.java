class Person {
    String name;
    String title;
    String armyType;
    int strength;
    int leadership;
    int intelligence;
    int politic;
    int hitPoint;

    public Person(String name, String title, String armyType, int strength, int leadership, int intelligence, int politic, int hitPoint) {
        this.name = name;
        this.title = title;
        this.armyType = armyType;
        this.strength = strength;
        this.leadership = leadership;
        this.intelligence = intelligence;
        this.politic = politic;
        this.hitPoint = hitPoint;
    }
}

class General extends Person {
    String department;

    public General(String name, String title, String armyType, int strength, int leadership, int intelligence, int politic, int hitPoint) {
        super(name, title, armyType, strength, leadership, intelligence, politic, hitPoint);
        this.department = assignDepartment();
    }

    private String assignDepartment() {
        if (intelligence > strength) {
            return "Management";
        } else {
            return "Military";
        }
    }
}

class ChiefOfMilitary extends Person {
    public ChiefOfMilitary(String name, String title, String armyType, int strength, int leadership, int intelligence, int politic, int hitPoint) {
        super(name, title, armyType, strength, leadership, intelligence, politic, hitPoint);
    }
}

class ChiefOfManagement extends Person {
    public ChiefOfManagement(String name, String title, String armyType, int strength, int leadership, int intelligence, int politic, int hitPoint) {
        super(name, title, armyType, strength, leadership, intelligence, politic, hitPoint);
    }
}

class Emperor extends Person {
    ChiefOfMilitary chiefOfMilitary;
    ChiefOfManagement chiefOfManagement;

    public Emperor(String name, String title, String armyType, int strength, int leadership, int intelligence, int politic, int hitPoint,
                   ChiefOfMilitary chiefOfMilitary, ChiefOfManagement chiefOfManagement) {
        super(name, title, armyType, strength, leadership, intelligence, politic, hitPoint);
        this.chiefOfMilitary = chiefOfMilitary;
        this.chiefOfManagement = chiefOfManagement;
    }
    public ChiefOfMilitary getChiefOfMilitary() {
        return chiefOfMilitary;
    }

    public ChiefOfManagement getChiefOfManagement() {
        return chiefOfManagement;
    }
}
