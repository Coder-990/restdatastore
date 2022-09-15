package hr.java.restdatastock.services;

import hr.java.restdatastock.MockEntityDataValues;
import hr.java.restdatastock.models.entities.StavkaPrimkeEntity;
import hr.java.restdatastock.repositories.StavkaPrimkeRepository;
import hr.java.restdatastock.services.impl.StavkaPrimkeServiceImpl;
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
class StavkaPrimkeServiceTest {

    @Mock
    private StavkaPrimkeRepository stavkaPrimkeRepository;

    @InjectMocks
    private StavkaPrimkeServiceImpl stavkaPrimkeService;

    @Nested
    @DisplayName("StavkaPrimkeServiceTest get all itemReceipts")
    class StavkaPrimkeServiceTestGetAllItemReceipts {
        @Test
        @DisplayName("GIVEN itemReceipts records exists in database, WHEN all itemReceipts records are requested, THEN all itemReceipts records from database are returned.")
        void testGetAll() {
            final List<StavkaPrimkeEntity> expectedListOfStavkaPrimke = MockEntityDataValues.givenStavkaPrimkeDataRecords();

            when(stavkaPrimkeRepository.findAll()).thenReturn(expectedListOfStavkaPrimke);
            final List<StavkaPrimkeEntity> actualListOfStavkaPrimke = stavkaPrimkeService.getAll();

            assertAll(
                    () -> assertNotNull(actualListOfStavkaPrimke),
                    () -> assertEquals(expectedListOfStavkaPrimke, actualListOfStavkaPrimke)
            );
        }

        @Test
        @DisplayName("GIVEN there are no itemReceipts records in database, WHEN all itemReceipts records are requested, THEN empty list is returned.")
        void testGetAllEmpty() {
            final List<StavkaPrimkeEntity> expectedListOfStavkaPrimke = Collections.emptyList();

            when(stavkaPrimkeRepository.findAll()).thenReturn(expectedListOfStavkaPrimke);
            final List<StavkaPrimkeEntity> actualListOfStavkaPrimke = stavkaPrimkeService.getAll();

            assertAll(
                    () -> assertNotNull(actualListOfStavkaPrimke),
                    () -> assertEquals(expectedListOfStavkaPrimke, actualListOfStavkaPrimke)
            );
        }
    }

    @Nested
    @DisplayName("StavkaPrimkeServiceTest create itemReceipts")
    class StavkaPrimkeServiceTestCreateItemReceipts {
        @Test
        @DisplayName("GIVEN there are no itemReceipts records in database, WHEN all itemReceipts records are requested, THEN empty list is returned.")
        void createStavkaPrimke() {
            final StavkaPrimkeEntity expectedStavkaPrimke = MockEntityDataValues.givenStavkaPrimkeDataRecords().get(0);

            when(stavkaPrimkeRepository.save(any(StavkaPrimkeEntity.class)))
                    .thenReturn(expectedStavkaPrimke);

            final StavkaPrimkeEntity actualStavkaIzdatnica = stavkaPrimkeService.createStavkaPrimke(expectedStavkaPrimke);

            assertAll(
                    () -> assertNotNull(actualStavkaIzdatnica),
                    () -> assertEquals(expectedStavkaPrimke, actualStavkaIzdatnica)
            );
        }
    }

    @Nested
    @DisplayName("StavkaPrimkeServiceTest cancel itemReceipts")
    class StavkaPrimkeServiceTestCancelItemReceipts {

        @Test
        @DisplayName("GIVEN cancelItemReceipts record exists in database, WHEN a cancelItemReceipts record is canceled/updated, THEN cancelItemReceipts record is canceled and returned.")
        void createStornoStavkePrimke() {
            final StavkaPrimkeEntity expectedStavkaPrimke = MockEntityDataValues.givenStavkaPrimkeDataRecords().get(0);

            when(stavkaPrimkeRepository.findById(4L))
                    .thenReturn(MockEntityDataValues.givenStavkaPrimkeDataRecords()
                            .stream()
                            .filter(stavkaPrimkeEntity -> stavkaPrimkeEntity.getId() == 4L).findFirst());
            when(stavkaPrimkeRepository.save(any(StavkaPrimkeEntity.class)))
                    .thenReturn(expectedStavkaPrimke);

            final StavkaPrimkeEntity actualStavkaPrimke = stavkaPrimkeService.createStornoStavkePrimke(expectedStavkaPrimke, 4L);

            assertAll(
                    () -> assertNotNull(actualStavkaPrimke),
                    () -> assertEquals(expectedStavkaPrimke, actualStavkaPrimke)
            );
        }
    }
}
