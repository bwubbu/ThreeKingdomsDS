/*import java.util.List;

public class GeneralSuggestion {
    public static void main(String[] args) {
        GeneralHierarchy hierarchy = createGeneralHierarchy();
        suggestGeneralsForLevel(hierarchy, "politic", "S", 250);
        suggestGeneralsForLevel(hierarchy, "leadership", "S", 250);
        suggestGeneralsForLevel(hierarchy, "strength", "S", 250);
        suggestGeneralsForLevel(hierarchy, "intelligence", "S", 250);

        suggestGeneralsForLevel(hierarchy, "politic", "A", 220);
        suggestGeneralsForLevel(hierarchy, "leadership", "A", 220);
        suggestGeneralsForLevel(hierarchy, "strength", "A", 220);
        suggestGeneralsForLevel(hierarchy, "intelligence", "A", 220);

        suggestGeneralsForLevel(hierarchy, "politic", "B", 190);
        suggestGeneralsForLevel(hierarchy, "leadership", "B", 190);
        suggestGeneralsForLevel(hierarchy, "strength", "B", 190);
        suggestGeneralsForLevel(hierarchy, "intelligence", "B", 190);

        suggestGeneralsForLevel(hierarchy, "politic", "C", 190);
        suggestGeneralsForLevel(hierarchy, "leadership", "C", 190);
        suggestGeneralsForLevel(hierarchy, "strength", "C", 190);
        suggestGeneralsForLevel(hierarchy, "intelligence", "C", 190);
    }

    private static void suggestGeneralsForLevel(GeneralHierarchy hierarchy, String attribute, String level, int minimumAbility) {
        List<WuKingdomNode> suggestedGenerals = hierarchy.suggestGeneralsByAttributeLevel(attribute, minimumAbility);

        System.out.println("Suggested Generals for " + attribute + " " + level + " level:");
        for (int i = 0; i < 3 && i < suggestedGenerals.size(); i++) {
            WuKingdomNode general = suggestedGenerals.get(i);
            System.out.println("General: " + general.getName() + ", Total Ability: " + general.getTotalAbility());
        }
        System.out.println();
    }

    private static GeneralHierarchy createGeneralHierarchy() {
        WuKingdomNode root = new WuKingdomNode("Wu Kingdom", 0, 0, 0, 0, 0);
        Characters characters = new Characters();
        GeneralHierarchy hierarchy = new GeneralHierarchy(root, characters);

        assignGeneralsToDepartments(characters, hierarchy.getRoot());
        return hierarchy;
    }

    private static void assignGeneralsToDepartments(Characters characters, WuKingdomNode node) {
        if (node != null) {
            for (WuKingdomNode child : node.getChildren()) {
                assignGeneralsToDepartments(characters, child);
            }
            if (node.getGeneral() != null) {
                characters.assignGeneralToDepartment(node.getGeneral(), node.getDepartment());
            }
        }
    }
}*/