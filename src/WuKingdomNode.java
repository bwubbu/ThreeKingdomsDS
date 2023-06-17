import java.util.ArrayList;
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