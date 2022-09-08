package hr.java.restdatastock.repositories;

import hr.java.restdatastock.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Transactional
    long deleteFirmeEntityById(Long id);
    @Transactional
    UserEntity findByUsername(String username);
}
