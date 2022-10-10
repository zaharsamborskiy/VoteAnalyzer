package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Company {

    public int getIncome(){
        int income = 0;
        for (Employee e: employeeList) {
            if (e instanceof Manager){
                income += (((Manager) e).getSale());
            }
        }
        return income;
    }


    public List<Employee> getEmployeeList() {
        return new ArrayList<>(employeeList);
    }

    private List<Employee> employeeList = new ArrayList<>();


    public void hire(Employee employee){
        employeeList.add(employee);

    }
    public void hireAll(List<Employee> employees){
        for (Employee e : employees){
            hire(e);
        }
    }
    public void fire(Employee employee){
        employeeList.remove(employee);
    }


    public List<Employee> getTopSalaryStaff(int count){
        if (count < 0 ) {
            return Collections.emptyList();
        }
        if (count > employeeList.size()) {
            count = employeeList.size();
        }

        Collections.sort(employeeList);
        Collections.reverse(employeeList);
        return employeeList.subList(0, count);
    }
    public List<Employee> getLowestSalaryStaff(int count) {
        if (count < 0) {
            return Collections.emptyList();
        }
        if (count > employeeList.size()) {
            count = employeeList.size();
        }
        Collections.sort(employeeList);
        return employeeList.subList(0, count);
    }

}
