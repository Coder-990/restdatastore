package hr.java.restdatastock.services;

import hr.java.restdatastock.MockEntityDataValues;
import hr.java.restdatastock.exceptions.RacunEntitiyNotFoundRuntimeException;
import hr.java.restdatastock.model.entities.RacunEntity;
import hr.java.restdatastock.repositories.RacunRepository;
import hr.java.restdatastock.services.impl.RacunServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RacunServiceTest {

    @Mock
    private RacunRepository racunRepository;

    @InjectMocks
    private RacunServiceImpl racunService;

    @Nested
    @DisplayName("RacunService get user by id")
    class RacunServiceTestGetOneById {

        @Test
        @DisplayName("GIVEN racun record exists in database, WHEN a single racun record is fetched, THEN the racun with requested ID is returned.")
        void testLoginGetOneById() {
            final Optional<RacunEntity> expectedRacun = MockEntityDataValues.givenRacunDataRecords().stream().findFirst();
            when(racunRepository.findById("user"))
                    .thenReturn(expectedRacun.stream().filter(racun -> racun.getUserId().equals("user")).findFirst());

            Optional<RacunEntity> actualRacun = racunService.login("user", "user");

            assertAll(
                    () -> assertNotNull(actualRacun),
                    () -> assertEquals(expectedRacun, actualRacun)
            );
        }

        @Test
        @DisplayName("GIVEN racun record does not exists in database, WHEN a single racun record is fetched, THEN error is thrown.")
        void testLoginGetOneByNonExistingId() {
            Class<RacunEntitiyNotFoundRuntimeException> expectedExceptionClass = RacunEntitiyNotFoundRuntimeException.class;

            when(racunRepository.findById("unknown")).thenThrow(new RacunEntitiyNotFoundRuntimeException("unknown"));
            Executable executable = () -> racunService.login("unknown","unknown");

            assertThrows(expectedExceptionClass, executable);
        }


        @Nested
        @DisplayName("RacunService create user")
        class UserServiceTestCreateUser {
            @Test
            @DisplayName("GIVEN racun record exists in database, WHEN a single racun record is created, THEN new record is created and returned.")
            void testCreateAccount() {
                final RacunEntity expectedRacun = MockEntityDataValues.givenRacunDataRecords().get(0);

                when(racunRepository.save(any(RacunEntity.class)))
                        .thenReturn(expectedRacun);

                final RacunEntity actualRacun = racunService.createAccount(expectedRacun);

                assertAll(
                        () -> assertNotNull(actualRacun),
                        () -> assertEquals(expectedRacun, actualRacun)
                );
            }
        }
    }
}