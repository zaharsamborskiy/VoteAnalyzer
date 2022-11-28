import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestBank extends TestCase {
    private Map<String, Account> accountMap = new HashMap<>();
    private Bank bank = new Bank();

    @Override
    protected void setUp() throws Exception
    {
        for (int i = 0; i <= 150; i++)
        {
            String name = String.valueOf(i);
            int money = (int) (Math.random() * 100_000);
            accountMap.put(name, new Account(name, money));
        }
    }

    @Test
    public void testTransfer()
    {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i <= 10; i++)
        {
            threads.add(new Thread(()-> {
                for (int j = 0; j <= 100; j++)
                {
                    try {
                        bank.transfer(String.valueOf((int) Math.random() * 150),String.valueOf((int) Math.random() * 150), (int) (Math.random() * 100_00));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                threads.forEach(Thread::start);
            }));
        }
    }
}
