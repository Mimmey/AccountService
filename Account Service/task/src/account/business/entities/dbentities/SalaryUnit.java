package account.business.entities.dbentities;

import account.business.entities.dto.SalaryUnitDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {"user_id", "period"}) })
public class SalaryUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private long id;

    @NotNull
    @JsonIgnore
    @Column(columnDefinition = "DATE")
    private Date period;

    @NotNull
    private long salary;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public SalaryUnit(long salary, User user, Date period) {
        this.period = period;
        this.salary = salary;
        this.user = user;
    }
}
