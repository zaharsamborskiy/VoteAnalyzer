package src;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Company company = new Company();

        for (int i = 0; i < 180; i++) {
            company.hire(new Operator());
        }
        List<Employee> workers = new ArrayList<>();
        for (int i = 0; i < 80; i++) {
            workers.add(new Manager());
        }
        for (int i = 0; i < 10; i++) {
            company.hire(new TopManager(company));
        }
        company.hireAll(workers);
        List<Employee> topSalaryStaff = company.getTopSalaryStaff(10);
        System.out.println("Высокие з/п : ");
        for (Employee e: topSalaryStaff) {
            System.out.println(e.getMonthSalary());
        }
        System.out.println("_____________________");
        List<Employee> lowSalaryStaff = company.getLowestSalaryStaff(30);
        System.out.println("Низкие з/п : ");
        for (Employee e: lowSalaryStaff) {
            System.out.println(e.getMonthSalary());
        }
        System.out.println("_____________________");
        List<Employee> employeeList = company.getEmployeeList();
        System.out.println("Количество сотрудников: " + employeeList.size());
        for (int i = 0; i < employeeList.size()/2; i++) {
            company.fire(employeeList.get(i));
        }
        System.out.println("_____________________");
        System.out.println("Количество сотрудников после увольнения: " + company.getEmployeeList().size());
        System.out.println("_____________________");
        List<Employee> topSalaryStaff1 = company.getTopSalaryStaff(10);
        System.out.println("Высокие з/п после увольнений: ");
        for (Employee e: topSalaryStaff1) {
            System.out.println(e.getMonthSalary());
        }
        System.out.println("_____________________");
        List<Employee> lowSalaryStaff1 = company.getLowestSalaryStaff(30);
        System.out.println("Низкие з/п после увольнений: ");
        for (Employee e: lowSalaryStaff1) {
            System.out.println(e.getMonthSalary());
        }
    }
}
