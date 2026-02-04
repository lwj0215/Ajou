public class CompoundInterest {
   // static final 변수들을 클래스 바로 아래에 선언
   static final double STARTRATE = 10;
   static final int NRATES = 6;
   static final int NYEARS = 10;

   public static void main(String[] args) {
       double[] interestRate = new double[NRATES];
       double[][] balances = new double[NYEARS][NRATES];

       initialize(interestRate, balances); // 이중배열 초기값 설정
       computeInterest(interestRate, balances); // 이율 계산 후 잔고 배열에 저장
       printResult(interestRate, balances); // 이중배열 출력
   }
   
   public static void initialize(double[] interestRate, double[][] balances) {
        
      // set interest rates to 10 . . . 15%
      for(int i = 0; i <NRATES;i++){
         interestRate[i] = (STARTRATE+i)/100.0;
      }
      // set initial balances to 10000
      for(int i =0;i<balances[0].length;i++){
         balances[0][i] = 10000;
      }
   }
   
   public static void computeInterest(double[] interestRate, double[][] balances) {
         // compute interest for future years
         for(int i =1;i<balances.length;i++){
            for(int j = 0;j<balances[i].length;j++){
               // get last year's balances from previous row
               double oldBalance = balances[i-1][j];
               // compute interest
               double interest = oldBalance * interestRate[j];
               // compute this year's balances
               balances[i][j] = oldBalance + interest;
            }
         }      
   }
   
   public static void printResult(double[] interestRate, double[][] balances) {
      // print one row of interest rates
      for(double i : interestRate){
         System.out.print("     " + (int)(i*100) + "% ");
      }
      System.out.println();
      // print balance table
      for(int i = 0;i<balances.length;i++){
         // print table row
         for(int j = 0;j<balances[i].length;j++){
            System.out.printf("%.2f ",balances[i][j]);
         }
         System.out.println();
      }
   }
}