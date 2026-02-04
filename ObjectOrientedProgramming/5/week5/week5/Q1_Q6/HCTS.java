// HCTS.java
import java.util.Scanner;


public class HCTS
{
      static Car[] carList;
      static Time currentTime;
      static Scanner in;
  
      public static void main(String[] args)
      {
            initialize();
            
            do {
                char cmd = readCommand();
                if(cmd=='t')
                    handleSettingTime();
                else if(cmd=='l')
                    handleLocatingCars();
                else if(cmd=='q'){
                 System.out.printf("Good Bye!%n");
                 System.exit(-1);
                }
                else 
                    System.out.printf("command error: %s%n", cmd);
             }while(true);
      } // end of main()
    
      // 자동차 정보와 현재 시간을 초기화한다.
      public static void initialize()
      {
                  //해당부분작성

           in = new Scanner(System.in);
      }

      // 명령어를 읽어들인다
      public static char readCommand()
      {
            System.out.printf("Enter a command: ");
            String s = in.next();
            return s.charAt(0);  // 읽어들인 스트링의 첫번째 문자를 명령어로 인식
      }

      // 현재 시간 설정 명령('t') 처리
      public static void handleSettingTime()
      {
            
                  //해당부분작성
            setCurrentTime(t);
            moveAllCars(min);

      }      

      // 차량 정보 출력 명령('s')  처리
      public static void handleLocatingCars()
      {
            //해당부분작성
      }      

      // 새로운 current_time을 설정한다
      public static void setCurrentTime(Time t)
      {
           currentTime = t;
      }

      // 주어진 시간(min) 만큼 이동한 자동차 위치를 조정한다
      public static void moveAllCars(int min)
      {
            //해당부분작성
      }

      // 차량 정보(자동차 번호, 및 현재 위치)를 출력한다
      public static void printCarInfo(Car c)
      {
            System.out.println(c.toString());
      }
} // end of CarManager class