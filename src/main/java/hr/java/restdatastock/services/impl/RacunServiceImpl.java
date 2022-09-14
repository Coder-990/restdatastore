package hr.java.restdatastock.services.impl;

import hr.java.restdatastock.exceptions.RacunEntityUserIdCheckRuntimeExcpetion;
import hr.java.restdatastock.exceptions.RacunEntityUserPasswordCheckRuntimeExcpetion;
import hr.java.restdatastock.models.entities.RacunEntity;
import hr.java.restdatastock.repositories.RacunRepository;
import hr.java.restdatastock.services.RacunService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RacunServiceImpl implements RacunService {

    private final RacunRepository racunRepository;

    @Override
    public Optional<RacunEntity> login(final String userid, final String password) {
        RacunEntity racun = this.racunRepository.findById(userid)
                .orElseThrow(() -> new RacunEntityUserIdCheckRuntimeExcpetion(userid));
        if (!password.equals(racun.getPassword())) {
            throw new RacunEntityUserPasswordCheckRuntimeExcpetion(password);
        }
        return Optional.of(racun);
    }

    @Override
    public RacunEntity createAccount(final RacunEntity racun) {
        return this.racunRepository.save(racun);
    }

}
