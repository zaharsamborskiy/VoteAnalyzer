import org.slf4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static java.lang.System.in;
import static java.lang.System.out;

public class RedisTest
{

    private static final int DELETE_SECONDS_AGO = 2;
    private static final int USERS = 20;
    private static final int SLEEP = 1000; // 1 секунда
    private static final SimpleDateFormat DF = new SimpleDateFormat("HH:mm:ss");

    private static void log(int UsersOnline) {
        String log = String.format("[%s] На главной странице показываем пользователя: %d", DF.format(new Date()), UsersOnline);
        out.println(log);
    }

    public static void main(String[] args) {

        RedisStorage redis = new RedisStorage();
        redis.init();
        int idUsers = 1;
        int iterable = 0;

        while (true) {
            if (iterable > 9)
            {
                iterable = 0;
                int user = (int) (Math.random() * USERS);
                String log = String.format("> Пользователь " + user + " оплатил платную услугу");
                out.println(log);
                log(user);
            }
            else
            {
                iterable++;
                int user = idUsers++;
                redis.logPageVisit(user);
                if (user == 20)
                {
                    idUsers = 1;
                }
                log(user);
            }
            try
            {
                redis.deleteOldEntries(DELETE_SECONDS_AGO);
                Thread.sleep(SLEEP);
            }
            catch (InterruptedException e)
            {
                redis.shutdown();
                throw new RuntimeException(e);
            }
        }
    }

}

