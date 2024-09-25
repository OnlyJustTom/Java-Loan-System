public class Record {
    
    private String recordID; //The id of the loan
    private String custID; //The id of the customer
    private int type; //1 = Auto, 2 = Builder, 3 = Mortgage, 4 = Personal, 5 = Other 6+ = Invalid
    private float intrestRate; //A percentage
    private float ammountLeft; //In thousands of £s eg 360 = £360,000
    private float loanTerm; //Years of loan

    


    //Contructor - This also checks to see if values entered are valid
    public Record(String recID , String custID, int type, float intrestRate, float ammLeft, float loanTerm){
        
        String errors = validityChecker(recID, custID, type, intrestRate, ammLeft, loanTerm);

        if(errors != ""){
            System.out.println(errors);    
        }else{
            this.recordID = recID;
            this.custID = custID;
            this.type = type;
            this.intrestRate = intrestRate;
            this.ammountLeft = ammLeft;
            this.loanTerm = loanTerm;
        }
    }
    //Default Constructor if no values are entered
    public Record(){
            this.recordID = "000000";
            this.custID = "ABC123";
            this.type = 1;
            this.intrestRate = 10;
            this.ammountLeft = 100;
            this.loanTerm = 10;
    }
    //This function tests to see if the values entered into the constructor are valid - it will also return the errors the user made when creating the record
    private String validityChecker(String recID, String custID, int type, float intrestRate, float ammLeft, float loanTerm){
        
        String returnString = "";

        //Tests to see if recID is 6 characters long
        if(recID.length() != 6){
            returnString += "Record ID needs to be 6 numbers long\n";
        }
        //Tests to see if recID is only numbers
        for(int i = 0; i < recID.length(); i++){
            if(!Character.isDigit(recID.charAt(i))){
                returnString += "Record ID needs to be all numbers\n";
            }
        }
        //Tests to see if custID is 6 characters long
        if(custID.length() != 6){
            returnString += "Customer ID needs to be 6 character long, 3 numbers and 3 letters\n";
        }
        //Tests to see if the first 3 characters of custId are A-Z
        for(int i = 0; i < custID.length()-3; i++){
            if(!Character.isLetter(custID.charAt(i))){
                returnString += "Customer ID needs to be 3 letters then 3 numbers\n";
            }
        }
        //Tests to see if the last 3 characters of custId are numbers
        for(int i = 3; i < custID.length(); i++){
            if(!Character.isDigit(custID.charAt(i))){
                returnString += "Customer ID needs to be 3 letters then 3 numbers\n";
            }
        }
        //Tests if type is between 1 and 5
        if(type < 1 || type > 5){
            returnString += "Type needs to be between 1 and 5\n";
        }
        //Tests if the intrest rate is above 0 (0%)
        if(intrestRate < 0){
            returnString += "Interest rate needs to be above 0\n";
        }
        //Tests if ammountLeft is above 0
        if(ammLeft < 0){
            returnString += "Ammount left needs to be above 0\n";
        }
        //Tests if loanTerm is above or equal to 0
        if(loanTerm <= 0){
            returnString += "Loan term needs to be above 0\n";
        }
        //Return the errors the user made, if no errors where made then an empty string is returned
        return returnString;
    }

    //Getters and setters
    public String getRecID(){
        return this.recordID;
    }

    public String getCustID(){
        return this.custID;
    }

    public int getType(){
        return this.type;
    }

    public float getIntrestRate(){
        return this.intrestRate;
    }
    
    public float getAmmLeft(){
        return this.ammountLeft;
    }

    public float getLoanTerm(){
        return this.loanTerm;
    }

    public void setRecID(String recID) {
        this.recordID = recID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setInterestRate(float interestRate) {
        this.intrestRate = interestRate;
    }

    public void setAmmLeft(float ammLeft) {
        this.ammountLeft = ammLeft;
    }

    public void setLoanTerm(float loanTerm) {
        this.loanTerm = loanTerm;
    }

    public String printRecord(){
        return this.recordID + "      " + this.custID + "        " + this.type +"           " +  this.intrestRate +"              "+ this.ammountLeft +"            "+ this.loanTerm;
    }

}



//System.out.println("123456      ABC123        5           50              100            10)