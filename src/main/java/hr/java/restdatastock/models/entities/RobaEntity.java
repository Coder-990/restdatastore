package hr.java.restdatastock.models.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "roba", schema = "datastock")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RobaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDRobe")
    private Long id;
    @Basic
    @Column(name = "NazivArtikla")
    private String nazivArtikla;
    @Basic
    @Column(name = "Cijena")
    private BigDecimal cijena;
    @Basic
    @Column(name = "Kolicina")
    private Integer kolicina;

    @Basic
    @Column(name = "Jmj")
    private String jmj;

    @Basic
    @Column(name = "Opis")
    private String opis;


    @Override
    public String toString() {
        return this.nazivArtikla + ", kolicina= "
                + this.kolicina + ", cijena= " + this.cijena;
    }
}
