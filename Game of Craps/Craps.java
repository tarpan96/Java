
package project2_tp;

import java.util.Scanner;

public class Craps {
    Scanner scnr = new Scanner(System.in);
    private String name = "";
    private int cash;
    
    public void setup()
    {
        
        
       
       System.out.println("Welcome to Street Dice. What is your name?");
       name = scnr.next(); // ask user for name

       System.out.println("How much money are you starting with?");
       cash = scnr.nextInt();
        
        while(cash < 0) //Checks for negative cash 
           {
            System.out.println("How much money are you starting with?");
            cash = scnr.nextInt();
           }
  
        DicePlayer player = new DicePlayer(name,cash); // Call class DicePlayer 

        System.out.printf("%s, $%.2f \n", player.getName(),player.getCash());
         
    }
}
