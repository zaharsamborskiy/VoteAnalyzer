import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Main {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String path = "rootpass";

        try {
            Connection connection = DriverManager.getConnection(url, user, path);
            Statement statement = connection.createStatement();



            ResultSet firstQuery = statement.executeQuery
                    ("SELECT course_name , count(*) subscription_date from purchaselist group by course_name");
            while (firstQuery.next()){
                String courseName = firstQuery.getString("course_name");
                String countSales = firstQuery.getString("subscription_date");
                double countsale = (Double.parseDouble(countSales));
                System.out.println(courseName + " продано курсов за год - " + countSales + " - среднее количество покупок в месяц - " + countsale / 12);
            }
            firstQuery.close();
            statement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
