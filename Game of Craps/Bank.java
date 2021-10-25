/*
Tarpan Patel
10/30/2016
*/
package project2_tp;

public class Bank {
    
    private double prevTotal;
    private double pay = 0.0;
   private double bet;
 
    
    public double getTotal() //returns the total won
    {         
        return pay;
    }        
    
    public boolean placeBet(double b)
    {
        
       boolean neg = true;
       
       if(b <= 0) // checks for positive numbers
       {    
           neg = false;
       }   
       
       else
       {
           neg = true;            
           bet = b * 2;                  
           pay += bet; 
           prevTotal = pay;
       }     
        return neg;  
    }
    
    public double payout() 
    {  
        pay = 0;
       return prevTotal;
    }
}        
    
     
