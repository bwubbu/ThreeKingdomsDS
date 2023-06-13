import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.lang.*;
class General implements Comparable<General> {
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
    private General right;
    private General left;

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
        this.right = null;
        this.left = null;
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
    /*
    public class ImagePrinter {
        public static void printImage(String imageFileName) {
            JFrame frame = new JFrame();
            frame.setLayout(new BorderLayout());

            // Load and display the image using Java's ImageIO
            try {
                BufferedImage image = ImageIO.read(new File(imageFileName));
                ImageIcon icon = new ImageIcon(image);
                JLabel imageLabel = new JLabel(icon);
                frame.getContentPane().add(imageLabel, BorderLayout.CENTER);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Create a text area to display the image file path
            JTextArea textArea = new JTextArea("Image: " + imageFileName);
            textArea.setEditable(false);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            JScrollPane scrollPane = new JScrollPane(textArea);
            frame.getContentPane().add(scrollPane, BorderLayout.SOUTH);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }
    }*/
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
    public General getRight() {
        return right;
    }
    public General getLeft() {
        return left;
    }
    // putting setters here

    public void setRight(General right) {
        this.right = right;
    }

    public void setLeft(General right) {
        this.left = left;
    }

    public int getTotalAbility() {
        return politic + leadership + strength + intelligence;
    }

    public void setAbilityLevel(String abilityLevel) {
        this.abilityLevel = abilityLevel;
    }

    public String getDepartment(){
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

/*

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;

class General {
    private String name;
    private String title;
    private String type;
    private int politic;
    private int leadership;
    private int strength;
    private int intelligence;
    private int hitPoint;
    private String imageFilename;

    public General(String name, String title, String type, int politic, int leadership, int strength, int intelligence, int hitPoint, String imageFilename) {
        this.name = name;
        this.title = title;
        this.type = type;
        this.politic = politic;
        this.leadership = leadership;
        this.strength = strength;
        this.intelligence = intelligence;
        this.hitPoint = hitPoint;
        this.imageFilename = imageFilename;
    }

    // Getters for the general's information

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public int getPolitic() {
        return politic;
    }

    public int getLeadership() {
        return leadership;
    }

    public int getStrength() {
        return strength;
    }

    public int getIntelligence() {
        return intelligence;
    }
    public int getHitPoint() {
        return hitPoint;
    }
    public int getTotalAbility() {
        return politic + leadership + strength + intelligence;
    }
    public String getImageFilename() {
        return imageFilename;
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

*/