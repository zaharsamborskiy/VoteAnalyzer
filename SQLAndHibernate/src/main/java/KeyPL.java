import jakarta.persistence.Column;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
public class KeyPL implements Serializable {
    @Column(name = "student_name")
    private String studentName;
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "price")
    private int price;
    @Column(name = "subscription_date")
    private Date subscriptionDate;
    public KeyPL(){}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyPL keyPL = (KeyPL) o;
        return price == keyPL.price && studentName.equals(keyPL.studentName) && courseName.equals(keyPL.courseName) && subscriptionDate.equals(keyPL.subscriptionDate);
    }
    @Override
    public int hashCode() {
        return Objects.hash(studentName, courseName, price, subscriptionDate);
    }
}
