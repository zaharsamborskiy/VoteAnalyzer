import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor()
public class Account implements Comparable<Account>
{
    private String accNumber;
    private long money;

    @Override
    public int compareTo(Account a) {
        return this.getAccNumber().compareTo(a.getAccNumber());
    }
}
