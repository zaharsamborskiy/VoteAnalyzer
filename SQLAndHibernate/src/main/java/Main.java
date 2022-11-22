import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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

        String hqlPurchase =  "FROM " + PurchaseList.class.getSimpleName();
        List<PurchaseList> purchase = session.createQuery(hqlPurchase).getResultList();

        String hqlStudent = "FROM " + Students.class.getSimpleName();
        List<Students> students = session.createQuery(hqlStudent).getResultList();

        String hqlCourse = "FROM " + Course.class.getSimpleName();
        List<Course> courses = session.createQuery(hqlCourse).getResultList();

        Transaction transaction = session.beginTransaction();



        transaction.commit();
        sessionFactory.close();
    }

}
