package hr.java.restdatastock.model.dtos;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RoleDto {

    private String name;
    private String description;
}
