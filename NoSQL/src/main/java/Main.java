import static java.lang.System.out;


public class Main
{
    static Client redis = new Client();

    public static void main(String[] args) {
        redis.init();

        int iterable = 0;
        while (true) {
            if (iterable > 5) {
                iterable = 0;
                int user = redis.userRandom();
                userPay(user);
            }
            else {
                iterable++;
                int user = redis.UsersNumber();
                user(user);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void user(int UsersOnline) {
        String log = String.format("— На главной странице показываем пользователя: " + UsersOnline);
        out.println(log);

    }

    private static void userPay(int user) {
        String log = String.format("> Пользователь " + user + " оплатил платную услугу");
        String log1 = String.format("— На главной странице показываем пользователя: " + user);
        out.println(log);
        out.println(log1);
    }
}
