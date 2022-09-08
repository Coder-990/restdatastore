package hr.java.restdatastock.model.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "users", schema = "datastock")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDUser")
    private Long id;

    @Basic
    @Column(name = "Username", unique = true, nullable = false)
    private String username;

    @Basic
    @Column(name = "Password", nullable = false)
    private String password;

    @ManyToMany(fetch = EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = {
                    @JoinColumn(name = "ID_User")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ID_Role")
            }
    )
    private Set<RoleEntity> roles = new HashSet<>();

}

