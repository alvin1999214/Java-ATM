// Transfer.java
// Represents  transfer ATM transaction

public class Transfer extends Transaction 
{   
    private double amount; //amount to transfer
    private int targetAccountNumber;
    private Keypad keypad;
    private final static int CANCEL = 0;
    private boolean checkAccountExist = false;
    private int targetAccountNumberInput;
    
    public Transfer( int userAccountNumber, Screen atmScreen, 
                BankDatabase atmBankDatabase, Keypad atmKeypad )
    {
        super( userAccountNumber, atmScreen, atmBankDatabase );
        keypad = atmKeypad;
    }
    
    public void execute()
    {
        double availableBalance;
        boolean transfered = false;
        
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();
        
           targetAccountNumber = returnTargetAccount();
           if(targetAccountNumber==CANCEL)
            {
                screen.displayMessageLine( "\nCanceling transaction..." );
                return;
            }
            do{
            amount = promptForTransferAmount();
            if(amount == CANCEL)
             {
                screen.displayMessageLine( "\nCanceling transaction..." );
                return;
            }
            
            
                availableBalance = bankDatabase.getAvailableBalance( getAccountNumber() );
                if( amount <= availableBalance )
                {
                    bankDatabase.debit( getAccountNumber(), amount );
                    bankDatabase.credit( targetAccountNumber, amount );
                    screen.displayMessageLine( "\nYou have already transfer HKD$" + amount +
                                   " to the account " + targetAccountNumber );
                    transfered = true;
                }
                else
                {
                    screen.displayMessageLine( "\nInsufficient funds in your account." +
                                   "\n\nPlease enter a smaller amount." );
                }
            }while (!transfered);
  
        
    }
    
    private void promptForTargetAccount()
    {
        Screen screen = getScreen();
        BankDatabase bankDatabase = getBankDatabase();
        
        do
        {
            screen.displayMessage("\nPlease enter the target account number (or 0 to cancel): ");
            targetAccountNumberInput = keypad.getInput();
            checkAccountExist = bankDatabase.accountExist( targetAccountNumberInput );
            
            if ( targetAccountNumberInput == CANCEL )
                return;
                
            if ( checkAccountExist == false )
                screen.displayMessage("\nAccount does not exist. Please try again.\n");
        } while ( checkAccountExist == false );
    }
    
    private int returnTargetAccount()
    {
        promptForTargetAccount();
        
        if ( targetAccountNumberInput == CANCEL )
                return CANCEL;
            else
                return targetAccountNumberInput;
    }
    
    private double promptForTransferAmount()
    {
    Screen screen = getScreen();
        
    screen.displayMessage("\nPlease enter the transfer amount (or 0 to cancel): ");
    double amount = keypad.getNonIntegerInput();
        
    if ( amount == CANCEL )
            return CANCEL;
    else
            return amount;
    }
    
    
    
    
    
    
}