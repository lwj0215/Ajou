
public class PairTest2
{
     public static void main(String[] args)
     {
	  Employee e1 = new Employee("Carl Cracker", 75000, 2007, 12, 15);
	  Employee e2 = new Employee("Gus Greedy", 80000, 2000, 1, 15);
	  Pair<Employee> p = new Pair<>(e1,e2);
	  System.out.println("First = "+p.getFirst());
	  System.out.println("Second = "+p.getSecond());
	 
	  Employee e3 = new Employee("Sid Sneaky", 100000, 1989, 1,1);
	 
	  changePartner(p, e3);
	  System.out.println("First = "+p.getFirst());
	  System.out.println("Second = "+p.getSecond()); 
     }
   
     public static void changePartner(Pair<Employee> p, Employee e)
     {
   	  p.setSecond(e);
     }
}
