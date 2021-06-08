// CurrentAccount.java
// Represents a saving account

public class CurrentAccount extends Account
{
    private int accountNumber; 
    private int pin; 
    private double availableBalance; 
    private double totalBalance;
    private double limit = 10000; 
    
    public CurrentAccount( int theAccountNumber, int thePIN, 
       double theAvailableBalance,double theTotalBalance )
    {
       super( theAccountNumber, thePIN, theAvailableBalance, theTotalBalance );
    }

    public void setLimt( int amount )
    {
       limit = amount;
    }
    
    public double getLimit()
    {
       return limit;
    }
}

