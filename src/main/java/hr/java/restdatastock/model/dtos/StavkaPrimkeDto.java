package hr.java.restdatastock.model.dtos;

import lombok.*;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class StavkaPrimkeDto {

    private Long id;
    private PrimkaDto stavkaPrimkePrimka;
    private RobaDto stavkaPrimkeRobe;
    private Integer kolicina;
    private Boolean storno;
    private LocalDate datumStorno;
}
