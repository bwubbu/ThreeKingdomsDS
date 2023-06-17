import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class HierarchyGUI extends JFrame {
    private JTree tree;
    private Map<String, General> generalMap;
    private final String imagePath = "C:\\Users\\user\\IdeaProjects\\ThreeKingdomsDS\\src\\Characters\\"; // Replace with the actual path to the images

    public HierarchyGUI(DefaultMutableTreeNode rootNode, Map<String, General> generalMap) {
        super("Wu Kingdom Hierarchy");

        this.generalMap = generalMap;

        // Create the JTree using the root node
        tree = new JTree(rootNode);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        // Customize the cell renderer to display the general's name and picture as the tree node text
        DefaultTreeCellRenderer cellRenderer = new DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,
                                                          boolean expanded, boolean leaf, int row, boolean hasFocus) {
                super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

                // Set the general's name and picture as the tree node text
                if (value instanceof DefaultMutableTreeNode) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
                    Object userObject = node.getUserObject();
                    if (userObject instanceof General) {
                        General general = (General) userObject;
                        setText(general.getName());
                        setIcon(new ImageIcon(imagePath + general.getImageFileName()));
                    }
                }

                return this;
            }
        };

        // Set the customized cell renderer for the JTree
        tree.setCellRenderer(cellRenderer);

        // Create a JScrollPane to add scrolling functionality
        JScrollPane scrollPane = new JScrollPane(tree);

        // Add the JScrollPane to the JFrame
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the JFrame on the screen
        setVisible(true);

        // Add a selection listener to the JTree
        tree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if (selectedNode != null && selectedNode.getUserObject() instanceof General) {
                General selectedGeneral = (General) selectedNode.getUserObject();
                displayGeneralInfo(selectedGeneral);
            }
        });
    }

    private void displayGeneralInfo(General general) {
        // Create a new JFrame to hold the information
        JFrame infoFrame = new JFrame(general.getName() + " Information");
        infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        infoFrame.setSize(400, 300);
        infoFrame.setLocationRelativeTo(this);

        // Create a new JPanel to hold the general's information
        JPanel infoPanel = new JPanel(new GridLayout(8, 3));

        // Create and add the components to the infoPanel

        JLabel nameLabel = new JLabel("Name: " + general.getName());
        JLabel titleLabel = new JLabel("Title: " + general.getTitle());
        JLabel typeLabel = new JLabel("Type: " + general.getArmyType());
        JLabel intelligenceLabel = new JLabel("Intelligence: " + general.getIntelligence());
        JLabel strengthLabel = new JLabel("Strength: " + general.getStrength());
        JLabel leadershipLabel = new JLabel("Leadership: " + general.getLeadership());
        JLabel politicLabel = new JLabel("Politics: " + general.getPolitic());
        JLabel hitpointLabel = new JLabel("Hit Point: " + general.getHitPoint());

        infoPanel.add(nameLabel);
        infoPanel.add(titleLabel);
        infoPanel.add(typeLabel);
        infoPanel.add(intelligenceLabel);
        infoPanel.add(strengthLabel);
        infoPanel.add(leadershipLabel);
        infoPanel.add(politicLabel);
        infoPanel.add(hitpointLabel);

// Add the infoPanel to the infoFrame's content pane using BorderLayout.CENTER
        infoFrame.getContentPane().add(infoPanel, BorderLayout.CENTER);

// Create the button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

// Create a back button to close the information frame
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoFrame.dispose(); // Close the information frame
            }
        });

// Add the back button to the button panel
        buttonPanel.add(backButton);

// Add the button panel to the infoFrame's content pane using BorderLayout.SOUTH
        infoFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

// Make the infoFrame visible
        infoFrame.setVisible(true);
    }
    public static void main(String[] args) {
        General sunQuan = new General("Sun Quan", "sunWu.png", "Emperor", "Cavalry", 96, 98, 72, 77, 95);
        WuKingdomNode sunQuanNode = new WuKingdomNode(sunQuan);

        General zhouYu = new General("Zhou Yu", "zhouYu.png", "Chief of Military", "Cavalry", 80, 86, 97, 80, 90);
        WuKingdomNode zhouYuNode = new WuKingdomNode(zhouYu);

        General zhangZhao = new General("Zhang Zhao", "zhangZhao.png", "Chief of Management", "Archer", 22, 80, 89, 99, 60);
        WuKingdomNode zhangZhaoNode = new WuKingdomNode(zhangZhao);

        sunQuanNode.addChild(zhouYuNode);
        sunQuanNode.addChild(zhangZhaoNode);

        General xuSheng = new General("Xu Sheng", "xuSheng.png", "General", "Archer", 90, 78, 72, 40, 94);
        General zhuGeJin = new General("Zu Ge Jin", "zhuGeJin.png", "General", "Archer", 63, 61, 88, 82, 71);
        General luSu = new General("Lu Su", "luSu.png", "General", "Infantry", 43, 87, 84, 88, 53);
        General taiShiCi = new General("Tai Shi Ci", "taiShiCi.png", "General", "Cavalry", 96, 81, 43, 33, 97);
        General xiaoQiao = new General("Xiao Qiao", "xiaoQiao.png", "General", "Infantry", 42, 52, 89, 77, 34);
        General daQiao = new General("Da Qiao", "daQiao.png", "General", "Cavalry", 39, 62, 90, 62, 41);
        General zhouTai = new General("Zhou Tai", "zhouTai.png", "General", "Infantry", 92, 89, 72, 43, 99);
        General ganNing = new General("Gan Ning", "ganNing.png", "General", "Archer", 98, 92, 45, 23, 97);
        General luMeng = new General("Lu Meng", "luMeng.png", "General", "Cavalry", 70, 77, 93, 83, 88);
        General huangGai = new General("Huang Gai", "huangGai.png", "General", "Infantry", 83, 98, 72, 42, 89);

        Map<String, General> generalMap = new HashMap<>();
        generalMap.put(xuSheng.getName(), xuSheng);
        generalMap.put(zhuGeJin.getName(), zhuGeJin);
        generalMap.put(luSu.getName(), luSu);
        generalMap.put(taiShiCi.getName(), taiShiCi);
        generalMap.put(xiaoQiao.getName(), xiaoQiao);
        generalMap.put(daQiao.getName(), daQiao);
        generalMap.put(zhouTai.getName(), zhouTai);
        generalMap.put(ganNing.getName(), ganNing);
        generalMap.put(luMeng.getName(), luMeng);
        generalMap.put(huangGai.getName(), huangGai);

        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Sun Quan");

        DefaultMutableTreeNode zhouYuNode1 = new DefaultMutableTreeNode("Zhou Yu");
        rootNode.add(zhouYuNode1);

        DefaultMutableTreeNode zhangZhaoNode1 = new DefaultMutableTreeNode("Zhang Zhao");
        rootNode.add(zhangZhaoNode1);

        for (General general : generalMap.values()) {
            DefaultMutableTreeNode generalNode1 = new DefaultMutableTreeNode(general);
            WuKingdomNode generalNode = new WuKingdomNode(general);
            if (general.getIntelligence() > general.getStrength()) {
                zhangZhaoNode.addChild(generalNode);
                zhangZhaoNode1.add(generalNode1);
            } else {
                zhouYuNode.addChild(generalNode);
                zhouYuNode1.add(generalNode1);
            }
        }

        // Create and display the GUI
        HierarchyGUI gui = new HierarchyGUI(rootNode, generalMap);
    }
}