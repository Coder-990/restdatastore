package hr.java.restdatastock.services;

import hr.java.restdatastock.MockEntityDataValues;
import hr.java.restdatastock.models.entities.PrimkaEntity;
import hr.java.restdatastock.repositories.PrimkaRepository;
import hr.java.restdatastock.services.impl.PrimkaServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PrimkaServiceTest {

    @Mock
    private PrimkaRepository primkaRepository;

    @InjectMocks
    private PrimkaServiceImpl primkaService;

    @Nested
    @DisplayName("PrimkaServiceTest get all receipts")
    class PrimkaServiceTestGetAllCompanies {

        @Test
        @DisplayName("GIVEN receipt records exists in database, WHEN all receipt records are requested, THEN all receipt" +
                " records from database are returned.")
        void testGetAll() {
            final List<PrimkaEntity> expectedListOfPrimka = MockEntityDataValues.givenPrimkaDataRecords();

            when(primkaRepository.findAll()).thenReturn(MockEntityDataValues.givenPrimkaDataRecords());
            final List<PrimkaEntity> actualListOfPrimka = primkaService.getAll();

            assertAll(
                    () -> assertNotNull(actualListOfPrimka),
                    () -> assertEquals(expectedListOfPrimka, actualListOfPrimka)
            );
        }

        @Test
        @DisplayName("GIVEN there are no receipt records in database, WHEN all receipt records are requested, THEN empty list is returned.")
        void testGetAllEmpty() {
            final List<PrimkaEntity> expectedListOfPrimka = Collections.emptyList();

            when(primkaRepository.findAll()).thenReturn(Collections.emptyList());
            final List<PrimkaEntity> actualListOfPrimka = primkaService.getAll();

            assertAll(
                    () -> assertNotNull(actualListOfPrimka),
                    () -> assertEquals(expectedListOfPrimka, actualListOfPrimka)
            );
        }

    }

    @Nested
    @DisplayName("PrimkaServiceTest create receipt")
    class PrimkaServiceTestCreatePrimka {

        @Test
        @DisplayName("GIVEN receipt record exists in database, WHEN a receipt record is updated, THEN receipt record is updated and returned.")
        void testCreatePrimka() {
            final PrimkaEntity expectedListOfPrimka = MockEntityDataValues.givenPrimkaDataRecords().get(0);

            when(primkaRepository.save(any(PrimkaEntity.class))).thenReturn(expectedListOfPrimka);
            final PrimkaEntity actualListOfPrimka = primkaService.createPrimka(expectedListOfPrimka);

            assertAll(
                    () -> assertNotNull(actualListOfPrimka),
                    () -> assertEquals(expectedListOfPrimka, actualListOfPrimka)
            );
        }
    }

    @Nested
    @DisplayName("PrimkaServiceTest delete receipt")
    class PrimkaServiceTestDeletePrimka {
        @Test
        @DisplayName("GIVEN receipt record either exist or not, WHEN a single receipt record is deleted, THEN repository delete method should be called once.")
        void testDeletePrimka() {
            final PrimkaEntity primka = MockEntityDataValues.givenPrimkaDataRecords().get(1);

            primkaRepository.delete(primka);

            verify(primkaRepository, times(1)).delete(primka);
        }
    }
}
