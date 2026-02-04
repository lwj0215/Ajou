public class PollTest 
{
    public static void main(String[] args) 
    {
        int[] data = { 1, 3, 1, 2 };
        int[] freq = new int[3]; // initialized by 0
        for (int i = 0; i < data.length; i++) {
            try{
                ++freq[data[i]];
                System.out.print("C");
            } 
            catch(ArrayIndexOutOfBoundsException e){
                System.out.println("E");
            }
        }
        System.out.println();
        for (int i = 1; i < freq.length; i++) {
            System.out.printf("%d:%d%n", i, freq[i]);
        }
    }
}
