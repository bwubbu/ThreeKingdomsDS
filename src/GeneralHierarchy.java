/*import java.util.*;

public class GeneralHierarchy {
    public static void main(String[] args) {
        General sunWu = new General("Sun Quan", "Emperor", "Cavalry", 96, 98, 72, 77, 95);
        WuKingdomNode sunWuN = new WuKingdomNode(sunWu);

        General zhouYu = new General("Zhou Yu", "Chief of Military", "Cavalry", 80, 86, 97, 80, 90);
        WuKingdomNode zhouYuN = new WuKingdomNode(zhouYu);

        General zhangZhao = new General("Zhang Zhao", "Chief of Management", "Archer", 22, 80, 89, 99, 60);
        WuKingdomNode zhangZhaoN = new WuKingdomNode(zhangZhao);

        sunWuN.addChild(zhouYuN);
        sunWuN.addChild(zhangZhaoN);

        General xuSheng = new General("Xu Sheng", "General", "Archer", 90, 78, 72, 40, 94);
        General zhuGeJin = new General("Zu Ge Jin", "General", "Archer", 63, 61, 88, 82, 71);
        General luSu = new General("Lu Su", "General", "Infantry", 43, 87, 84, 88, 53);
        General taiShiCi = new General("Tai Shi Ci", "General", "Cavalry", 96, 81, 43, 33, 97);
        General xiaoQiao = new General("Xiao Qiao", "General", "Infantry", 42, 52, 89, 77, 34);
        General daQiao = new General("Da Qiao", "General", "Cavalry", 39, 62, 90, 62, 41);
        General zhouTai = new General("Zhou Tai", "General", "Infantry", 92, 89, 72, 43, 99);
        General ganNing = new General("Gan Ning", "General", "Archer", 98, 92, 45, 23, 97);
        General luMeng = new General("Lu Meng", "General", "Cavalry", 70, 77, 93, 83, 88);
        General huangGai = new General("Huang Gai", "General", "Infantry", 83, 98, 72, 42, 89);

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
            System.out.println(general.getTotalAbility() + " " + general.getName());
            if (general.getIntelligence() > general.getStrength()) {
                zhangZhaoN.addChild(generalNode);
            } else {
                zhouYuN.addChild(generalNode);
            }
        }
        sunWuN.displayWuKingdom();
        System.out.println();

        Comparator<General> abilityComparator = Comparator.comparingInt(General::getTotalAbility);
        Collections.sort(generals, abilityComparator);

        // binary search starts from here
        int targetAbility = 294;
        int index = binarySearch(generals, targetAbility);
        if (index != -1) {
            General targetGeneral = generals.get(index);
            System.out.println("General found with ability " + targetAbility + ": " + targetGeneral.getName());
        } else {
            System.out.println("General not found with ability " + targetAbility);
        }


        Comparator<General> politicComparator = Comparator.comparingInt(General::getPolitic).reversed();
        Comparator<General> leadershipComparator = Comparator.comparingInt(General::getLeadership).reversed();
        Comparator<General> strengthComparator = Comparator.comparingInt(General::getStrength).reversed();
        Comparator<General> intelligenceComparator = Comparator.comparingInt(General::getIntelligence).reversed();

        Collections.sort(generals, politicComparator);
        List<General> politicTeam = formTeam(generals, "politic", 3);

        Set<General> selectedGenerals = new HashSet<>(politicTeam);

        Collections.sort(generals, leadershipComparator);
        List<General> leadershipTeam = formTeam(generals, "leadership", 3, selectedGenerals);

        selectedGenerals.addAll(leadershipTeam);

        Collections.sort(generals, strengthComparator);
        List<General> strengthTeam = formTeam(generals, "strength", 3, selectedGenerals);

        selectedGenerals.addAll(strengthTeam);

        Collections.sort(generals, intelligenceComparator);
        List<General> intelligenceTeam = formTeam(generals, "intelligence", 3, selectedGenerals);

        System.out.println("Politic Team:");
        displayTeam(politicTeam);

        System.out.println("\nLeadership Team:");
        displayTeam(leadershipTeam);

        System.out.println("\nStrength Team:");
        displayTeam(strengthTeam);

        System.out.println("\nIntelligence Team:");
        displayTeam(intelligenceTeam);

    }
    //so that there won't be a 4th parameter
    private static List<General> formTeam(List<General> generals, String field, int count) {
        List<General> team = new ArrayList<>();
        int remainingCount = count;

        for (General general : generals) {
            if (remainingCount == 0) {
                break;
            }

            if (isPositiveStat(general, field)) {
                team.add(general);
                remainingCount--;
            }
        }

        return team;
    }
    // for excluding the same repeating generals
    private static List<General> formTeam(List<General> generals, String field, int count, Set<General> excludedGenerals) {
        List<General> team = new ArrayList<>();
        int remainingCount = count;

        for (General general : generals) {
            if (remainingCount == 0) {
                break;
            }

            if (!excludedGenerals.contains(general) && isPositiveStat(general, field)) {
                boolean canAddToTeam = true;

                // Check if the general's ability sum is higher than other stats
                for (General selectedGeneral : team) {
                    if (getTotalAbility(general) <= getTotalAbility(selectedGeneral)) {
                        canAddToTeam = false;
                        break;
                    }
                }

                if (canAddToTeam) {
                    team.add(general);
                    excludedGenerals.add(general);
                    remainingCount--;
                }
            }
        }

        return team;
    }

    private static int getTotalAbility(General general) {
        return general.getLeadership() + general.getStrength() + general.getIntelligence() + general.getPolitic();
    }
    private static boolean isPositiveStat(General general, String field) {
        int stat;
        switch (field) {
            case "politic":
                stat = general.getPolitic();
                break;
            case "leadership":
                stat = general.getLeadership();
                break;
            case "strength":
                stat = general.getStrength();
                break;
            case "intelligence":
                stat = general.getIntelligence();
                break;
            default:
                return false;
        }
        return stat >= 0;
    }

    private static void displayTeam(List<General> team) {
        for (General general : team) {
            System.out.println("Name: " + general.getName() +
                    ", Politic: " + general.getPolitic() +
                    ", Leadership: " + general.getLeadership() +
                    ", Strength: " + general.getStrength() +
                    ", Intelligence: " + general.getIntelligence() +
                    ", Total Ability : " + general.getTotalAbility());
        }
    }

    private static int binarySearch(List<General> generals, int targetAbility) {
        int left = 0;
        int right = generals.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int ability = generals.get(mid).getTotalAbility();

            if (ability == targetAbility) {
                return mid;
            } else if (ability < targetAbility) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -(left + 1);
    }
}*/
/*
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GeneralHierarchy extends Frame {

    private static final long serialVersionUID = 1L;

    private List<General> generals;

    public GeneralHierarchy() {
        super("General Hierarchy");

        generals = new ArrayList<>();
        General sunWu = new General("Sun Quan", "Emperor", "Cavalry", 96, 98, 72, 77, 95);
        WuKingdomNode sunWuN = new WuKingdomNode(sunWu);

        General zhouYu = new General("Zhou Yu", "Chief of Military", "Cavalry", 80, 86, 97, 80, 90);
        WuKingdomNode zhouYuN = new WuKingdomNode(zhouYu);

        General zhangZhao = new General("Zhang Zhao", "Chief of Management", "Archer", 22, 80, 89, 99, 60);
        WuKingdomNode zhangZhaoN = new WuKingdomNode(zhangZhao);

        sunWuN.addChild(zhouYuN);
        sunWuN.addChild(zhangZhaoN);

        General xuSheng = new General("Xu Sheng", "General", "Archer", 90, 78, 72, 40, 94);
        General zhuGeJin = new General("Zu Ge Jin", "General", "Archer", 63, 61, 88, 82, 71);
        General luSu = new General("Lu Su", "General", "Infantry", 43, 87, 84, 88, 53);
        General taiShiCi = new General("Tai Shi Ci", "General", "Cavalry", 96, 81, 43, 33, 97);
        General xiaoQiao = new General("Xiao Qiao", "General", "Infantry", 42, 52, 89, 77, 34);
        General daQiao = new General("Da Qiao", "General", "Cavalry", 39, 62, 90, 62, 41);
        General zhouTai = new General("Zhou Tai", "General", "Infantry", 92, 89, 72, 43, 99);
        General ganNing = new General("Gan Ning", "General", "Archer", 98, 92, 45, 23, 97);
        General luMeng = new General("Lu Meng", "General", "Cavalry", 70, 77, 93, 83, 88);
        General huangGai = new General("Huang Gai", "General", "Infantry", 83, 98, 72, 42, 89);

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

        // Create a panel to display the generals.
        Panel generalPanel = new Panel();
        generalPanel.setLayout(new GridLayout(generals.size(), 1));

        for (General general : generals) {
            Label label = new Label(general.getName());
            generalPanel.add(label);
        }

        // Create a button to sort the generals by total ability.
        Button sortButton = new Button("Sort by Total Ability");
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Sort the generals by total ability.
                Collections.sort(generals, new Comparator<General>() {
                    @Override
                    public int compare(General o1, General o2) {
                        return o1.getTotalAbility() - o2.getTotalAbility();
                    }
                });

                // Update the display.
                for (int i = 0; i < generals.size(); i++) {
                    Label label = (Label) generalPanel.getComponent(i);
                    label.setText(generals.get(i).getName());
                }
            }
        });

        // Add the panel and button to the frame.
        add(generalPanel, BorderLayout.CENTER);
        add(sortButton, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new GeneralHierarchy();
    }
}*/
/*
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.imageio.ImageIO;

public class GeneralHierarchy extends JFrame {
    private JTextArea outputTextArea;

    public GeneralHierarchy() {
        // Set up the frame
        setTitle("General Hierarchy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));

        // Create the output text area
        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);

        // Add the output text area to a scroll pane
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Set the layout manager
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        // Display the frame
        pack();
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);

        // Call the method to run the code and display the output
        displayOutput();
    }

    private void displayOutput() {
        // Redirect the console output to the JTextArea
        TextAreaOutputStream textOutputStream = new TextAreaOutputStream(outputTextArea);
        System.setOut(new PrintStream(textOutputStream));

        // The code from the original main method
        General sunWu = new General("Sun Quan", "Emperor", "Cavalry", 96, 98, 72, 77, 95);
        WuKingdomNode sunWuN = new WuKingdomNode(sunWu);

        General zhouYu = new General("Zhou Yu", "Chief of Military", "Cavalry", 80, 86, 97, 80, 90);
        WuKingdomNode zhouYuN = new WuKingdomNode(zhouYu);

        General zhangZhao = new General("Zhang Zhao", "Chief of Management", "Archer", 22, 80, 89, 99, 60);
        WuKingdomNode zhangZhaoN = new WuKingdomNode(zhangZhao);

        sunWuN.addChild(zhouYuN);
        sunWuN.addChild(zhangZhaoN);

        General xuSheng = new General("Xu Sheng", "General", "Archer", 90, 78, 72, 40, 94);
        General zhuGeJin = new General("Zu Ge Jin", "General", "Archer", 63, 61, 88, 82, 71);
        General luSu = new General("Lu Su", "General", "Infantry", 43, 87, 84, 88, 53);
        General taiShiCi = new General("Tai Shi Ci", "General", "Cavalry", 96, 81, 43, 33, 97);
        General xiaoQiao = new General("Xiao Qiao", "General", "Infantry", 42, 52, 89, 77, 34);
        General daQiao = new General("Da Qiao", "General", "Cavalry", 39, 62, 90, 62, 41);
        General zhouTai = new General("Zhou Tai", "General", "Infantry", 92, 89, 72, 43, 99);
        General ganNing = new General("Gan Ning", "General", "Archer", 98, 92, 45, 23, 97);
        General luMeng = new General("Lu Meng", "General", "Cavalry", 70, 77, 93, 83, 88);
        General huangGai = new General("Huang Gai", "General", "Infantry", 83, 98, 72, 42, 89);

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
            System.out.println(general.getTotalAbility() + " " + general.getName());
            if (general.getIntelligence() > general.getStrength()) {
                zhangZhaoN.addChild(generalNode);
            } else {
                zhouYuN.addChild(generalNode);
            }
        }
        sunWuN.displayWuKingdom();
        System.out.println();

        Comparator<General> abilityComparator = Comparator.comparingInt(General::getTotalAbility);
        Collections.sort(generals, abilityComparator);

        // binary search starts from here
        int targetAbility = 294;
        int index = binarySearch(generals, targetAbility);
        if (index != -1) {
            General targetGeneral = generals.get(index);
            System.out.println("General found with ability " + targetAbility + ": " + targetGeneral.getName());
        } else {
            System.out.println("General not found with ability " + targetAbility);
        }


        Comparator<General> politicComparator = Comparator.comparingInt(General::getPolitic).reversed();
        Comparator<General> leadershipComparator = Comparator.comparingInt(General::getLeadership).reversed();
        Comparator<General> strengthComparator = Comparator.comparingInt(General::getStrength).reversed();
        Comparator<General> intelligenceComparator = Comparator.comparingInt(General::getIntelligence).reversed();

        Collections.sort(generals, politicComparator);
        List<General> politicTeam = formTeam(generals, "politic", 3);

        Set<General> selectedGenerals = new HashSet<>(politicTeam);

        Collections.sort(generals, leadershipComparator);
        List<General> leadershipTeam = formTeam(generals, "leadership", 3, selectedGenerals);

        selectedGenerals.addAll(leadershipTeam);

        Collections.sort(generals, strengthComparator);
        List<General> strengthTeam = formTeam(generals, "strength", 3, selectedGenerals);

        selectedGenerals.addAll(strengthTeam);

        Collections.sort(generals, intelligenceComparator);
        List<General> intelligenceTeam = formTeam(generals, "intelligence", 3, selectedGenerals);

        System.out.println("Politic Team:");
        displayTeam(politicTeam);

        System.out.println("\nLeadership Team:");
        displayTeam(leadershipTeam);

        System.out.println("\nStrength Team:");
        displayTeam(strengthTeam);

        System.out.println("\nIntelligence Team:");
        displayTeam(intelligenceTeam);

    }

    //so that there won't be a 4th parameter
    private static List<General> formTeam(List<General> generals, String field, int count) {
        List<General> team = new ArrayList<>();
        int remainingCount = count;

        for (General general : generals) {
            if (remainingCount == 0) {
                break;
            }

            if (isPositiveStat(general, field)) {
                team.add(general);
                remainingCount--;
            }
        }

        return team;
    }

    // for excluding the same repeating generals
    private static List<General> formTeam(List<General> generals, String field, int count, Set<General> excludedGenerals) {
        List<General> team = new ArrayList<>();
        int remainingCount = count;

        for (General general : generals) {
            if (remainingCount == 0) {
                break;
            }

            if (!excludedGenerals.contains(general) && isPositiveStat(general, field)) {
                boolean canAddToTeam = true;

                // Check if the general's ability sum is higher than other stats
                for (General selectedGeneral : team) {
                    if (getTotalAbility(general) <= getTotalAbility(selectedGeneral)) {
                        canAddToTeam = false;
                        break;
                    }
                }

                if (canAddToTeam) {
                    team.add(general);
                    excludedGenerals.add(general);
                    remainingCount--;
                }
            }
        }

        return team;
    }

    private static int getTotalAbility(General general) {
        return general.getLeadership() + general.getStrength() + general.getIntelligence() + general.getPolitic();
    }

    private static boolean isPositiveStat(General general, String field) {
        int stat;
        switch (field) {
            case "politic":
                stat = general.getPolitic();
                break;
            case "leadership":
                stat = general.getLeadership();
                break;
            case "strength":
                stat = general.getStrength();
                break;
            case "intelligence":
                stat = general.getIntelligence();
                break;
            default:
                return false;
        }
        return stat >= 0;
    }

    private static void displayTeam(List<General> team) {
        for (General general : team) {
            System.out.println("Name: " + general.getName() +
                    ", Politic: " + general.getPolitic() +
                    ", Leadership: " + general.getLeadership() +
                    ", Strength: " + general.getStrength() +
                    ", Intelligence: " + general.getIntelligence() +
                    ", Total Ability : " + general.getTotalAbility());
        }
    }

    private static int binarySearch(List<General> generals, int targetAbility) {
        int left = 0;
        int right = generals.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int ability = generals.get(mid).getTotalAbility();

            if (ability == targetAbility) {
                return mid;
            } else if (ability < targetAbility) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -(left + 1);
    }
    private void saveOutputToFile(String filename) {
        try {
            File outputFile = new File(filename);
            PrintWriter writer = new PrintWriter(outputFile);

            // Write the output from the JTextArea to the file
            writer.write(outputTextArea.getText());

            writer.close();
            System.out.println("Output saved to file: " + filename);
        } catch (IOException e) {
            System.err.println("Error saving output to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Create the GUI on the event dispatch thread
        SwingUtilities.invokeLater(() -> {
            new GeneralHierarchy();
        });
    }
}

class TextAreaOutputStream extends OutputStream {
    private JTextArea textArea;

    public TextAreaOutputStream(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) throws IOException {
        // Redirect the output to the JTextArea
        textArea.append(String.valueOf((char) b));
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}*/

