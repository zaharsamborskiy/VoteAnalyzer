package src;

public class TopManager implements Employee{

    public static final int INCOME = 10_000_000;
    public static final double BONUSPROCENT = 1.5;
    public static final int SALARYTOP = 60000;
    private int salary = (int) (Math.random() * SALARYTOP) + SALARYTOP;
    private Company company;
    public TopManager(Company company){
        this.company = company;
    }
    @Override
    public int getMonthSalary() {
        if (company.getIncome() > INCOME) {
            return (int) (salary + salary * BONUSPROCENT);
        }else {
            return salary;
        }
    }
}
