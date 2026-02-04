public class PayableTest 
{
   public static void main(String[] args)
   {
      Payable[] payableObjects = new Payable[5];
      
      payableObjects[0] = new Invoice("01234", "seat", 2, 375.0);
      payableObjects[1] = new Invoice("56789", "tire", 4, 79.95);
      payableObjects[2] = 
         new SalariedEmployee("John", "Smith", "111-11-1111", 800.0);
      payableObjects[3] = 
         new SalariedEmployee("Lisa", "Barnes", "888-88-8888", 1200.0);
      payableObjects[4] =new Loan("청년도약", 1000.0); //청년도약은 대출명이며,
                                                                  // 1000.0은 대출원금을 나타낸다.
      Loan loan = (Loan)payableObjects[4];
      loan.setInterest(100.0); // 이자율 100.0을 설정

      System.out.println(
         "Invoices and Employees processed polymorphically:"); 

      // generically process each element in array payableObjects
      for (Payable currentPayable : payableObjects)
      {
         // output currentPayable and its appropriate payment amount
         System.out.printf("%n%s %n%s: $%,.2f%n", 
            currentPayable.toString(), // could invoke implicitly
            "payment due", currentPayable.getPaymentAmount()); 
      } 
   } // end main
} // end class PayableTest

