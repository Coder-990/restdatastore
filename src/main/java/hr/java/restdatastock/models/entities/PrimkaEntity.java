package hr.java.restdatastock.models.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "primka", schema = "datastock")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PrimkaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPrimke")
    private Long id;

    @Basic
    @Column(name = "Datum")
    private LocalDate datum;

    @ManyToOne
    @JoinColumn(name = "IDFirme", referencedColumnName = "IDFirme")
    private FirmeEntity primkaFirme;

    @Override
    public String toString() {
        return this.primkaFirme.getNazivFirme() + "-[" + this.primkaFirme.getOibFirme() + "]";
    }
}
