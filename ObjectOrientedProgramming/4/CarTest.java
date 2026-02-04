public class CarTest {
    public static void main(String[] args) 
    {
        Car[] carList = new Car[2];

        // 2-(가)
        carList[0] = new Car("c1111", 80, 0);
        carList[1] = new Car("c2222", 100, 0);
        
        // 2-(나)
        for(Car c : carList){
            c.move(60);
        }

        // 2-(다)
        switch(carList[0].compareTo(carList[1])){
            case 1 : System.out.println(carList[0].toString());
            case 0 : System.out.println("equal");
            case -1 : System.out.println(carList[1].toString());
        }
   
    }
}
// 2-(라) java carTest 실행 결과 첨부