import java.util.*;

// 2-(가): 아래 EmployeeSortTest 클래스에는 어떤 컴파일 문제가 있는지 설명하시오.
// ( EmployeeSortTest.java는 Employee 객체를 급여(salary) 오름차순으로 정렬하는 프로그램이다. )

// 2-(나): (가)번의 문제를 해결하기 위해 Comparator Interface를 구현하는 lambda expression을 사용할 수 있다.
// 어디를 어떻게 수정하면 되는지 코드를 첨부하시오. [ 단, Employee 클래스는 수정할 수 없다. ]

public class EmployeeSortTest {
    public static void main(String[] args) {
        List<Employee> elist = new ArrayList<>();
        elist.add(new Employee("Lee", 10000));
        elist.add(new Employee("Kim", 20000));
        elist.add(new Employee("Park", 8000));
        elist.add(new Employee("Han", 15000));

        Collections.sort(elist, (Employee e1, Employee e2)->e1.getSalary()-e2.getSalary());
        System.out.println(elist);
    }
}