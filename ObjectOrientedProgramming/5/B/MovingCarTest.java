public class MovingCarTest{
      public static void main(String[] args)
      {
            //해당부분작성
            Car car = new Car("c1111", 120, 100);
            MovingCar mc = new MovingCar(car, 0, 400);
            System.out.println(mc.getCar().toString());
            System.out.println(mc.calcTollFee());
      } 
}
