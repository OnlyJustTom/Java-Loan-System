import java.util.InputMismatchException;
import java.util.Scanner;


public class XYZBank {
    public static void main(String[] args){
        //Setup input
        Scanner input = new Scanner(System.in);
        //Sets the initial value of max number of records until it is assigned by the user
        int maxNumOfRecords = -1;
        //Gets the maximun number of records
        //If the users doesnt enter an integer into the getMaxRecords function then it returns -1 so while the user doesnt enter a integer keep asking them for a integer
        while(maxNumOfRecords == -1){
            maxNumOfRecords = 1;
            maxNumOfRecords = getMaxRecords(input);
        }
        //Initilises the array of records and sets its max value
        Record[] Records = new Record[maxNumOfRecords];        
        //This will be set to false if the user selects exit in the main menu
        boolean done = false;
        while(done == false){
        //Displays the main menu
        int choice = mainMenu(input);
            switch(choice){
                case 1:
                    //Can the user add a new record or is the max records reached
                    boolean canAddNewRecord = !(Records.length > maxNumOfRecords);
                    if(canAddNewRecord){
                        //To add a new array call the addNewRecord function and add the returned function to to array of Records
                        for(int i = 0; i < maxNumOfRecords; i++){
                            Records[i] = addNewRecord(input);
                        }
                    }else{
                        //If the max number of records has been reached then print this
                        System.out.println("The max number records has been reached, cannot add another.\n");
                    }
                    break;
                case 2:
                    //Checks to see if the record array is empty
                    boolean isStartingRecord = (Records[0] == null);
                    if(isStartingRecord){
                        System.out.println("There are no records in the database so it cannot be shown\n");
                    }else{
                        printRecords(maxNumOfRecords, Records,input);
                    }
                    break;
                case 3:
                    changeRecord(input, Records, maxNumOfRecords);
                    break;
                case 4:
                    done = true;
                    break;
            }
        }
        //Closes the scanner to prevent a memory leak
        input.close();
    }
    
    private static int mainMenu(Scanner input){
        //"Main menu screen" with 3 options 1) adds a new record, 2) prints the "database" 3) Quits the program
        System.out.println("###############################");
        System.out.println("Welcome to XYZBanks Loan system");
        System.out.println("####Please choose an option####");
        System.out.println("##### 1) Creates a record #####");
        System.out.println("# 2) Print the current record #");
        System.out.println("#######3)Change a record ######");
        System.out.println("########### 4) Quit ###########");
        System.out.println("###############################");

        int choice = input.nextInt();
        //Checks if the entered input is valid
        if(choice > 5 || choice < 0){
            System.out.println("Invalid choice\n");
            mainMenu(input);
        }
        return choice;
    }
    
    //This funcion asks the user for all required detail and the syntax for that detail eg for custID print smth like "Must be entered as AAAXXX with A being any letter and X being any number"
    private static Record addNewRecord(Scanner input){

        //Askes the user for the record ID and assigns it to a variable
        System.out.println("Please enter the Record ID in the form of XXXXXX with X being any number: ");
        
        String recordID = input.next();
    
        //Asks for the Customer ID
        System.out.println("Please enter the Customer ID in the form of AAAXXX with A being any letter and X being any number: ");
        
        String customerID = input.next();

        //Asks for the type
        System.out.println("Please enter the type of loan 1-5:\n1 = Auto loan,\n2 = Builder loan,\n3 = Mortgage loan,\n4 = Personal loan,\n5 = Other type of loan");
        int type = input.nextInt();

        //Asks for the intrest rate of the loan
        System.out.println("Please enter the intrest rate of the loan as a percentage, however please dont include the percentage sign: ");
        float intrestRate = input.nextFloat();

        //Asks for the ammount left
        System.out.println("Please enter the ammount left on the loan in thousands. For example 360 = £360,000");
        float ammountLeft = input.nextFloat();

        //Ask for the loan term
        System.out.println("Please enter the loan term in years: ");
        float loanTerm = input.nextFloat();
        
        //Creates the new record object and returns it
        return new Record(recordID, customerID, type, intrestRate, ammountLeft, loanTerm);
    }

    private static void printRecords(int maxNumOfRecords, Record[] records, Scanner input){
        //Prints the max number of records and current number of records
        System.out.println("Maximum number of records: " + maxNumOfRecords + "\n");
        System.out.println("Current number of records: " + records.length + "\n");
        //Prints the table headers
        System.out.println("Record ID   Customer ID   Loan Type   Interest Rate   Amount Left   Loan Term");
        //For each record print it
        for(Record record : records){
            //If the user makes an error when creating a record it will be skipped
            if(record.getRecID() == null){
                continue;
            }
            System.out.println(record.printRecord());  
        }
    }

    private static int getMaxRecords(Scanner input){
        try{
            //Get the maximun number of records
            System.out.println("Enter the maximum number of records:");
            int maxRecords = input.nextInt();
            return maxRecords;
        }
        catch(InputMismatchException e){
            //If the user doesnt enter an integer then this will run
            System.out.println("Please enter a number");
            input.nextLine();
            return -1;
        }
    }

    private static void changeRecord(Scanner input, Record[] record, int maxNum){
        //Shows the current records
        printRecords(maxNum, record, input);
        System.out.println("\n\n");
        //Gets the record to change and the aspect of it to change
        int recordToChange = getRecordToChange(input);
        int indexToChange = getIndexToChange(input);
        //Changes the correct aspect of the record
        switch(indexToChange){
            case 1:
                System.out.println("Enter a new Record ID");
                String recordID = input.nextLine();
                record[recordToChange].setRecID(recordID);
                break;
            case 2:
                System.out.println("Enter a new Customer ID");
                String customerID = input.nextLine();
                record[recordToChange].setCustID(customerID);
                break;
            case 3:
                System.out.println("Enter a new Type");
                int type = input.nextInt();
                record[recordToChange].setType(type);
                break;
            case 4:
                System.out.println("Enter a new interest rate");
                float interestRate = input.nextFloat();
                record[recordToChange].setInterestRate(interestRate);
                break;
            case 5:
                System.out.println("Enter a new ammount left");
                float ammountLeft = input.nextFloat();
                record[recordToChange].setAmmLeft(ammountLeft);
                break;
            case 6:
                System.out.println("Enter a new loan term");
                float loanTerm = input.nextFloat();
                record[recordToChange].setLoanTerm(loanTerm);
            
        }
    }
    private static int getIndexToChange(Scanner input){
        
        while (true) {
            try {
                System.out.println("Enter the index to change:\n1 = Record ID\n2 = Customer ID\n3 = Type\n4 = Interest ammount\n5 = Ammount left\n6 = Loan term");
                int whatToChange = input.nextInt();
                assert (whatToChange > 6 || whatToChange < 0);
                return whatToChange;//Return if integer is entered
            } catch (java.util.InputMismatchException e) {
                // If user input is not an integer, display an error message
                System.out.println("Invalid input. Please enter an integer.");
                input.nextLine(); // Clean scanner buffer to prevent further errors
            } catch(AssertionError e){
                System.out.println("Enter a number between 1 and 6");
                input.nextLine(); // Clean scanner buffer to prevent further errors
            }
        }
                
    }

    private static int getRecordToChange(Scanner input){
        while(true){
            try {
                System.out.print("Enter the index of the record to change");
                int recordToChange = input.nextInt();
                return recordToChange;
            } catch (java.util.InputMismatchException e) {
                // If user input is not an integer, display an error message
                System.out.println("Invalid input. Please enter an integer.");
                input.nextLine(); // Clean scanner buffer to prevent further errors
            }
        }
    }
}
