// HCTS.java
import java.util.*;

public class HCTS
{
      static Car[] car_list;
      static Time current_time;
      static Scanner in;
  
      public static void main(String[] args)
      {
            initialize();
            
            do {
               char cmd = read_command();
               if(cmd=='t')
                   handle_setting_time();
               else if(cmd=='l')
                   handle_locating_cars();
               else 
                   System.out.printf("command error: %s%n", cmd);
            } while(true);
      } // end of main()
    
      // 자동차 정보와 현재 시간을 초기화한다.
      public static void initialize()
      {
           car_list = new Car[3];

           for(int i=0; i<car_list.length; i++)
              car_list[i] = new Car();

           car_list[0].no = 1111;
           car_list[0].speed = 80;
           car_list[0].position = 0;

           car_list[1].no = 2222;
           car_list[1].speed = 100;
           car_list[1].position = 0;

           car_list[2].no = 3333;
           car_list[2].speed = 120;
           car_list[2].position = 0;
         
           current_time = new Time();
           current_time.hour = 0;
           current_time.minute = 0;

           in = new Scanner(System.in);
      }

      // 명령어를 읽어들인다
      public static char read_command()
      {
            System.out.printf("Enter a command: ");
            String s = in.next();
            return s.charAt(0);  // 읽어들인 스트링의 첫번째 문자를 명령어로 인식
      }

      // 현재 시간 설정 명령('t') 처리
      public static void handle_setting_time()
      {
            Time t = new Time();
            t.hour = in.nextInt();  
            t.minute = in.nextInt();
 
            int min = calculate_time_difference(t);
            set_current_time(t);
            move_all_cars(min);

      }      

      // 차량 정보 출력 명령('s')  처리
      public static void handle_locating_cars()
      {
           for(int i=0; i<car_list.length; i++)
               print_car_info(car_list[i]);
      }      

      // 새로운 current_time을 설정한다
      public static void set_current_time(Time t)
      {
           current_time = t;
      }
     
      // 시간의 차이(분단위) t - current_time를 계산한다
      public static int calculate_time_difference(Time t)
      {
           return  (t.hour-current_time.hour)*60 + (t.minute-current_time.minute);
      }

      // 주어진 시간(min) 만큼 이동한 자동차 위치를 조정한다
      public static void move_all_cars(int min)
      {
           for(int i=0; i<car_list.length; i++)
           {
                car_list[i].position += (double) car_list[i].speed/60*min;
                if(car_list[i].position > 500.0)
                    car_list[i].position = 500.0;	// 목적지에 도착
           }
      }

      // 차량 정보(자동차 번호, 및 현재 위치)를 출력한다
      public static void print_car_info(Car c)
      {
            System.out.printf("car no. = %d, car position = %f%n", c.no, c.position);
      }
} // end of CarManager class

class Time
{
    int hour;
    int minute;
}

class Car
{
    int no;
    int speed;
    double position;
}