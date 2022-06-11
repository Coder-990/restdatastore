package hr.java.restdatastock.model.dtos;

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
