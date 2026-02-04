public class Car
{
    private String no;
    private int speed;
    private double position;
    
    public Car(String no, int speed, double position){
     this.no =no;
     this.speed=speed;
     this.position = position;
    }
    
    public String getNo() {return no;}
    public int getSpeed() {return speed;}
    public double getPosition() {return position;}

    public void setNo(String no) {
        this.no = no;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void setPosition(double position) {
        this.position = position;
    }

    public void move(int min){

        this.position += (double) this.speed *min / 60.0;

    }

    @Override
    public String toString() {
    
        return "No=" + this.no + ", Speed=" + this.speed + "km/h, Position=" + this.position +"km";

    }

    public int compareTo(Car c){


        if (this.position > c.position){
    
            return 1;

        }
        else if (this.position < c.position){

            return -1;

        }

        else {

            return 0;

        }

    }


}
