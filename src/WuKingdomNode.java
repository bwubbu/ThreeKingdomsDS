import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
        General general = node.getGeneral();
        System.out.printf("\n" + indent + general.getName());

        for (WuKingdomNode child : node.getChildren()) {
            displayNode(child, indent + "- ");
        }
    }
}

class General {
    private String name;
    private String imageFileName;
    private String title;
    private String armyType;
    private int strength;
    private int leadership;
    private int intelligence;
    private int politic;
    private int hitPoint;
    private String abilityLevel;

    public General(String name, String imageFileName, String title, String armyType, int strength, int leadership, int intelligence,
                   int politic, int hitPoint) {
        this.name = name;
        this.imageFileName = imageFileName;
        this.title = title;
        this.armyType = armyType;
        this.strength = strength;
        this.leadership = leadership;
        this.intelligence = intelligence;
        this.politic = politic;
        this.hitPoint = hitPoint;
    }

    // Getters and setters

    public String getName() {
        return name;
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

    public int getTotalAbility() {
        return politic + leadership + strength + intelligence;
    }

    public String getAbilityLevel() {
        return abilityLevel;
    }

    public void setAbilityLevel(String abilityLevel) {
        this.abilityLevel = abilityLevel;
    }

    public String getDepartment() {
        if (getName().equals("Sun Quan")) {
            return "Emperor";
        }
        if (getName().equals("Zhou Yu")) {
            return "Chief of Military Department";
        }
        if (getName().equals("Zhang Zhao")) {
            return "Chief of Management Department";
        }

        if (getIntelligence() > getStrength()) {
            return "Management Department";
        } else {
            return "Military Department";
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
