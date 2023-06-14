import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
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

            // Create the main frame
            JFrame frame = new JFrame("Three Kingdoms: Battle of Red Cliff");
            frame.setPreferredSize(new Dimension(800, 400)); // Set the preferred size

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
            subtitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
            subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            mainPanel.add(subtitleLabel, BorderLayout.CENTER);

            // Create the start button
            JButton startButton = new JButton("Start");
            startButton.setFont(new Font("Arial", Font.BOLD, 16));
            startButton.addActionListener(e -> {

                displayCharacters(frame);

                frame.dispose();
            });
            mainPanel.add(startButton, BorderLayout.SOUTH);

            ImageIcon imageIcon = new ImageIcon("C:\\Users\\user\\IdeaProjects\\ThreeKingdomsDS\\src\\Characters\\mainMenu.png");
            JLabel imageLabel = new JLabel(imageIcon);
            mainPanel.add(imageLabel, BorderLayout.CENTER);
            // Add the main panel to the frame
            frame.getContentPane().add(mainPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null); // Center the frame
            frame.setVisible(true);
            playMp3("C:\\Users\\user\\IdeaProjects\\ThreeKingdomsDS\\src\\song.mp3");
        }
    private void displayCharacters(JFrame frame) {
        General sunWu = new General("Sun Quan", "sunWu.png","Emperor", "Cavalry", 96, 98, 72, 77, 95);
        WuKingdomNode sunWuN = new WuKingdomNode(sunWu);

        General zhouYu = new General("Zhou Yu","zhouYu.png", "Chief of Military", "Cavalry", 80, 86, 97, 80, 90);
        WuKingdomNode zhouYuN = new WuKingdomNode(zhouYu);

        General zhangZhao = new General("Zhang Zhao","zhangZhao.png", "Chief of Management", "Archer", 22, 80, 89, 99, 60);
        WuKingdomNode zhangZhaoN = new WuKingdomNode(zhangZhao);

        sunWuN.addChild(zhouYuN);
        sunWuN.addChild(zhangZhaoN);

        General xuSheng = new General("Xu Sheng","xuSheng.png", "General", "Archer", 90, 78, 72, 40, 94);
        General zhuGeJin = new General("Zu Ge Jin", "zhuGeJin.png","General", "Archer", 63, 61, 88, 82, 71);
        General luSu = new General("Lu Su","luSu.png", "General", "Infantry", 43, 87, 84, 88, 53);
        General taiShiCi = new General("Tai Shi Ci","taiShiCi.png", "General", "Cavalry", 96, 81, 43, 33, 97);
        General xiaoQiao = new General("Xiao Qiao","xiaoQiao.png", "General", "Infantry", 42, 52, 89, 77, 34);
        General daQiao = new General("Da Qiao", "daQiao.png","General", "Cavalry", 39, 62, 90, 62, 41);
        General zhouTai = new General("Zhou Tai", "zhouTai.png","General", "Infantry", 92, 89, 72, 43, 99);
        General ganNing = new General("Gan Ning","ganNing.png", "General", "Archer", 98, 92, 45, 23, 97);
        General luMeng = new General("Lu Meng","luMeng.png", "General", "Cavalry", 70, 77, 93, 83, 88);
        General huangGai = new General("Huang Gai", "huangGai.png","General", "Infantry", 83, 98, 72, 42, 89);

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

            System.out.println(general.getName() + "'s Total Ability: " + general.getTotalAbility());
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

            JFrame frame2 = new JFrame("Generals");


            frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel contentPanel = new JPanel();
            contentPanel.setLayout(new GridLayout(generals.size(), 6));

            for (General general : generals) {

                String imageFileName = "C:\\Users\\user\\IdeaProjects\\ThreeKingdomsDS\\src\\Characters\\" + general.getImageFileName();
                try {
                    BufferedImage image = ImageIO.read(new File(imageFileName));
                    ImageIcon icon = new ImageIcon(image);
                    JLabel imageLabel = new JLabel(icon);
                    contentPanel.add(imageLabel);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String generalInfo = "Name: " + general.getName() +
                        "\nArmy Type: " + general.getArmyType() +
                        "\nDepartment: " + general.getDepartment() +
                        "\nPolitic: " + general.getPolitic() +
                        "\nLeadership: " + general.getLeadership() +
                        "\nStrength: " + general.getStrength() +
                        "\nIntelligence: " + general.getIntelligence() +
                        "\nHit Point: " + general.getHitPoint() +
                        "\nTotal Ability: " + general.getTotalAbility() +
                        "\nAbility Level: " + general.getAbilityLevel();


                JTextArea textArea = new JTextArea(generalInfo);
                textArea.setEditable(false);
                contentPanel.add(textArea);

            }
            JScrollPane scrollPane = new JScrollPane(contentPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

            frame2.setLocationRelativeTo(null); // Center the frame
            frame2.add(scrollPane, BorderLayout.CENTER);
            frame2.pack();
            frame2.setVisible(true);
        }

    private void playMp3(String filePath) {
        // Create a JavaFX panel to initialize JavaFX toolkit
        JFXPanel fxPanel = new JFXPanel();

        // Create a Media object with the file path
        Media media = new Media(new File(filePath).toURI().toString());

        // Create a MediaPlayer to play the media
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        // Set the MediaPlayer to play once the media is ready
        mediaPlayer.setOnReady(() -> {
            mediaPlayer.play();
        });

        // Ensure JavaFX Platform exits after media playback is finished
        mediaPlayer.setOnEndOfMedia(() -> {
            Platform.exit();
        });
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

    public static void displayTeam(List<General> team) {


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
}