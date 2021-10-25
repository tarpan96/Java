
package project2_tp;

import java.util.Scanner;

public class Project2_TP 
{

    
    public static void main(String[] args) 
    {
        char yes = 'y';
        char input;
        Scanner scnr = new Scanner(System.in);
        
        Craps game = new Craps(); 
        game.setup();
        
        input = scnr.next().charAt(0);
        do
        {
         //   game.play();
            
        }
        while (input == yes);
    }
    
}
