// Fig. 16.2: CollectionTest.java
// Collection interface demonstrated via an ArrayList object.
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
public class CollectionTest2 
{
      public static void main(String[] args)
   {
     // add elements in colors array to list
     String[] colors = {"BLUE", "MAGENTA", "RED", "WHITE", "BLUE", "CYAN", "RED", "CYAN", "RED" };
     LinkedList<String> list = new LinkedList<String>();      
    
     makeList(list, colors); 

     // output list contents
     System.out.printf("%n%nOriginal colors:%n");
      for (String color : list)
        System.out.printf("%s ", color);
      System.out.println();

      list.sort(Comparator.reverseOrder()); 

      // output list contents
      System.out.printf("%n%nSorted colors:%n");
      for (String color : list)
         System.out.printf("%s ", color);
      System.out.println();
      
      //2-(나)
      // get duplicates
      List<String> list2 = removeDuplicates(list);
      // output list contents
      System.out.printf("\n\nDuplicate-remove colors:\n");
      for (String color : list)
         System.out.printf("%s ", color);
      // output list contents
      System.out.printf("\n\nDuplicated colors:\n");
      for (String color : list2)
         System.out.printf("%s ", color);
      System.out.println();
   }

   private static void makeList(LinkedList<String> list, String[] colors)
   {
      //2-(가)
      ListIterator<String> listIter = list.listIterator();
      for(String s : colors) 
         listIter.add(s);
   }

   private static<T extends List<String>> T removeDuplicates(T list) {
      ListIterator<String> listIter = list.listIterator();
      List<String> res = new ArrayList<>();
      //T res = (T) new LinkedList<String>();
      while(listIter.hasNext()){
         String s = listIter.next();
         if (!res.contains(s)){
            res.add(s);
         }
      }
      return (T) res;
   }
   

} // end class CollectionTest

/**************************************************************************
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/

