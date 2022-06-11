package hr.java.restdatastock.model.dtos;

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
