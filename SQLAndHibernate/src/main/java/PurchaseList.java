import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "purchaselist")
public class PurchaseList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private CompositeKeyPurchase id;

    @Column(name = "student_name", insertable = false, updatable = false)
    private String studentName;
    @Column(name = "course_name", insertable = false, updatable = false)
    private String courseName;

    private int price;
    @Column(name = "subscription_date", insertable = false, updatable = false)
    private Date subscriptionDate;

}
