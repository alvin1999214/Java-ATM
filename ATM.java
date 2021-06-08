// ATM.java
// Represents an automated teller machine
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicButtonUI;
import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.SwingWorker;

public class ATM extends JFrame
{
   private JPanel contentPane;
   private JTextPane maintext;
   private String pinEntry = "", amount = "",accNo = "";
   private int accNO;
   private int pinNO;
   private JPanel panel;
   private JTextArea textArea;
   private JPasswordField passwordField;
   private JTextField textField;
   private boolean accEntered = false;
   private boolean pinEntered = false;
   private boolean bothEntered = false;
   
   private JLabel lblCancel;
   private JLabel lblOk;
   private JLabel lbl200;
   private JLabel lbl500;
   private JLabel lbl1500;
   private JLabel lbl100;
   private JLabel lbl300;
   private JLabel lbl1000;
   private JPanel btnPanel;
   private JButton btn1;
   private JButton btn2;
   private JButton btn3;
   private JButton btn4;
   private JButton btn5;
   private JButton btn6;
   private JButton btn7;
   private JButton btn8;
   private JButton btn9;
   private JButton btnDot;
   private JButton btn0;
   private JButton btnC;
   private JButton btn100;
   private JButton btn200;
   private JButton btn300;
   private JButton btn500;
   private JButton btn1000;
   private JButton btn1500;
   private JButton btnCancel;
   private JButton btnOk;
   
    
   private boolean userAuthenticated = false; // whether user is authenticated
   private int currentAccountNumber; // current user's account number
   private Screen screen; // ATM's screen
   private Keypad keypad; // ATM's keypad
   private CashDispenser cashDispenser; // ATM's cash dispenser
   private BankDatabase bankDatabase; // account information database

   // constants corresponding to main menu options
   private static final int BALANCE_INQUIRY = 1;
   private static final int WITHDRAWAL = 2;
   private static final int TRANSFER = 3;
   private static final int EXIT = 4;

   // no-argument ATM constructor initializes instance variables
   public ATM() 
   {
      super("Assignment 3");
   
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);
      
      
      panel = new JPanel();
      panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
      panel.setBackground(Color.CYAN);
      panel.setBounds(185, 11, 300, 175);
      contentPane.add(panel);
      panel.setLayout(null);
        
      //textArea =new JTextArea(5,20);
      //textArea.setEditable(false);
      //textArea.setText("Welcome to the ATM, please press the OK button.");
      //textArea.setBounds(63, 52, 166, 64);
      //panel.add(textArea);
      
      maintext = new JTextPane();
      maintext.setVisible(true);
      maintext.setFont(new Font("Arial", Font.BOLD, 14));
      maintext.setBackground(Color.CYAN);
      maintext.setEditable(false);
      maintext.setText("Welcome to the ATM, please press the OK button.");
      maintext.setBounds(63, 22, 166, 64);
      panel.add(maintext);
      
      textField = new JTextField();
      textField.setVisible(false);
      textField.setEditable(false);
      textField.setBounds(63, 92, 120, 20);
      textField.setText("");
      panel.add(textField);
      
      passwordField = new JPasswordField();
      passwordField.setVisible(false);
      passwordField.setEditable(false);
      passwordField.setBounds(63, 112, 120, 20);
      passwordField.setText("");
      panel.add(passwordField);
      
      lblCancel = new JLabel("Cancel");
      lblCancel.setVisible(false);
      lblCancel.setFont(new Font("Tahoma", Font.BOLD, 11));
      lblCancel.setBounds(10, 138, 46, 14);
      panel.add(lblCancel);
      
      lblOk = new JLabel("Ok");
      lblOk.setVisible(true);
      lblOk.setFont(new Font("Tahoma", Font.BOLD, 11));
      lblOk.setHorizontalAlignment(SwingConstants.RIGHT);
      lblOk.setBounds(244, 138, 46, 14);
      panel.add(lblOk);
      
      lbl200 = new JLabel("200");
      lbl200.setVisible(false);
      lbl200.setHorizontalAlignment(SwingConstants.RIGHT);
      lbl200.setBounds(244, 24, 46, 14);
      panel.add(lbl200);
      
      lbl500 = new JLabel("500");
      lbl500.setVisible(false);
      lbl500.setHorizontalAlignment(SwingConstants.RIGHT);
      lbl500.setBounds(244, 64, 46, 14);
      panel.add(lbl500);
      
      lbl1500 = new JLabel("1500");
      lbl1500.setVisible(false);
      lbl1500.setHorizontalAlignment(SwingConstants.RIGHT);
      lbl1500.setBounds(244, 102, 46, 14);
      panel.add(lbl1500);
      
      lbl100 = new JLabel("100");
      lbl100.setVisible(false);
      lbl100.setHorizontalAlignment(SwingConstants.LEFT);
      lbl100.setBounds(10, 24, 46, 14);
      panel.add(lbl100);
      
      lbl300 = new JLabel("300");
      lbl300.setVisible(false);
      lbl300.setHorizontalAlignment(SwingConstants.LEFT);
      lbl300.setBounds(10, 64, 46, 14);
      panel.add(lbl300);
      
