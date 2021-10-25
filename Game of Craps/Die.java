package project2_tp;

import java.util.Random;

public class Die {

    private int sides;
    private int face;
    private int face1;
    private int sum;
    
    public Die() 
    {
       sides = 6;       
    }
    
    public Die(int a)
    {
        sides = a;
    }
  
    public int getSides()
    { 
      return sides;
    }   
  
    public int getValue()
    {
      Random rand = new Random(); 
      
      face = rand.nextInt(sides) + 1;
      face1 = face;
      return face;
    }
    
    public int getSum()
    {
        return sum;
    }
    
}    
    
