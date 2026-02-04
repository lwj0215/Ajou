// Fig. 16.2: CollectionTest.java
// Collection interface demonstrated via an ArrayList object.
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CollectionTest1 
{
   public static void main(String[] args)
   {
      // add elements in colors array to list
      String[] colors = {"MAGENTA", "RED", "WHITE", "BLUE", "CYAN"};
      //List<String> list = new ArrayList<String>();  // 1-(나)
      LinkedList<String> list = new LinkedList<String>();  // 1-(나)

      for (String color : colors)
         list.add(color); // adds color to end of list      

      // add elements in removeColors array to removeList
      String[] removeColors = {"RED", "WHITE", "BLUE"};
      List<String> removeList = new ArrayList<String>(); // 1-(나)


      for (String color : removeColors)
         removeList.add(color); 

      // output list contents
      System.out.println("ArrayList: ");

      
      // for (int count = 0; count < list.size(); count++)     //1-(가)
      //    System.out.printf("%s ", list.get(count));
      // 1 - (가)
      for(String s: list){
         System.out.printf(s + " ");
      }

      // remove from list the colors contained in removeList
      removeColors(list, removeList);

      // output list contents
      System.out.printf("%n%nArrayList after calling removeColors:%n");

      for (String color : list)
         System.out.printf("%s ", color);
      System.out.println();
   } 

   // remove colors specified in collection2 from collection1
   private static void removeColors(Collection<String> collection1, 
      Collection<String> collection2)
   {
      // get iterator
      Iterator<String> iterator = collection1.iterator(); 

      // loop while collection has items
      while (iterator.hasNext())  //1-(다)       
      {
         // if (collection2.contains(iterator.next())) //1-(라)
         //    iterator.remove(); // remove current element//1-(라)
         String s = iterator.next();
         if (collection2.contains(s))
            collection1.remove(s);

      } 
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

