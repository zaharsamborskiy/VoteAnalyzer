import java.util.HashMap;
import java.util.Map;

public class CustomerStorage {
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;


        String[] components = data.split("\\s+");
        if (components.length != 4) {
            String smsWrong = "Wrong format! Correct format: add Василий Петров vasily.petrov@gmail.com +79215637722";
            throw new IllegalArgumentException(smsWrong);
        }
        String regexPhone = "\\+79[0-9]{9}";
        if (!components[INDEX_PHONE].matches(regexPhone)){
            String smsWrong = "Wrong format! Correct format: +79215637722";
            throw new IllegalArgumentException(smsWrong);
        }
        String regexEmail = "[a-z.]+@[a-z.]+[a-z]+";
        if (!components[INDEX_EMAIL].matches(regexEmail)) {
            String smsWrong = "Wrong format! Correct format: vasily.petrov@gmail.com";
            throw new IllegalArgumentException(smsWrong);
        }
        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}