package hr.java.restdatastock.repositories;

import hr.java.restdatastock.models.entities.RacunEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RacunRepository extends JpaRepository<RacunEntity, String> {
}