      lbl1000 = new JLabel("1000");
      lbl1000.setVisible(false);
      lbl1000.setHorizontalAlignment(SwingConstants.LEFT);
      lbl1000.setBounds(10, 102, 46, 14);
      panel.add(lbl1000);
      
      btnPanel = new JPanel();
      btnPanel.setBounds(203, 212, 198, 123);
      contentPane.add(btnPanel);
      btnPanel.setLayout(new GridLayout(4, 3, 0, 0));
      
      btn1 = new JButton("1");
      
      btnPanel.add(btn1);
      
      btn2 = new JButton("2");
      
      btnPanel.add(btn2);
      
      btn3 = new JButton("3");
      btnPanel.add(btn3);
      
      btn4 = new JButton("4");
      btnPanel.add(btn4);
      
      btn5 = new JButton("5");
      btnPanel.add(btn5);
      
      btn6 = new JButton("6");
      btnPanel.add(btn6);
      
      btn7 = new JButton("7");
      btnPanel.add(btn7);
      
      btn8 = new JButton("8");
      btnPanel.add(btn8);
      
      btn9 = new JButton("9");
      btnPanel.add(btn9);
      
      btnDot = new JButton(".");
      btnPanel.add(btnDot);
      
      btn0 = new JButton("0");
      btnPanel.add(btn0);
      
      btnC = new JButton("C");
      //btnC.setUI((ButtonUI) BasicButtonUI.createUI(btnC));
      btnC.setBackground(Color.RED);
      btnPanel.add(btnC);
      
      btn100 = new JButton("");
      btn100.setBounds(128, 24, 47, 28);
      contentPane.add(btn100);
      
      btn200 = new JButton("");
      btn200.setBounds(128, 63, 47, 28);
      contentPane.add(btn200);
      
      btn300 = new JButton("");
      btn300.setBounds(128, 103, 47, 28);
      contentPane.add(btn300);
      
      btnCancel = new JButton("");
      btnCancel.setBounds(128, 142, 47, 28);
      contentPane.add(btnCancel);
      
      btn500 = new JButton("");
      btn500.setBounds(495, 24, 47, 28);
      contentPane.add(btn500);
      
      btn1000 = new JButton("");
      btn1000.setBounds(495, 63, 47, 28);
      contentPane.add(btn1000);
        
      btn1500 = new JButton("");
      btn1500.setBounds(495, 103, 47, 28);
      contentPane.add(btn1500);
      
      btnOk = new JButton("");
      btnOk.setBounds(495, 142, 47, 28);
      
      contentPane.add(btnOk);
      
      //ButtonHandler okhandler = new ButtonHandler();
      //btnOk.addActionListener(okhandler);
      
      ButtonHandler handler = new ButtonHandler();
      btn1.addActionListener(handler);
      btn2.addActionListener(handler);
      btn3.addActionListener(handler);
      btn4.addActionListener(handler);
      btn5.addActionListener(handler);
      btn6.addActionListener(handler);
      btn7.addActionListener(handler);
      btn8.addActionListener(handler);
      btn9.addActionListener(handler);
      btn0.addActionListener(handler);
      btnDot.addActionListener(handler);
      btnC.addActionListener(handler);
        
      userAuthenticated = false; // user is not authenticated to start
      currentAccountNumber = 0; // no current account number to start
      screen = new Screen(); // create screen
      keypad = new Keypad(); // create keypad 
      cashDispenser = new CashDispenser(); // create cash dispenser
      bankDatabase = new BankDatabase(); // create acct info database
   
