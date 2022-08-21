package hr.java.restdatastock.model.dtos;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RobaDto {

    private Long id;
    private String nazivArtikla;
    private BigDecimal cijena;
    private Integer kolicina;
    private String jmj;
    private String opis;
}
