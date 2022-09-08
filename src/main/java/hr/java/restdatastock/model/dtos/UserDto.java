package hr.java.restdatastock.model.dtos;

import hr.java.restdatastock.model.entities.RoleEntity;
import lombok.*;

import java.util.Collection;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private Set<RoleEntity> roles;
}
