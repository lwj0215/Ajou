public class Loan implements Payable{
    
    private final String name;
    private double principal;
    private double interest;

    public Loan(String name, double principal){
        this.name = name;
        this.principal = principal;
    }

    public String getName() {return name;}
    public double getPrincipal() {return principal;}
    public void setPrincipal(double principal) {this.principal = principal;}
    public double getInterest() {return interest;}
    public void setInterest(double interest) {this.interest = interest;}

    @Override                                                           
    public double getPaymentAmount()                                    
    {                                                                   
        return principal + interest; 
    }

    @Override
    public String toString(){
        return "loan: " + this.name + "\nprincipal: " + this.principal + "\ninterest: " + this.interest;
    }
}
