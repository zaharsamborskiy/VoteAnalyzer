import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.*;


public class Main {

    public static void main(String[] args) {
        StandardServiceRegistry reg = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(reg).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hqlPurchase = "FROM " + PurchaseList.class.getSimpleName();
        List<PurchaseList> purchase = session.createQuery(hqlPurchase).getResultList();


        List<Linked> linkeds = new ArrayList<>();
        for (int i = 0; i < purchase.size(); i++){
            Linked linked = new Linked();
            linked.setPrice(purchase.get(i).getPrice());
            linked.setSubscriptionDate(purchase.get(i).getSubscriptionDate());

            String hqlCourse = "FROM " + Course.class.getSimpleName() + " WHERE name LIKE '%" + purchase.get(i).getCourseName() + "%'";
            List<Course> courseList = session.createQuery(hqlCourse).getResultList();
            int courseId = courseList.get(0).getId();

            String hqlStudents = "FROM " + Students.class.getSimpleName() + " WHERE name LIKE '%" + purchase.get(i).getStudentName() + "%'";
            List<Students> students = session.createQuery(hqlStudents).getResultList();
            int studentId = students.get(0).getId();

            linked.setId(new LinkedKey(studentId, courseId));

            linkeds.add(linked);
        }

        for (Linked l : linkeds){
            session.saveOrUpdate(l);
        }

        transaction.commit();
        sessionFactory.close();
    }
}
