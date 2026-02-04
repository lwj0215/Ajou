
public class PairTest1
{
   public static void main(String[] args)
   {
      String[] words = { "Mary", "had", "a", "little", "lamb" };
      Pair<String> mm = ArrayAlg.minmax(words);
      System.out.println("min = " + mm.getFirst());
      System.out.println("max = " + mm.getSecond());
      //
      Employee[] emps = new Employee[4];
      emps[0] = new Employee("Kim", 100000.0, 2015, 3, 1);
      emps[1] = new Employee("Lee", 200000.0, 2005, 9, 1);
      emps[2] = new Employee("Hong", 80000.0, 2017, 5, 1);
      emps[3] = new Employee("Park", 150000.0, 2008, 3, 1);
      Pair<Employee> ee = ArrayAlg.minmax(emps);
      System.out.println("min = " + ee.getFirst());
      System.out.println("max = " + ee.getSecond());
   }
}

class ArrayAlg
{
   // public static Pair<String> minmax(String[] a)
   // {
   //    if (a == null || a.length == 0) return null;
   //    String min = a[0];
   //    String max = a[0];
   //    for (int i = 1; i < a.length; i++)
   //    {
   //       if (min.compareTo(a[i]) > 0) min = a[i];
   //       if (max.compareTo(a[i]) < 0) max = a[i];
   //    }
   //    return new Pair<>(min, max);
   // }

   // public static Pair<Employee> minmax(Employee[] emp){
   //    if(emp == null || emp.length == 0) return null;
   //    Employee min = emp[0];
   //    Employee max = emp[0];
   //    for(int i = 0;i<emp.length;i++){
   //       if(min.compareTo(emp[i]) > 0) min = emp[i];
   //       if(max.compareTo(emp[i]) < 0) max = emp[i];
   //    }
   //    return new Pair<>(min, max);
   // }

   public static Pair<T> minmax(T[] a){
      if (a == null || a.length == 0) return null;
      T min = a[0];
      T max = a[0];
      for (int i = 1; i < a.length; i++){
         if (min.compareTo(a[i]) > 0) min = a[i];
         if (max.compareTo(a[i]) < 0) max = a[i];
      }
      return new Pair<>(min, max);
   }
}
