
import java.util.function.Predicate;

public class HowManyEmployeeTest {
    public static void main(String[] args) {
        Employee[] emps = new Employee[3];

        emps[0] = new Employee("Kim", 10000);
        emps[1] = new Employee("Lee", 20000);
        emps[2] = new Employee("Kim", 30000);

        int n = HowManyEmployeeTest.howMany(emps, e -> e.getSalary() >= 20000);    // 2-(다)-(a): (emps, _____) 을 채우시오.
        int m =  HowManyEmployeeTest.howMany(emps, e -> e.getName() == "Kim");   // 2-(다)-(b): (emps, _____) 을 채우시오.

        System.out.println("Number of High - salaried Employees = "+n);
        System.out.println("Number of Employees named Kim = "+m);
    }

    public static int howMany(Employee[] emps, Predicate<Employee> t) {
    int n = 0;
    for (Employee e: emps) {
        if (t.test(e)) {
            n++;
        }
    }
    return n;
    }
}