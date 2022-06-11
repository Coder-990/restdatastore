package hr.java.restdatastock.repositories;

import hr.java.restdatastock.model.entities.PrimkaEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Primary
@Transactional
public interface PrimkaRepository extends JpaRepository<PrimkaEntity, Long> {

    long deletePrimkaEntityById(Long id);
}
