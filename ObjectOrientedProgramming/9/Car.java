public class Car extends Vehicle
{ 
    public static final double BASE_VOLUME = 2000.0;  // 기준 배기량
    //private static int distanceRate = 50; 		// 거리 요율, 삭제

    // private String no; 삭제
    // private int departure; 삭제
    // private int destination; 삭제
    private int volume;					// 차량 배기량

    public Car(String no, int departure, int destination, int volume)
    {
        super(no, departure, destination);
        this.volume = volume;
    }
    @Override
    public int calcTollFee() 				// 통행료 계산
    { 
         double volumeRate = volume / BASE_VOLUME;
        // int distance = destination - departure; 삭제 및 수정
        int distance = getDestination() - getDeparture();
        // int toll = (int) ( distance * distanceRate * volumeRate ); 삭제 및 수정
        int toll = (int) (distance * getDistanceRate() * volumeRate);
         return toll;
    }
    // public String getNo() { return no; } 삭제
    // public int getDeparture() { return departure; } 삭제
    // public int getDestination() { return destination; } 삭제
    public int getVolume() { return volume; }
    // public static void setDistanceRate(int d) { distanceRate = d; } 삭제
    // public static int getDistanceRate() { return distanceRate; } 삭제
}