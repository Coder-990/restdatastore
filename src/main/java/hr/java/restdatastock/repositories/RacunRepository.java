package hr.java.restdatastock.repositories;

import hr.java.restdatastock.model.entities.RacunEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Primary
@Transactional
public interface RacunRepository extends JpaRepository<RacunEntity, String> {
}
