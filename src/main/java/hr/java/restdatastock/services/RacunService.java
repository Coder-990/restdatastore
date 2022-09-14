package hr.java.restdatastock.services;

import hr.java.restdatastock.models.entities.RacunEntity;

import java.util.Optional;

public interface RacunService {

    Optional<RacunEntity> login(final String userId, final String password);

    RacunEntity createAccount(final RacunEntity racun);
}
