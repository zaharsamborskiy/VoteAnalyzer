import java.util.Map;

public class Main
{
    public static void main(String[] args) {
        Bank bank = new Bank();
        Map<String, Account> accs = bank.account();

        System.out.println("Сумма на банковском счете " + bank.getSumAllAccounts() + "\n");

        Account a1 = new Account();
        String a1Name = accs.get("A1").getAccNumber();
        int a1Money = (int) accs.get("A1").getMoney();

        Account a2 = new Account();
        String a2Name = accs.get("A2").getAccNumber();
        int a2Money = (int) accs.get("A2").getMoney();


        for (int i = 0; i <= 100; i++)
        {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        bank.transfer(a2Name, a1Name, 5000);
                        bank.transfer(a1Name, a2Name, 50000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            thread.start();
        }
    }
}
