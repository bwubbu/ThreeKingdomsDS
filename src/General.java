import java.lang.*;
class General implements Comparable<General> {
    private final String name;
    private final String imageFileName;
    private final String title;
    private final String armyType;
    private final int strength;
    private final int leadership;
    private final int intelligence;
    private final int politic;
    private final int hitPoint;
    private String abilityLevel;
    public General(String name,String imageFileName, String title, String armyType, int strength, int leadership, int intelligence,
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

    public String getImageFileName() {
        return imageFileName;
    }
    public int getPolitic() {
        return politic;
    }

    public int getHitPoint() {
        return hitPoint;
    }
    public String getAbilityLevel() {
        return abilityLevel;
    }
    public int getTotalAbility() {
        return politic + leadership + strength + intelligence;
    }

    public void setAbilityLevel(String abilityLevel) {
        this.abilityLevel = abilityLevel;
    }

    public String getDepartment(){
        if (getName().equals("Sun Quan")) {
            return "Emperor";
        }
        if (getName().equals("Zhou Yu")) {
            return "Chief of Military Department";
        }
        if (getName().equals("Zhang Zhao"))
            return "Chief of Management Department";

        if (getIntelligence() > getStrength()){
            return "Management Department";
        }
        else {
            return "Military Department";
        }
    }
    @Override
    public int compareTo(General other) {
        return Integer.compare(this.leadership, other.leadership);
    }
}