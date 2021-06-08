// SavingAccount.java
// Represents a saving account

public class SavingAccount extends Account 
{
   private int accountNumber; 
   private int pin; 
   private double availableBalance; 
   private double totalBalance;
   private double interestRate = 0.001; 
    
   public SavingAccount( int theAccountNumber, int thePIN, 
      double theAvailableBalance,double theTotalBalance )
   {
      super ( theAccountNumber, thePIN, theAvailableBalance, theTotalBalance );
   }
  
   public void setInterest( int amount )
   {
      interestRate = amount;
   }
   
   public double getInterest()
   {
      return interestRate;
   }
}
