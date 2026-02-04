import java.util.Comparator;

public abstract class Vehicle implements Comparable<Vehicle>
{ 
    private int distanceRate = 50;
    private String no;
    private int departure;
    private int destination;

    public Vehicle(String no, int departure, int destination){
        this.no = no;
        this.departure = departure;
        this.destination = destination;
    }

    public abstract int calcTollFee();
    public String getNo() { return no; }
    public int getDeparture() { return departure; }
    public int getDestination() { return destination; }
    public void setDistanceRate(int d) { distanceRate = d; }
    public int getDistanceRate() { return distanceRate; }

    public int compareTo(Vehicle other){
        return this.no.compareTo(other.getNo());
    }

    static class TollComparator implements Comparator<Vehicle> {
        @Override
        public int compare(Vehicle v1, Vehicle v2){
            return v1.calcTollFee() - v2.calcTollFee();
        }
    };
}