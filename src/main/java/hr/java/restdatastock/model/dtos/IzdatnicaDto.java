package hr.java.restdatastock.model.dtos;

import lombok.*;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class IzdatnicaDto {

    private Long id;
    private LocalDate datum;
    private FirmeDto izdatnicaFirme;
}
