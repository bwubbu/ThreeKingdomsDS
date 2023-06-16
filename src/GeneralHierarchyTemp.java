import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
public class GeneralHierarchyTemp extends JFrame {
    private JTextArea outputTextArea;

    public GeneralHierarchyTemp() {
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

        // Create the displayCharacters button
        JButton displayCharacterButton = new JButton("Characters");
        displayCharacterButton.setFont(new Font("Arial", Font.BOLD, 13));
        displayCharacterButton.addActionListener(e -> {
            displayCharacters(mainFrame);

            mainFrame.dispose();
        });
        buttonPanel.add(displayCharacterButton);

        // Create the characterFinder button
        JButton characterFinderButton = new JButton("Character Finder");
        characterFinderButton.setFont(new Font("Arial", Font.BOLD, 13));
        characterFinderButton.addActionListener(e -> {
            displayCharacters(mainFrame);

            mainFrame.dispose();
        });
        buttonPanel.add(characterFinderButton);

        // Create the hierarchy button
        JButton displayHierarchyButton = new JButton("Hierarchy");
        displayHierarchyButton.setFont(new Font("Arial", Font.BOLD, 13));
        displayHierarchyButton.addActionListener(e -> {
            displayCharacters(mainFrame);

            mainFrame.dispose();
        });
        buttonPanel.add(displayHierarchyButton);

        // Create the Team button
        JButton displayTeamButton = new JButton("Team");
        displayTeamButton.setFont(new Font("Arial", Font.BOLD, 13));
        displayTeamButton.addActionListener(e -> {
            displayCharacters(mainFrame);

            mainFrame.dispose();
        });
        buttonPanel.add(displayTeamButton);

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
    private void displayCharacters(JFrame mainFrame) {
        General sunWu = new General("Sun Quan", "sunWu.png", "Emperor", "Cavalry", 96, 98, 72, 77, 95);
        WuKingdomNode sunWuN = new WuKingdomNode(sunWu);

        General zhouYu = new General("Zhou Yu", "zhouYu.png", "Chief of Military", "Cavalry", 80, 86, 97, 80, 90);
        WuKingdomNode zhouYuN = new WuKingdomNode(zhouYu);

        General zhangZhao = new General("Zhang Zhao", "zhangZhao.png", "Chief of Management", "Archer", 22, 80, 89, 99, 60);
        WuKingdomNode zhangZhaoN = new WuKingdomNode(zhangZhao);

        sunWuN.addChild(zhouYuN);
        sunWuN.addChild(zhangZhaoN);

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

        ArrayList<General> chiefs = new ArrayList<>();
        chiefs.add(sunWu);
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
        chiefFrame.setPreferredSize(new Dimension(700, 500)); // Set the preferred size

        JPanel chiefContentPanel = new JPanel();
        chiefContentPanel.setLayout(new GridLayout(chiefs.size(), 6));

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

            if (general.getIntelligence() > general.getStrength()) {
                zhangZhaoN.addChild(generalNode);
            } else {
                zhouYuN.addChild(generalNode);
            }
        }
        sunWuN.displayWuKingdom();
        System.out.println();

        Comparator<General> abilityComparator = Comparator.comparingInt(General::getTotalAbility);
        generals.sort(abilityComparator);

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

        generals.sort(politicComparator);
        List<General> politicTeam = formTeam(generals, "politic");
        Set<General> selectedGenerals = new HashSet<>(politicTeam);
        generals.sort(leadershipComparator);
        List<General> leadershipTeam = formTeam(generals, "leadership", selectedGenerals);
        selectedGenerals.addAll(leadershipTeam);
        generals.sort(strengthComparator);
        List<General> strengthTeam = formTeam(generals, "strength", selectedGenerals);
        selectedGenerals.addAll(strengthTeam);
        generals.sort(intelligenceComparator);
        List<General> intelligenceTeam = formTeam(generals, "intelligence", selectedGenerals);

        System.out.println("Politic Team:");
        displayTeam(politicTeam);

        System.out.println("\nLeadership Team:");
        displayTeam(leadershipTeam);

        System.out.println("\nStrength Team:");
        displayTeam(strengthTeam);

        System.out.println("\nIntelligence Team:");
        displayTeam(intelligenceTeam);

        JFrame generalFrame = new JFrame("Generals");
        generalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        generalFrame.setPreferredSize(new Dimension(700, 500)); // Set the preferred size

        JPanel generalContentPanel = new JPanel();
        generalContentPanel.setLayout(new GridLayout(generals.size(), 6));

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
        JScrollPane generalScrollPane = new JScrollPane(generalContentPanel);
        generalScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        generalFrame.add(generalScrollPane, BorderLayout.CENTER);
        generalFrame.setLocationRelativeTo(mainFrame); // Center the frame
        generalFrame.pack();
        generalFrame.setVisible(false);

        // Create a button for switching to the general frame
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chiefFrame.setVisible(false);  // Hide the chief frame
                generalFrame.setVisible(true); // Show the general frame
            }
        });

        chiefFrame.add(nextButton, BorderLayout.SOUTH); // Add the button to the chief frame
    }
    private void playMp3() {
        // Create a JavaFX panel to initialize JavaFX toolkit
        JFXPanel fxPanel = new JFXPanel();

        // Create a Media object with the file path
        Media media = new Media(new File("C:\\Users\\user\\IdeaProjects\\ThreeKingdomsDS\\src\\liyue_fight_song.mp3").toURI().toString());

        // Create a MediaPlayer to play the media
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        // Set the MediaPlayer to play once the media is ready
        mediaPlayer.setOnReady(mediaPlayer::play);

        // Ensure JavaFX Platform exits after media playback is finished
        mediaPlayer.setOnEndOfMedia(Platform::exit);
    }
    //so that there won't be a 4th parameter
    private static List<General> formTeam(List<General> generals, String field) {
        List<General> team = new ArrayList<>();
        int remainingCount = 3;

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
    private static List<General> formTeam(List<General> generals, String field, Set<General> excludedGenerals) {
        List<General> team = new ArrayList<>();
        int remainingCount = 3;

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
            case "politic" -> stat = general.getPolitic();
            case "leadership" -> stat = general.getLeadership();
            case "strength" -> stat = general.getStrength();
            case "intelligence" -> stat = general.getIntelligence();
            default -> {
                return false;
            }
        }
        return stat >= 0;
    }

    public static void displayTeam(List<General> team) {
        for (General general : team) {
            System.out.println(general.getName());
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
    static class TextAreaOutputStream extends OutputStream {
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
    }
    public static void main(String[] args) {
        // Create the GUI on the event dispatch thread
        SwingUtilities.invokeLater(GeneralHierarchyTemp::new);
    }
}