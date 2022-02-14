package account.business.entities.dbentities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.util.*;

@Data
@Entity
@Table(name = "principle_groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Group(String name) {
        this.name = name;
        this.users = new ArrayList<>();
    }

    public Group() {
        this.users = new ArrayList<>();
    }
}