package hr.java.restdatastock.models.dtos;

import lombok.*;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PrimkaDto {

    private Long id;
    private LocalDate datum;
    private FirmeDto primkaFirme;
}
