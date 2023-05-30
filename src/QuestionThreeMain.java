/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class QuestionThreeMain {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
    Boats boat = new Boats();
    ArrayList<Integer> arrowshot = new ArrayList<>();
    ArrayList<String> Direction = new ArrayList<>();
    ArrayList<Integer> receivedArrow = new ArrayList<>();
    
    System.out.println("Front Part");
    boat.setFront(putStrawman());
    System.out.println("Left Part");
    boat.setLeft(putStrawman());
    System.out.println("Right Part");
    boat.setRight(putStrawman());
    System.out.println("Back Part");
    boat.setBack(putStrawman());
    int waves=0;
    while(true){
        System.out.println("Please input a waves that less then 12");
        waves = input.nextInt();
        if(waves>12 || waves<0){
            System.out.println("Please enter a correct waves number");
        }
        else
            break;
    }
    for(int i =0; i<waves ; i++){
        System.out.printf("Please enter number of arrow for wave %d=",i+1);
        arrowshot.add(input.nextInt());
        Direction.add(RandomDirection());
     }
    for(int i=0; i<waves; i++){
        System.out.printf("\n%d wave arrow:%d",i+1,arrowshot.get(i));
        receivedArrow.add(boat.Attack(Direction.get(i),arrowshot.get(i)));
    }
        System.out.println("\nXBoat Direction :"+Direction.toString());
        System.out.println("Arrow Received :"+receivedArrow.toString());
   }
    public static String RandomDirection(){
        Random r = new Random();
        int random = r.nextInt(4);
        String Direction;
        if(random == 0){
            Direction="Front";
            return Direction;
        }
        else if(random == 1){
            Direction="Right";
            return Direction;
        }
        else if(random == 2){
            Direction="Left";
            return Direction;
        }
        else {
            Direction="Back";
            return Direction;
        }
    }
    public static int putStrawman(){
    int inputStrawman=0;
        while(true){
        System.out.print("Please enter number of strawman:");
        inputStrawman = input.nextInt();
        if(inputStrawman>100){
            System.out.println("Number of straw man couldnt exceed 100");
        }
        else if(inputStrawman<0){
            System.out.println("Please Enter a valid number");
        }
        else{
            break;
        }
    }
        return inputStrawman;
    }
}

