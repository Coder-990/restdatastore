package hr.java.restdatastock.model.entities;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "roles", schema = "datastock")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RoleEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDRole")
    private Long id;

    @Basic
    @Column(name = "RoleType", unique = true, nullable = false)
    private String name;

}

