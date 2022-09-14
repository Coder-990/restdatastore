package hr.java.restdatastock.repositories;

import hr.java.restdatastock.models.entities.RobaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RobaRepository extends JpaRepository<RobaEntity, Long> {
    @Transactional
    long deleteRobaEntityById(Long id);
    @Query("SELECT re " +
            "FROM RobaEntity re " +
            "WHERE re.nazivArtikla = :#{#current.nazivArtikla} " +
            "AND  re.id <> :#{#current.id}")
    List<RobaEntity> checkIfExistsAllByNazivArtiklaAndIdNotEquals(@Param("current") RobaEntity current);
}
