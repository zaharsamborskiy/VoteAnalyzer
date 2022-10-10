package src;
public class Manager implements Employee{
    public static final int SALARYMANG = 40000;
    static final int MAXINCOMECOMPANY = 140000;
    static final int MININCOMECOMPANY = 115000;
    public static final double BONUSPROCENTMAN = 0.05;
    private int sale;
    private int salary = (int) (Math.random() * SALARYMANG) + SALARYMANG;
    public Manager(){
        sale = (int) (Math.random() * (MAXINCOMECOMPANY - MININCOMECOMPANY) + MININCOMECOMPANY);
        salary += sale * BONUSPROCENTMAN;
    }
    public int getSale() {
        return sale;
    }
    @Override
    public int getMonthSalary() {
        return salary;
    }


}
