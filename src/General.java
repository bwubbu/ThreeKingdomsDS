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
    public String getTitle() {
        if (title.equals("General")) {
            if (strength > intelligence) {
                return "Military General";
            } else {
                return "Management General";
            }
        } else {
            return title;
        }
    }
    public String getImageFileName() {
        return imageFileName;
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