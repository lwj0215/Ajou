//기본적으로 모든 .java 파일 한 번에 javac하기는 알려줘야겠군...
public class Car
    {
       private String no;
       private int speed;
       private double position;
       
       // 1-(가)
       public Car(String no, int speed, double position) {
            this.no = no;
            this.speed = speed;
            this.position = position;
       }

        public void move(int min){
            // 1-(나), assume min >= 0
            this.position += (int)(this.speed * min/60);
        }

        @Override
        public String toString() {
            // 1-(다), 반환 형식은 No=c1111,Speed=80km/h,Position=120km
            return "No="+this.no+",Speed="+this.speed+"km/h,Position="+(int)this.position+"km";
        }

        // 1-(라)
        public int compareTo(Car c){
            return this.position>c.position ? 1 : (this.position<c.position? -1 : 0);
        }
}
