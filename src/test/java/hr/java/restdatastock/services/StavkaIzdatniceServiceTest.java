package hr.java.restdatastock.services;

import hr.java.restdatastock.MockEntityDataValues;
import hr.java.restdatastock.models.entities.StavkaIzdatniceEntity;
import hr.java.restdatastock.repositories.StavkaIzdatniceRepository;
import hr.java.restdatastock.services.impl.StavkaIzdatniceServiceImpl;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StavkaIzdatniceServiceTest {

    @Mock
    private StavkaIzdatniceRepository stavkaIzdatniceRepository;

    @InjectMocks
    private StavkaIzdatniceServiceImpl stavkaIzdatniceService;

    @Nested
    @DisplayName("StavkaIzdatnicaServiceTest get all itemShipments")
    class StavkaIzdatnicaServiceTestGetAllItemShipments {

        @Test
        @DisplayName("GIVEN itemShipment records exists in database, WHEN all itemShipments records are requested, THEN all itemShipments records from database are returned.")
        void testGetAll() {
            final List<StavkaIzdatniceEntity> expectedListOfStavkaIzdatnice = MockEntityDataValues.givenStavkaIzdatniceDataRecords();

            when(stavkaIzdatniceRepository.findAll()).thenReturn(expectedListOfStavkaIzdatnice);
            final List<StavkaIzdatniceEntity> actualListOfStavkaIzdatnice = stavkaIzdatniceService.getAll();

            assertAll(
                    () -> assertNotNull(actualListOfStavkaIzdatnice),
                    () -> assertEquals(expectedListOfStavkaIzdatnice, actualListOfStavkaIzdatnice)
            );
        }

        @Test
        @DisplayName("GIVEN there are no itemShipments records in database, WHEN all itemShipments records are requested, THEN empty list is returned.")
        void testGetAllEmpty() {
            final List<StavkaIzdatniceEntity> expectedListOfStavkaIzdatnice = Collections.emptyList();

            when(stavkaIzdatniceRepository.findAll()).thenReturn(expectedListOfStavkaIzdatnice);
            final List<StavkaIzdatniceEntity> actualListOfStavkaIzdatnice = stavkaIzdatniceService.getAll();

            assertAll(
                    () -> assertNotNull(actualListOfStavkaIzdatnice),
                    () -> assertEquals(expectedListOfStavkaIzdatnice, actualListOfStavkaIzdatnice)
            );
        }
    }

    @Nested
    @DisplayName("StavkaIzdatnicaServiceTest create stavkaIzdatnice")
    class StavkaIzdatnicaServiceTestCreateItemShipments {

        @Test
        @DisplayName("GIVEN itemShipments record exists in database, WHEN a itemShipments record is created, THEN itemShipments record is returned.")
        void createStavkaIzdatnice() {
            final StavkaIzdatniceEntity expectedStavkaIzdatnice = MockEntityDataValues.givenStavkaIzdatniceDataRecords().get(0);

            when(stavkaIzdatniceRepository.save(any(StavkaIzdatniceEntity.class)))
                    .thenReturn(expectedStavkaIzdatnice);

            final StavkaIzdatniceEntity actualStavkaIzdatnica = stavkaIzdatniceService.createStavkaIzdatnice(expectedStavkaIzdatnice);

            assertAll(
                    () -> assertNotNull(actualStavkaIzdatnica),
                    () -> assertEquals(expectedStavkaIzdatnice, actualStavkaIzdatnica)
            );
        }
    }

    @Nested
    @DisplayName("StavkaIzdatnicaServiceTest cancel itemShipment")
    class StavkaIzdatnicaServiceTestCancelItemShipments {
        @Test
        @DisplayName("GIVEN cancelItemShipment record exists in database, WHEN a cancelItemShipment record is canceled/updated, THEN cancelItemShipment record is canceled and returned.")
        void createStornoStavkeIzdatnice() {
            final StavkaIzdatniceEntity expectedStavkaIzdatnice = MockEntityDataValues.givenStavkaIzdatniceDataRecords().get(2);

            when(stavkaIzdatniceRepository.findById(104L))
                    .thenReturn(MockEntityDataValues.givenStavkaIzdatniceDataRecords()
                            .stream()
                            .filter(stavkaIzdatniceEntity -> stavkaIzdatniceEntity.getId() == 104L).findFirst());
            when(stavkaIzdatniceRepository.save(any(StavkaIzdatniceEntity.class)))
                    .thenReturn(expectedStavkaIzdatnice);

            final StavkaIzdatniceEntity actualStavkaIzdatnice = stavkaIzdatniceService.createStornoStavkeIzdatnice(expectedStavkaIzdatnice, 104L);

            assertAll(
                    () -> assertNotNull(actualStavkaIzdatnice),
                    () -> assertEquals(expectedStavkaIzdatnice, actualStavkaIzdatnice)
            );
        }
    }
}
