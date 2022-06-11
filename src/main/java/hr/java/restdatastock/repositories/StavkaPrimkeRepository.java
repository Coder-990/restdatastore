package hr.java.restdatastock.repositories;

import hr.java.restdatastock.model.entities.StavkaPrimkeEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Primary
@Transactional
public interface StavkaPrimkeRepository extends JpaRepository <StavkaPrimkeEntity, Long> {
}
