/*
Tarpan Patel 
CIS 141
10/30/16
*/
package project2_tp;

class DicePlayer {
    
    private String namePlayer;
    private int amount;
    private double add;
    private double sub;
    
    public DicePlayer()
    {
        namePlayer = "no Name";
        amount = 1;
    }

    public DicePlayer (String n, int c)
    {
       namePlayer = n;
       amount = c;      
    }

  /*  
    public void setName(String playerName) 
    {
       namePlayer = playerName;
    }
   
    public void setCash(double m)
    {
       amount = m;
    }    
   */
    public String getName()
    {
        return namePlayer;
    }
     
    public double getCash()
    {
        return amount;
    }        
     
    public boolean addCash(double a) 
    {
        boolean num1 = true;
        add = a;
        
        if(add <= 0)
        {
            num1 = false;
        }   
        else
        {
            num1 = true;
            amount = amount + add;
        }
        return num1;
    }
    
    public boolean subtractCash(double s)
    {
        boolean num2 = false;
        sub = s;
        
        if(sub > amount || sub < 0)
        {
            num2 = false;
        }
        else
        {
            num2 = true;
            amount = amount - sub;
        }
        return num2;
    }          
}
   

    
  
    
