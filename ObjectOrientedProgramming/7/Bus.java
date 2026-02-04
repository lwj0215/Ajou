public class Bus extends Car
{
    //해당 코드 작성: 1-(가)
    private int passengers;

    public Bus(String no, int departure, int destination, int passengers){
        super(no, departure, destination);
        this.passengers = passengers;
    }

    @Override
    public int calcTollFee()
    {
        //해당 코드 작성: 1-(나)
        return 2000 + (int)((super.getDestination() - super.getDeparture()) * 50 * this.passengers / 24);
    }

    @Override
    public String toString()
    {
        //해당 코드 작성: 1-(다)
        return "Bus: No="+super.getNo()+", Departure="+super.getDeparture()+"km, Destination="+super.getDestination()+"km, "+ passengers + "명";
    }
}

