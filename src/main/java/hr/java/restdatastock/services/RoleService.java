package hr.java.restdatastock.services;

import hr.java.restdatastock.model.entities.RoleEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoleService {

    @Transactional(readOnly = true)
    List<RoleEntity> getAll();

    RoleEntity saveRole(RoleEntity roleEntity);
}
