package hr.java.restdatastock.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "izdatnica", schema = "datastock")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class IzdatnicaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDIzdatnice")
    private Long id;

    @Basic
    @Column(name = "Datum")
    private LocalDate datum;

    @ManyToOne
    @JoinColumn(name = "IDFirme", referencedColumnName = "IDFirme")
    private FirmeEntity izdatnicaFirme;

    @Override
    public String toString() {
        return this.izdatnicaFirme.getNazivFirme() + "- [created: " + this.datum + "]";
    }
}
