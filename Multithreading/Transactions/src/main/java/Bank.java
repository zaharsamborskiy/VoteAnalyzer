import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class Bank {

    private Map<String, Account> accounts;

    private final Random random = new Random();
    private final int limit = 50_000;

    public synchronized Map<String, Account> account()
    {
        accounts = new HashMap<>();
        for(int i = 1; i <= 5; i++)
        {
            String accName = "A" + i;
            int randomValue = random.nextInt(1_000_000);
            accounts.put(accName, new Account(accName, randomValue));
        }
        return accounts;
    }

        public synchronized boolean isFraud (String fromAccountNum, String toAccountNum,long amount)
        throws InterruptedException
        {
            Thread.sleep(1000);
            return random.nextBoolean();
        }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */
    public synchronized void transfer(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException
    {
        boolean block = true;
        if (amount >= limit)
        {
            block = isFraud(fromAccountNum,toAccountNum,amount);
            if (!block)
            {
                System.out.println("Перевод выше 50_000 т.р., счета заблокированы");
                return;
            }
        }

        if (getBalance(fromAccountNum) > amount && block)
        {
            accounts.get(fromAccountNum).setMoney(accounts.get(fromAccountNum).getMoney() - amount);
            accounts.get(toAccountNum).setMoney(accounts.get(toAccountNum).getMoney() + amount);
            System.out.println(toAccountNum + ": перевод в размере " + amount + "р. от " + fromAccountNum);
        } else
        {
            System.out.println("Недостаточно средств для перевода");
        }

    }
    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public synchronized int getBalance(String accountNum)
    {
        return (int) accounts.get(accountNum).getMoney();
    }

    public synchronized long getSumAllAccounts()
    {
        AtomicLong atomicLong = new AtomicLong();
        long sumAllAcc= 0;
        for (Map.Entry<String, Account> entry: accounts.entrySet())
        {
            atomicLong.addAndGet(entry.getValue().getMoney() + sumAllAcc);
        }
        return atomicLong.get();
    }
}
