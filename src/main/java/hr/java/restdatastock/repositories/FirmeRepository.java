package hr.java.restdatastock.repositories;

import hr.java.restdatastock.model.entities.FirmeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FirmeRepository extends JpaRepository<FirmeEntity, Long> {

    long deleteFirmeEntityById(Long id);

    @Query("SELECT fe " +
            "FROM FirmeEntity fe " +
            "WHERE fe.oibFirme = :#{#current.oibFirme} " +
            "AND  fe.id <> :#{#current.id}")
    List<FirmeEntity> checkIfExistsAllByOibAndIdNotEquals(@Param("current") FirmeEntity current);
}
