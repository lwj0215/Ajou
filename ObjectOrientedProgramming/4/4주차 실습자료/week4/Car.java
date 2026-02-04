//기본적으로 모든 .java 파일 한 번에 javac하기는 알려줘야겠군...

public class Car
    {
       private String no;
       private int speed;
       private double position;
       
       public Car(String no, int speed, double position) {
            // 1-(가)
       }

        public void move(int min){
            // 1-(나), assume min >= 0
        }

        @Override
        public String toString() {
            // 1-(다), 반환 형식은 No=c1111,Speed=80km/h,Position=120km
        }

        public int compareTo(Car c){
            // 1-(라)
        }
}
