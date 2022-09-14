package hr.java.restdatastock.models.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "stavkaizdatnice", schema = "datastock")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class StavkaIzdatniceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDStavkaIzdatnice")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IDIzdatnice", referencedColumnName = "IDIzdatnice")
    private IzdatnicaEntity stavkaIzdatniceIzdatnica;

    @ManyToOne
    @JoinColumn(name = "IDRobe", referencedColumnName = "IDRobe")
    private RobaEntity stavkaIzdatniceRobe;

    @Basic
    @Column(name = "Kolicina")
    private Integer kolicina;

    @Basic
    @Column(name = "Storno")
    private Boolean storno;

    @Basic
    @Column(name = "DatumStorno")
    private LocalDate datumStorno;
}
