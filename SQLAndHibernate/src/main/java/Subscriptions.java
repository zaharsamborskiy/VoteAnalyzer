import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Subscriptions")
public class Subscriptions implements Serializable {

    public Subscriptions() {}

    @EmbeddedId
    private CompositeKey id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Students student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Course course;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public Students getStudent() {
        return student;
    }

    public void setStudent(Students student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public CompositeKey getId() {return id;}

    public void setId(CompositeKey id) {this.id = id;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscriptions that = (Subscriptions) o;
        return id.equals(that.id) && student.equals(that.student) && course.equals(that.course);}

    @Override
    public int hashCode() {
        return Objects.hash(id, student, course);
    }
}
