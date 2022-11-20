import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "purchaselist")
public class PurchaseList {

    @EmbeddedId
    private CompositeKeyPurchase id;

    @Column(name = "student_name", insertable = false, updatable = false, nullable = true)
    private String studentName;

    @Column(name = "course_name", insertable = false, updatable = false, nullable = true)
    private String courseName;

    private int price;
    @Column(name = "subscription_date", insertable = false, updatable = false)
    private Date subscriptionDate;

    public PurchaseList() {}

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "course_name", referencedColumnName = "name", insertable = false, updatable = false)
//    private Course course;
//
//    public Course getCourse() {
//        return course;
//    }
//
//    public void setCourse(Course course) {
//        this.course = course;
//    }

    public String getStudentName() {
        return studentName;
    }


    public void setStudentName(String students) {
        this.studentName = students;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String course) {
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
