import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.List;
/*


public class MainMenuGUI extends JFrame {
    private JTextArea outputTextArea;
    private JPanel mainPanel;

    public void displayOutput() {
        // Redirect the console output to the JTextArea
        TextAreaOutputStream textOutputStream = new TextAreaOutputStream(outputTextArea);
        System.setOut(new PrintStream(textOutputStream));

        // Create the main frame
        JFrame mainFrame = new JFrame("Three Kingdoms: Battle of Red Cliff");
        mainFrame.setPreferredSize(new Dimension(800, 600)); // Set the preferred size

        // Create the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create the title label
        JLabel titleLabel = new JLabel("THREE KINGDOMS: BATTLE OF RED CLIFF");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Create the subtitle label
        JLabel subtitleLabel = new JLabel("CHARACTERS");
        subtitleLabel.setFont(new Font("Arial", Font.BOLD, 12));
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(subtitleLabel, BorderLayout.CENTER);

        // Create the button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Create the displayEmperor button
        JButton displayEmperorButton = new JButton("Display Emperor");
        displayEmperorButton.setFont(new Font("Arial", Font.BOLD, 13));
        displayEmperorButton.addActionListener(e -> {
            displayEmperor(mainFrame);
            mainFrame.dispose();
        });
        buttonPanel.add(displayEmperorButton);

        // Create the displayChiefs button
        JButton displayChiefsButton = new JButton("Display Chiefs");
        displayChiefsButton.setFont(new Font("Arial", Font.BOLD, 13));
        displayChiefsButton.addActionListener(e -> {
            displayChiefs(mainFrame);

            mainFrame.dispose();
        });
        buttonPanel.add(displayChiefsButton);

        // Create the displayGenerals button
        JButton displayGeneralsButton = new JButton("Display Generals");
        displayGeneralsButton.setFont(new Font("Arial", Font.BOLD, 13));
        displayGeneralsButton.addActionListener(e -> {
            displayGenerals(mainFrame);

            mainFrame.dispose();
        });
        buttonPanel.add(displayGeneralsButton);

        // Create the displayHierarchy button
        JButton displayHierarchyButton = new JButton("Wu Kingdom Hierarchy");
        displayHierarchyButton.setFont(new Font("Arial", Font.BOLD, 13));
        displayHierarchyButton.addActionListener(e -> {
            displayHierarchy(mainFrame);

            mainFrame.dispose();
        });
        buttonPanel.add(displayHierarchyButton);

        // Create the displayGeneralFind button
        JButton displayGeneralFindButton = new JButton("Find a General");
        displayGeneralFindButton.setFont(new Font("Arial", Font.BOLD, 13));
        displayGeneralFindButton.addActionListener(e -> {

            mainFrame.dispose();
        });
        buttonPanel.add(displayGeneralFindButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        ImageIcon imageIcon = new ImageIcon("C:\\Users\\user\\IdeaProjects\\ThreeKingdomsDS\\src\\Characters\\mainMenu.png");
        JLabel imageLabel = new JLabel(imageIcon);
        mainPanel.add(imageLabel, BorderLayout.CENTER);

        // Add the main panel to the frame
        mainFrame.getContentPane().add(mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null); // Center the frame
        mainFrame.setVisible(true);
    }

    private void displayEmperor(JFrame mainFrame) {
        General sunWu = new General("Sun Quan", "sunWu.png", "Emperor", "Cavalry", 96, 98, 72, 77, 95);

        ArrayList<General> chiefs = new ArrayList<>();
        chiefs.add(sunWu);

        for (General chief : chiefs) {
            WuKingdomNode chiefNode = new WuKingdomNode(chief);
            int totalAbility = chief.getTotalAbility();
            if (totalAbility >= 250) {
                chief.setAbilityLevel("S");
            } else if (totalAbility >= 220) {
                chief.setAbilityLevel("A");
            } else if (totalAbility >= 190) {
                chief.setAbilityLevel("B");
            } else {
                chief.setAbilityLevel("C");
            }
        }

        JFrame emperorFrame = new JFrame("Emperor");
        emperorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        emperorFrame.setPreferredSize(new Dimension(600, 600)); // Set the preferred size

        JPanel emperorContentPanel = new JPanel();
        emperorContentPanel.setLayout(new GridLayout(chiefs.size(), 6));

        for (General chief : chiefs) {
            String imageFileName = "C:\\Users\\user\\IdeaProjects\\ThreeKingdomsDS\\src\\Characters\\" + chief.getImageFileName();
            try {
                BufferedImage image = ImageIO.read(new File(imageFileName));
                ImageIcon icon = new ImageIcon(image);
                JLabel imageLabel = new JLabel(icon);
                emperorContentPanel.add(imageLabel);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String chiefInfo = "Name: " + chief.getName() +
                    "\nArmy Type: " + chief.getArmyType() +
                    "\nRole: " + chief.getDepartment() +
                    "\nPolitic: " + chief.getPolitic() +
                    "\nLeadership: " + chief.getLeadership() +
                    "\nStrength: " + chief.getStrength() +
                    "\nIntelligence: " + chief.getIntelligence() +
                    "\nHit Point: " + chief.getHitPoint() +
                    "\nTotal Ability: " + chief.getTotalAbility() +
                    "\nAbility Level: " + chief.getAbilityLevel();
            JTextArea textArea = new JTextArea(chiefInfo);
            textArea.setEditable(false);
            emperorContentPanel.add(textArea);
        }
        JScrollPane chiefScrollPane = new JScrollPane(emperorContentPanel);
        chiefScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        emperorFrame.add(chiefScrollPane, BorderLayout.CENTER);
        emperorFrame.setLocationRelativeTo(null); // Center the frame
        emperorFrame.pack();
        emperorFrame.setVisible(true);

        // Create the button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.addActionListener(e -> {
            mainFrame.getContentPane().removeAll(); // Remove all components from the frame
            displayOutput(); // Display the main screen again
            emperorFrame.dispose();
        });
        emperorFrame.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(backButton);
    }

    private void displayChiefs(JFrame mainFrame) {
        General zhouYu = new General("Zhou Yu", "zhouYu.png", "Chief of Military", "Cavalry", 80, 86, 97, 80, 90);
        General zhangZhao = new General("Zhang Zhao", "zhangZhao.png", "Chief of Management", "Archer", 22, 80, 89, 99, 60);

        ArrayList<General> chiefs = new ArrayList<>();
        chiefs.add(zhouYu);
        chiefs.add(zhangZhao);

        for (General chief : chiefs) {
            WuKingdomNode chiefNode = new WuKingdomNode(chief);
            int totalAbility = chief.getTotalAbility();
            if (totalAbility >= 250) {
                chief.setAbilityLevel("S");
            } else if (totalAbility >= 220) {
                chief.setAbilityLevel("A");
            } else if (totalAbility >= 190) {
                chief.setAbilityLevel("B");
            } else {
                chief.setAbilityLevel("C");
            }
        }

        JFrame chiefFrame = new JFrame("Chiefs");
        chiefFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chiefFrame.setPreferredSize(new Dimension(600, 600)); // Set the preferred size

        JPanel chiefContentPanel = new JPanel();
        chiefContentPanel.setLayout(new GridLayout(chiefs.size(), 12));

        for (General chief : chiefs) {

            String imageFileName = "C:\\Users\\user\\IdeaProjects\\ThreeKingdomsDS\\src\\Characters\\" + chief.getImageFileName();
            try {
                BufferedImage image = ImageIO.read(new File(imageFileName));
                ImageIcon icon = new ImageIcon(image);
                JLabel imageLabel = new JLabel(icon);
                chiefContentPanel.add(imageLabel);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String chiefInfo = "Name: " + chief.getName() +
                    "\nArmy Type: " + chief.getArmyType() +
                    "\nRole: " + chief.getDepartment() +
                    "\nPolitic: " + chief.getPolitic() +
                    "\nLeadership: " + chief.getLeadership() +
                    "\nStrength: " + chief.getStrength() +
                    "\nIntelligence: " + chief.getIntelligence() +
                    "\nHit Point: " + chief.getHitPoint() +
                    "\nTotal Ability: " + chief.getTotalAbility() +
                    "\nAbility Level: " + chief.getAbilityLevel();
            JTextArea textArea = new JTextArea(chiefInfo);
            textArea.setEditable(false);
            chiefContentPanel.add(textArea);
        }
        JScrollPane chiefScrollPane = new JScrollPane(chiefContentPanel);
        chiefScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        chiefFrame.add(chiefScrollPane, BorderLayout.CENTER);
        chiefFrame.setLocationRelativeTo(mainFrame); // Center the frame
        chiefFrame.pack();
        chiefFrame.setVisible(true);

        // Create the button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.addActionListener(e -> {
            mainFrame.getContentPane().removeAll(); // Remove all components from the frame
            displayOutput(); // Display the main screen again
            chiefFrame.dispose();
        });
        chiefFrame.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(backButton);
    }

    public void displayGenerals(JFrame mainFrame) {
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

        ArrayList<General> generals = new ArrayList<>();
        generals.add(xuSheng);
        generals.add(zhuGeJin);
        generals.add(luSu);
        generals.add(taiShiCi);
        generals.add(xiaoQiao);
        generals.add(daQiao);
        generals.add(zhouTai);
        generals.add(ganNing);
        generals.add(luMeng);
        generals.add(huangGai);

        for (General general : generals) {
            WuKingdomNode generalNode = new WuKingdomNode(general);
            int totalAbility = general.getTotalAbility();

            if (totalAbility >= 250) {
                general.setAbilityLevel("S");
            } else if (totalAbility >= 220) {
                general.setAbilityLevel("A");
            } else if (totalAbility >= 190) {
                general.setAbilityLevel("B");
            } else {
                general.setAbilityLevel("C");
            }
        }

        JFrame generalFrame = new JFrame("Generals");
        generalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        generalFrame.setPreferredSize(new Dimension(600, 600)); // Set the preferred size

        JPanel generalContentPanel = new JPanel();
        generalContentPanel.setLayout(new GridLayout(generals.size(), 12));

        for (General general : generals) {

            String imageFileName = "C:\\Users\\user\\IdeaProjects\\ThreeKingdomsDS\\src\\Characters\\" + general.getImageFileName();
            try {
                BufferedImage image = ImageIO.read(new File(imageFileName));
                ImageIcon icon = new ImageIcon(image);
                JLabel imageLabel = new JLabel(icon);
                generalContentPanel.add(imageLabel);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String generalInfo = "Name: " + general.getName() +
                    "\nArmy Type: " + general.getArmyType() +
                    "\nRole: " + general.getDepartment() +
                    "\nPolitic: " + general.getPolitic() +
                    "\nLeadership: " + general.getLeadership() +
                    "\nStrength: " + general.getStrength() +
                    "\nIntelligence: " + general.getIntelligence() +
                    "\nHit Point: " + general.getHitPoint() +
                    "\nTotal Ability: " + general.getTotalAbility() +
                    "\nAbility Level: " + general.getAbilityLevel();
            JTextArea textArea = new JTextArea(generalInfo);
            textArea.setEditable(false);
            generalContentPanel.add(textArea);
        }
        JScrollPane chiefScrollPane = new JScrollPane(generalContentPanel);
        chiefScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        generalFrame.add(chiefScrollPane, BorderLayout.CENTER);
        generalFrame.setLocationRelativeTo(mainFrame); // Center the frame
        generalFrame.pack();
        generalFrame.setVisible(true);

        // Create the button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.addActionListener(e -> {
            mainFrame.getContentPane().removeAll(); // Remove all components from the frame
            displayOutput(); // Display the main screen again
            generalFrame.dispose();
        });
        generalFrame.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(backButton);
    }

    private void displayHierarchy(JFrame mainFrame) {

        JFrame hierarchyFrame = new JFrame("Wu Kingdom Hierarchy");
        hierarchyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hierarchyFrame.setPreferredSize(new Dimension(600, 600)); // Set the preferred size

        JPanel hierarchyContentPanel = new JPanel();

        GeneralHierarchy hierarchy = new GeneralHierarchy();

        JScrollPane HierarchyScrollPane = new JScrollPane(hierarchyContentPanel);
        HierarchyScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        hierarchyFrame.add(HierarchyScrollPane, BorderLayout.CENTER);
        hierarchyFrame.setLocationRelativeTo(mainFrame); // Center the frame
        hierarchyFrame.pack();
        hierarchyFrame.setVisible(true);

        // Create the button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.addActionListener(e -> {
            mainFrame.getContentPane().removeAll(); // Remove all components from the frame
            displayOutput(); // Display the main screen again
            hierarchyFrame.dispose();
        });
        hierarchyFrame.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(backButton);
    }

    public void generalFinder(JFrame mainFrame) {
        JFrame generalFinderFrame = new JFrame("General Finder");
        generalFinderFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        generalFinderFrame.setPreferredSize(new Dimension(600, 600)); // Set the preferred size

        JPanel generalFinderContentPanel = new JPanel();
        JScrollPane HierarchyScrollPane = new JScrollPane(generalFinderContentPanel);
        HierarchyScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        generalFinderFrame.add(HierarchyScrollPane, BorderLayout.CENTER);
        generalFinderFrame.setLocationRelativeTo(mainFrame); // Center the frame
        generalFinderFrame.pack();
        generalFinderFrame.setVisible(true);

        // Ask the user to input a number
        String input = JOptionPane.showInputDialog(generalFinderFrame, "Enter a number:");

        // Parse the user input as an integer
        int num = Integer.parseInt(input);

        // Create the button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.addActionListener(e -> {
            mainFrame.getContentPane().removeAll(); // Remove all components from the frame
            displayOutput(); // Display the main screen again
            generalFinderFrame.dispose();
        });
        generalFinderFrame.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(backButton);
        }
    }
    class TextAreaOutputStream extends OutputStream {
        private final JTextArea textArea;

        public TextAreaOutputStream(JTextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void write(int b) throws IOException {
            // Redirect the output to the JTextArea
            textArea.append(String.valueOf((char) b));
            textArea.setCaretPosition(textArea.getDocument().getLength());
        }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenuGUI menu = new MainMenuGUI();
            menu.displayOutput();
        });
    }
}

*/
