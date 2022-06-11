package hr.java.restdatastock.repositories;

import hr.java.restdatastock.model.entities.RobaEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Primary
@Transactional
public interface RobaRepository extends JpaRepository<RobaEntity, Long> {

    long deleteRobaEntityById(Long id);
    @Query("SELECT re " +
            "FROM RobaEntity re " +
            "WHERE re.nazivArtikla = :#{#current.nazivArtikla} " +
            "AND  re.id <> :#{#current.id}")
    List<RobaEntity> checkIfExistsAllByNazivArtiklaAndIdNotEquals(@Param("current") RobaEntity current);
}
