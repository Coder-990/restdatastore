package hr.java.restdatastock.repositories;

import hr.java.restdatastock.model.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

//    RoleEntity findByName(String name);
}
