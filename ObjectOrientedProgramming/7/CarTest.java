public class CarTest 
{
    public static void main(String[] args) 
    {
        //해당 코드 작성: 1-(라)
        // Car c1 = new Car("c1111", 0, 200);
        // Bus b1 = new Bus("b2222", 100, 400, 40);
        
        // System.out.print(c1.toString());
        // System.out.println(", TollFee="+ c1.calcTollFee()+"원");
        // System.out.print(b1.toString());
        // System.out.println(", TollFee="+ b1.calcTollFee()+"원");
        //실행 결과 첨부 후 1-(마)를 해결할 때에는 주석처리할 것.

        //해당 코드 작성: 1-(마)
        Car[] cars = {
            new Car("c1111", 0, 200),
            new Bus("b2222", 100, 400, 40),
            new Truck("t3333", 0, 100, 4),
            new ElectricCar("e4444", 0, 200)
        };
        for(Car c : cars)
            System.out.println(c.toString() + ", " + c.calcTollFee() + "원");

        //해당 코드 작성: 4

    }
}
//실행결과