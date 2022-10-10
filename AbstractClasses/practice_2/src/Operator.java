package src;

public class Operator implements Employee{
    public static final int SALARYOPER = 35000;
    private int salary = (int) (Math.random() * SALARYOPER) + SALARYOPER;

    @Override
    public int getMonthSalary() {
        return salary;
    }
}
