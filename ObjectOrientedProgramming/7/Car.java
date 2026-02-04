public class Car
{
    private String no;
    private int departure;
    private int destination;

    public Car(String no, int departure, int destination)
    {
        this.no = no; 
        this.departure = departure;
        this.destination = destination;
    }
    public String getNo() { return no; }
    public int getDeparture() { return departure; }
    public int getDestination() { return destination; }

    @Override
    public String toString()
    {
        String result;
        result = "Car: No="+no+", Departure="+departure+"km, Destination="+destination+"km";
        return result;
    }
    public int calcTollFee() 
    {
        int distance = destination - departure;
        int toll = 1000 + distance * 50;
        return toll;
    }
}
