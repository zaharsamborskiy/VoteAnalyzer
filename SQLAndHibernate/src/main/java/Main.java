import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {

    public static void main(String[] args) {
        StandardServiceRegistry reg = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(reg).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();

        Subscriptions subscriptions = session.get(Subscriptions.class, new CompositeKey(1, 2));
        System.out.println(subscriptions.getCourse().getName() + " - " + subscriptions.getStudent().getName() + " - " + subscriptions.getSubscriptionDate());
        PurchaseList pl = session.get(PurchaseList.class, new KeyPL());
        System.out.println(pl.getStudentName());//???

        sessionFactory.close();
    }

}
