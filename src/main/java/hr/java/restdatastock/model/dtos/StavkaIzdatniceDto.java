package hr.java.restdatastock.model.dtos;

import lombok.*;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class StavkaIzdatniceDto {

    private Long id;
    private IzdatnicaDto stavkaIzdatniceIzdatnica;
    private RobaDto stavkaIzdatniceRobe;
    private Integer kolicina;
    private Boolean storno;
    private LocalDate datumStorno;
}