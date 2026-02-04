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

        // set initial balances to 10000

   }
   
   public static void computeInterest(double[] interestRate, double[][] balances) {
         // compute interest for future years

            // get last year's balances from previous row

            // compute interest

            // compute this year's balances

         

   }
   
   public static void printResult(double[] interestRate, double[][] balances) {

        // print one row of interest rates

        // print balance table

         // print table row

   }
}