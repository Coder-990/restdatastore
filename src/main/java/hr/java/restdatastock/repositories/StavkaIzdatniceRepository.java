package hr.java.restdatastock.repositories;

import hr.java.restdatastock.models.entities.StavkaIzdatniceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StavkaIzdatniceRepository extends JpaRepository<StavkaIzdatniceEntity, Long> {
}
