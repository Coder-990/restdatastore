package hr.java.restdatastock.services.impl;

import hr.java.restdatastock.model.entities.RoleEntity;
import hr.java.restdatastock.repositories.RoleRepository;
import hr.java.restdatastock.services.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public RoleEntity saveRole(RoleEntity roleEntity) {
        log.info("Saving role {} to the database", roleEntity.getName());
        return roleRepository.save(roleEntity);
    }
}