      btnOk.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
           btnOkActionPerformed(e);
            }
            });
    } // end no-argument ATM constructor
    
    private class ButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();            
            if (bothEntered)    
              amount += command;                
                if (!pinEntered){
                //accNo += e.getActionCommand();
                if (command == "C")
                textField.setText("");
                else
                textField.setText(textField.getText()+command);              
            }
                else {
                    //pinEntry += e.getActionCommand();
                    if (command == "C")
                    passwordField.setText("");
                    else
                    passwordField.setText(passwordField.getText()+command);
            }
            accNO = stringtoint(textField.getText());
            pinNO = stringtoint(String.valueOf(passwordField.getPassword()));
            }        
        }
  
    
        
   private void btnOkActionPerformed(ActionEvent e){
     if (!accEntered){
       maintext.setText("\nPlease enter your account number: ");
       textField.setVisible(true);
       accEntered = true;
    }
       else if (accEntered && !pinEntered) {          
           maintext.setText("\nEnter your PIN: " );
           passwordField.setVisible(true);
           pinEntered = true;
        }
        else if (accEntered && pinEntered){
        bothEntered = true;       
    }
     if (bothEntered){
     userAuthenticated = 
        bankDatabase.authenticateUser( accNO, pinNO );
        System.out.println(accNO + " "+ pinNO);
    } 
       if ( userAuthenticated && bothEntered)
      {
         //currentAccountNumber = accountNumber; // save user's account #
         currentAccountNumber = accNO;
         mainMenu();
        } // end if
      else if( !userAuthenticated && bothEntered){       
           maintext.setText("Invalid account number or PIN. Please try again.");
           textField.setText("");
           passwordField.setText("");
           accEntered = false;
           pinEntered = false;
           bothEntered = false;
            } 
            
    }
   
   public int stringtoint(String s){
    return Integer.parseInt(s);
    }
   
   public void mainMenu(){
    lbl100.setText("View my balance");
    lbl200.setText("Withdraw cash");
    lbl300.setText("Transfer funds");
    lbl500.setText("Exit");
    lbl100.setVisible(true);
    lbl200.setVisible(true);
    lbl300.setVisible(true);
    lbl500.setVisible(true);
    } 
    
   
   // start ATM 
   public void run()
   {
      // welcome and authenticate user; perform transactions     
      //while ( this.isVisible())
      //{
         // loop while user is not yet authenticated                      
         btnOk.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
           btnOkActionPerformed(e);
            }
            });
         //while ( !userAuthenticated ) 
         //{
         //   screen.displayMessageLine( "\nWelcome!" );                   
          //  authenticateUser(); // authenticate user                    
         //} // end while
      

         
         //performTransactions(); // user is now authenticated 
         //userAuthenticated = false; // reset before next ATM session
         //currentAccountNumber = 0; // reset before next ATM session 
         //screen.displayMessageLine( "\nThank you! Goodbye!" );
      // } // end while 
      // }
     
      
   } // end method run

   // attempts to authenticate user against database
   private void authenticateUser() 
   {
      
      int accountNumber2 = 0;
      int pin2 = 0;
      screen.displayMessage( "\nPlease enter your account number: " );
      
      accountNumber2 = accNO;
      
      //int accountNumber = keypad.getInput(); // input account number
      screen.displayMessage( "\nEnter your PIN: " ); // prompt for PIN
      
      //int pin = keypad.getInput(); // input PIN
      pin2 = pinNO;

    
      // set userAuthenticated to boolean value returned by database
        
      //userAuthenticated = 
        // bankDatabase.authenticateUser( accountNumber, pin );
        
      //userAuthenticated = 
         //bankDatabase.authenticateUser( accountNumber2, pin2 );   
      
      // check whether authentication succeeded
      if ( userAuthenticated )
      {
         //currentAccountNumber = accountNumber; // save user's account #
         currentAccountNumber = accountNumber2;
        } // end if
      else {
         screen.displayMessageLine( 
             "Invalid account number or PIN. Please try again." );
           maintext.setText("Invalid account number or PIN. Please try again.");
           textField.setText("");
           passwordField.setText("");
            } 
        
   } // end method authenticateUser

   // display the main menu and perform transactions
   private void performTransactions() 
   {
      // local variable to store transaction currently being processed
      Transaction currentTransaction = null;
      
      boolean userExited = false; // user has not chosen to exit

      // loop while user has not chosen option to exit system
      while ( !userExited )
      {     
         // show main menu and get user selection
         int mainMenuSelection = displayMainMenu();

         // decide how to proceed based on user's menu selection
         switch ( mainMenuSelection )
         {
            // user chose to perform one of three transaction types
            case BALANCE_INQUIRY: 
            case WITHDRAWAL: 
            case TRANSFER:

               // initialize as new object of chosen type
               currentTransaction = 
                  createTransaction( mainMenuSelection );

               currentTransaction.execute(); // execute transaction
               break; 
            case EXIT: // user chose to terminate session
               screen.displayMessageLine( "\nExiting the system..." );
               userExited = true; // this ATM session should end
               break;
            default: // user did not enter an integer from 1-4
               screen.displayMessageLine( 
                  "\nYou did not enter a valid selection. Try again." );
               break;
         } // end switch
      } // end while
   } // end method performTransactions
   
   // display the main menu and return an input selection
   private int displayMainMenu()
   {
      screen.displayMessageLine( "\nMain menu:" );
      screen.displayMessageLine( "1 - View my balance" );
      screen.displayMessageLine( "2 - Withdraw cash" );
      screen.displayMessageLine( "3 - Transfer funds" );
      screen.displayMessageLine( "4 - Exit\n" );
      screen.displayMessage( "Enter a choice: " );
      return keypad.getInput(); // return user's selection
   } // end method displayMainMenu
         
   // return object of specified Transaction subclass
   private Transaction createTransaction( int type )
   {
      Transaction temp = null; // temporary Transaction variable
      
      // determine which type of Transaction to create     
      switch ( type )
      {
         case BALANCE_INQUIRY: // create new BalanceInquiry transaction
            temp = new BalanceInquiry( 
               currentAccountNumber, screen, bankDatabase );
            break;
         case WITHDRAWAL: // create new Withdrawal transaction
            temp = new Withdrawal( currentAccountNumber, screen, 
               bankDatabase, keypad, cashDispenser );
            break; 
         case TRANSFER: // create new Deposit transaction
            temp = new Transfer( currentAccountNumber, screen, 
               bankDatabase, keypad );
            break;
      } // end switch

      return temp; // return the newly created object
   } // end method createTransaction
} // end class ATM



/**************************************************************************
 * (C) Copyright 1992-2007 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/