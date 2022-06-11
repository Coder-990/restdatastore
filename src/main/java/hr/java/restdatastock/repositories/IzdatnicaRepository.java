package hr.java.restdatastock.repositories;

import hr.java.restdatastock.model.entities.IzdatnicaEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Primary
@Transactional
public interface IzdatnicaRepository extends JpaRepository<IzdatnicaEntity, Long> {

    long deleteIzdatnicaEntityById(Long id);
}
