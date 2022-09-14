package hr.java.restdatastock.models.dtos;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RacunDto {

    private String userId;
    private String password;
}
