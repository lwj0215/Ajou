public class Truck extends Car
{
    private int weight;
    //해당 코드 작성
    public Truck(String no, int departure, int destination, int weight){
        super(no, departure, destination);
        this.weight = weight;
    }

    @Override
    public int calcTollFee()
    {
        //해당 코드 작성
        return 2000 + (int)((super.getDestination() - super.getDeparture()) * 50 * this.weight / 2);
    }

    @Override
    public String toString()
    {
        //해당 코드 작성
        return "Truck: No="+super.getNo()+", Departure="+super.getDeparture()+"km, Destination="+super.getDestination()+"km, "+ weight + "ton";
    }
}