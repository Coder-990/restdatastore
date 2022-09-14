package hr.java.restdatastock.models.dtos;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FirmeDto {

    private Long id;
    private String oibFirme;
    private String nazivFirme;
}