import java.io.IOException;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import javax.imageio.ImageIO;

public class GeneralHierarchy extends JFrame {
    private JTextArea outputTextArea;
    private JPanel characterPanel;
    private List<General> generals;
    private int currentPage;

    public GeneralHierarchy() {
        // Set up the frame
        setTitle("General Hierarchy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));

        // Create the output text area
        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);

        // Add the output text area to a scroll pane
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Create the character panel
        characterPanel = new JPanel(new BorderLayout());

        // Set the layout manager
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        // Display the frame
        pack();
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);

        // Load the generals and display the first page
        loadGenerals();
        displayPage(0);
    }

    private void loadGenerals() {
        // Load the generals' data
        generals = new ArrayList<>();
        General sunWu = new General("Sun Quan", "Emperor", "Cavalry", 96, 98, 72, 77, 95);
        WuKingdomNode sunWuN = new WuKingdomNode(sunWu);

        General zhouYu = new General("Zhou Yu", "Chief of Military", "Cavalry", 80, 86, 97, 80, 90);
        WuKingdomNode zhouYuN = new WuKingdomNode(zhouYu);

        General zhangZhao = new General("Zhang Zhao", "Chief of Management", "Archer", 22, 80, 89, 99, 60);
        WuKingdomNode zhangZhaoN = new WuKingdomNode(zhangZhao);

        sunWuN.addChild(zhouYuN);
        sunWuN.addChild(zhangZhaoN);

        General xuSheng = new General("Xu Sheng", "General", "Archer", 90, 78, 72, 40, 94);
        General zhuGeJin = new General("Zu Ge Jin", "General", "Archer", 63, 61, 88, 82, 71);
        General luSu = new General("Lu Su", "General", "Infantry", 43, 87, 84, 88, 53);
        General taiShiCi = new General("Tai Shi Ci", "General", "Cavalry", 96, 81, 43, 33, 97);
        General xiaoQiao = new General("Xiao Qiao", "General", "Infantry", 42, 52, 89, 77, 34);
        General daQiao = new General("Da Qiao", "General", "Cavalry", 39, 62, 90, 62, 41);
        General zhouTai = new General("Zhou Tai", "General", "Infantry", 92, 89, 72, 43, 99);
        General ganNing = new General("Gan Ning", "General", "Archer", 98, 92, 45, 23, 97);
        General luMeng = new General("Lu Meng", "General", "Cavalry", 70, 77, 93, 83, 88);
        General huangGai = new General("Huang Gai", "General", "Infantry", 83, 98, 72, 42, 89);

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
            System.out.println(general.getTotalAbility() + " " + general.getName());
            if (general.getIntelligence() > general.getStrength()) {
                zhangZhaoN.addChild(generalNode);
            } else {
                zhouYuN.addChild(generalNode);
            }
        }
        sunWuN.displayWuKingdom();
        System.out.println();

        // Sort the generals by ability
        Comparator<General> abilityComparator = Comparator.comparingInt(General::getTotalAbility);
        Collections.sort(generals, abilityComparator);
        Comparator<General> politicComparator = Comparator.comparingInt(General::getPolitic).reversed();
        Comparator<General> leadershipComparator = Comparator.comparingInt(General::getLeadership).reversed();
        Comparator<General> strengthComparator = Comparator.comparingInt(General::getStrength).reversed();
        Comparator<General> intelligenceComparator = Comparator.comparingInt(General::getIntelligence).reversed();

        Collections.sort(generals, politicComparator);
        List<General> politicTeam = formTeam(generals, "politic", 3);

        Set<General> selectedGenerals = new HashSet<>(politicTeam);

        Collections.sort(generals, leadershipComparator);
        List<General> leadershipTeam = formTeam(generals, "leadership", 3, selectedGenerals);

        selectedGenerals.addAll(leadershipTeam);

        Collections.sort(generals, strengthComparator);
        List<General> strengthTeam = formTeam(generals, "strength", 3, selectedGenerals);

        selectedGenerals.addAll(strengthTeam);

        Collections.sort(generals, intelligenceComparator);
        List<General> intelligenceTeam = formTeam(generals, "intelligence", 3, selectedGenerals);

        System.out.println("Politic Team:");
        displayTeam(politicTeam);

        System.out.println("\nLeadership Team:");
        displayTeam(leadershipTeam);

        System.out.println("\nStrength Team:");
        displayTeam(strengthTeam);

        System.out.println("\nIntelligence Team:");
        displayTeam(intelligenceTeam);

    }

    //so that there won't be a 4th parameter
    private static List<General> formTeam(List<General> generals, String field, int count) {
        List<General> team = new ArrayList<>();
        int remainingCount = count;

        for (General general : generals) {
            if (remainingCount == 0) {
                break;
            }

            if (isPositiveStat(general, field)) {
                team.add(general);
                remainingCount--;
            }
        }

        return team;
    }

    // for excluding the same repeating generals
    private static List<General> formTeam(List<General> generals, String field, int count, Set<General> excludedGenerals) {
        List<General> team = new ArrayList<>();
        int remainingCount = count;

        for (General general : generals) {
            if (remainingCount == 0) {
                break;
            }

            if (!excludedGenerals.contains(general) && isPositiveStat(general, field)) {
                boolean canAddToTeam = true;

                // Check if the general's ability sum is higher than other stats
                for (General selectedGeneral : team) {
                    if (getTotalAbility(general) <= getTotalAbility(selectedGeneral)) {
                        canAddToTeam = false;
                        break;
                    }
                }

                if (canAddToTeam) {
                    team.add(general);
                    excludedGenerals.add(general);
                    remainingCount--;
                }
            }
        }

        return team;
    }

    private static int getTotalAbility(General general) {
        return general.getLeadership() + general.getStrength() + general.getIntelligence() + general.getPolitic();
    }

    private static boolean isPositiveStat(General general, String field) {
        int stat;
        switch (field) {
            case "politic":
                stat = general.getPolitic();
                break;
            case "leadership":
                stat = general.getLeadership();
                break;
            case "strength":
                stat = general.getStrength();
                break;
            case "intelligence":
                stat = general.getIntelligence();
                break;
            default:
                return false;
        }
        return stat >= 0;
    }

    private static void displayTeam(List<General> team) {
        for (General general : team) {
            System.out.println("Name: " + general.getName() +
                    ", Politic: " + general.getPolitic() +
                    ", Leadership: " + general.getLeadership() +
                    ", Strength: " + general.getStrength() +
                    ", Intelligence: " + general.getIntelligence() +
                    ", Total Ability : " + general.getTotalAbility());
        }
    }

    private static int binarySearch(List<General> generals, int targetAbility) {
        int left = 0;
        int right = generals.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int ability = generals.get(mid).getTotalAbility();

            if (ability == targetAbility) {
                return mid;
            } else if (ability < targetAbility) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -(left + 1);
    }
    private void displayPage(int page) {
        currentPage = page;
        characterPanel.removeAll();

        // Calculate the range of generals to display on the current page
        int start = page * 3;
        int end = Math.min(start + 3, generals.size());

        // Create a sub-list of generals for the current page
        List<General> pageGenerals = generals.subList(start, end);

        // Create a panel to hold the character labels
        JPanel pagePanel = new JPanel(new GridLayout(pageGenerals.size(), 1));

        // Iterate over the generals and create a label for each character
        for (General general : pageGenerals) {
            JLabel characterLabel = createCharacterLabel(general);
            pagePanel.add(characterLabel);
        }

        // Add the page panel to the character panel
        characterPanel.add(pagePanel, BorderLayout.CENTER);
        add(characterPanel, BorderLayout.SOUTH);
        validate();
        repaint();
    }

    private JLabel createCharacterLabel(General general) {
        JLabel characterLabel = new JLabel();
        characterLabel.setHorizontalAlignment(JLabel.CENTER);

        // Load and set the character's PNG image
        try {
            BufferedImage image = ImageIO.read(new File(general.getName() + "luSu.png"));
            ImageIcon icon = new ImageIcon(image);
            characterLabel.setIcon(icon);
        } catch (IOException e) {
            System.err.println("Error loading character image: " + e.getMessage());
        }

        // Set the tooltip text to display the character's information
        String tooltip = "Name: " + general.getName() + "\n"
                + "Title: " + general.getTitle() + "\n"
                + "Type: " + general.getArmyType() + "\n"
                + "Politic: " + general.getPolitic() + "\n"
                + "Leadership: " + general.getLeadership() + "\n"
                + "Strength: " + general.getStrength() + "\n"
                + "Intelligence: " + general.getIntelligence() + "\n"
                + "Total Ability: " + general.getTotalAbility();
        characterLabel.setToolTipText(tooltip);

        // Add a mouse click listener to display the character's details in the output text area
        characterLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                outputTextArea.setText(general.getName() + "\n\n" + tooltip);
            }
        });

        return characterLabel;
    }

    public static void main(String[] args) {
        // Create the GUI on the event dispatch thread
        SwingUtilities.invokeLater(() -> {
            new GeneralHierarchy();
        });
    }
}

