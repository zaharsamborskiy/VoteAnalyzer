import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Students")
public class Students implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int age;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Subscriptions",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")})
    private List<Course> CourseList;

    @Column(name = "registration_date")
    private Date registrationDate;

    public List<Course> getCourseList() {
        return CourseList;
    }

    public void setCourseList(List<Course> courseList) {
        CourseList = courseList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
