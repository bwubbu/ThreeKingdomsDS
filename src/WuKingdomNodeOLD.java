/*

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
        General generic = node.getGeneral();
        System.out.printf("\n" + indent + generic.getName());

        for (WuKingdomNode child : node.getChildren()) {
            displayNode(child, indent + "- ");
        }
    }
}

*/