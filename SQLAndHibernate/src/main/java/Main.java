import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;


public class Main {

    public static void main(String[] args) {
        StandardServiceRegistry reg = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(reg).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();


        Subscriptions subscriptions = session.get(Subscriptions.class, new CompositeKey(4, 3));
        System.out.println(subscriptions.getCourse().getName() + " - " + subscriptions.getStudent().getName() + " - " + subscriptions.getSubscriptionDate());

        PurchaseList purchaseList = session.get(PurchaseList.class, new CompositeKeyPurchase("Бойков Максим","Веб-разработчик c 0 до PRO"));
        System.out.println(purchaseList.getStudentName() + " - " + purchaseList.getCourseName() + " - " + purchaseList.getPrice());

        Students students = session.get(Students.class, 1);
        List<Course> listCourse = students.getCourseList();
        for (Course c : listCourse){
            System.out.println(students.getName() + " - " + c.getName());
        }


        sessionFactory.close();

    }

}
