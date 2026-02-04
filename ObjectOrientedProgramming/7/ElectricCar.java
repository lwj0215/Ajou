public class ElectricCar extends Car
{
    //해당 코드 작성
    public ElectricCar(String no, int departure, int destination){
        super(no, departure, destination);
    }

    @Override
    public int calcTollFee()
    {
        return (int)(super.calcTollFee() / 2) ;
    }

    @Override
    public String toString()
    {
        //해당 코드 작성
        return "ElectricCar: No="+super.getNo()+", Departure="+super.getDeparture()+"km, Destination="+super.getDestination()+"km";
    }
}