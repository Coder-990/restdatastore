package hr.java.restdatastock.model.entities;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class JwtRequestEntity {

    private String username;
    private String userPassword;
}
