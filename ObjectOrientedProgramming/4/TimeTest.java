public class TimeTest {
    public static void main(String[] args) 
    {
        Time t1 = new Time(23, 30);
        Time t2 = new Time(2, 40);

        // 4-(가)
        System.out.println(t1.toString());
        System.out.println(t2.toString());

        // 4-(나)
        System.out.printf("%02d:%02d\n",t1.getHour(),t1.getMinute());
        System.out.printf("%02d:%02d\n",t2.getHour(),t2.getMinute());
        
        // 4-(다)
        t1.add(t2);
        System.out.println(t1.toString());
    }
}
// 4-(라) java TimeTest 실행 결과 첨부
