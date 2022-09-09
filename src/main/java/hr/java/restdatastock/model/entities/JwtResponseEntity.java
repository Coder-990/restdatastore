package hr.java.restdatastock.model.entities;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class JwtResponseEntity {

    private UserEntity user;
    private String jwtToken;

}
