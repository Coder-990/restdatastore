package hr.java.restdatastock.model.dtos;

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
