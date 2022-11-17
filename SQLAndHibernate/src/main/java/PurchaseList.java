import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "purchaselist")
public class PurchaseList {
    @EmbeddedId
    private KeyPL id;
    @Column(name = "student_name", insertable = false, updatable = false)
    private String studentName;
    @Column(name = "course_name", insertable = false, updatable = false)
    private String courseName;
    @Column(name = "price", insertable = false, updatable = false)
    private int price;
    @Column(name = "subscription_date", insertable = false, updatable = false)
    private Date subscriptionDate;
    public PurchaseList() {}
    public PurchaseList(KeyPL id) {
        this.id = id;
    }
    public String getStudentName() {return studentName;}
    public void setStudents(String students) {
        this.studentName = students;
    }

    public String getCourse() {
        return courseName;
    }

    public void setCourse(String course) {
        this.courseName = course;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

}
