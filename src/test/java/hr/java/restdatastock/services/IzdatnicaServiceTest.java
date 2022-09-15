package hr.java.restdatastock.services;

import hr.java.restdatastock.MockEntityDataValues;
import hr.java.restdatastock.models.entities.IzdatnicaEntity;
import hr.java.restdatastock.repositories.IzdatnicaRepository;
import hr.java.restdatastock.services.impl.IzdatnicaServiceImpl;
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
class IzdatnicaServiceTest {


    @Mock
    private IzdatnicaRepository izdatnicaRepository;

    @InjectMocks
    private IzdatnicaServiceImpl izdatnicaService;

    @Nested
    @DisplayName("IzdatnicaServiceTest get all shipments")
    class IzdatnicaServiceTestGetAllShipments {

        @Test
        @DisplayName("GIVEN shipment records exists in database, WHEN all shipment records are requested, THEN all shipment records from database are returned.")
        void testGetAll() {
            final List<IzdatnicaEntity> expectedListOfIzdatnica = MockEntityDataValues.givenIzdatnicaDataRecords();

            when(izdatnicaRepository.findAll()).thenReturn(MockEntityDataValues.givenIzdatnicaDataRecords());
            final List<IzdatnicaEntity> actualListOfIzdatnica = izdatnicaService.getAll();

            assertAll(
                    () -> assertNotNull(actualListOfIzdatnica),
                    () -> assertEquals(expectedListOfIzdatnica, actualListOfIzdatnica)
            );
        }

        @Test
        @DisplayName("GIVEN there are no shipment records in database, WHEN all shipment records are requested, THEN empty list is returned.")
        void testGetAllEmpty() {
            final List<IzdatnicaEntity> expectedListOfIzdatnica = Collections.emptyList();

            when(izdatnicaRepository.findAll()).thenReturn(expectedListOfIzdatnica);
            final List<IzdatnicaEntity> actualListOfIzdatnica = izdatnicaService.getAll();

            assertAll(
                    () -> assertNotNull(actualListOfIzdatnica),
                    () -> assertEquals(expectedListOfIzdatnica, actualListOfIzdatnica)
            );
        }

    }

    @Nested
    @DisplayName("IzdatnicaServiceTest create shipment")
    class IzdatnicaServiceTestCreateIzdatnica {
        @Test
        @DisplayName("GIVEN shipment record exists in database, WHEN a shipment record is updated, THEN shipment record is returned.")
        void testCreateIzdatnica() {
            final IzdatnicaEntity expectedIzdatnica = MockEntityDataValues.givenIzdatnicaDataRecords().get(0);

            when(izdatnicaRepository.save(any(IzdatnicaEntity.class))).thenReturn(expectedIzdatnica);
            final IzdatnicaEntity actualIzdatnica = izdatnicaService.createIzdatnica(expectedIzdatnica);

            assertAll(
                    () -> assertNotNull(actualIzdatnica),
                    () -> assertEquals(expectedIzdatnica, actualIzdatnica)
            );
        }
    }

    @Nested
    @DisplayName("IzdatnicaServiceTest delete shipment")
    class IzdatnicaServiceTestDeleteIzdatnica {
        @Test
        @DisplayName("GIVEN shipment record either exist or not, WHEN a single shipment record is deleted, THEN repository delete method should be called once.")
        void testDeleteIzdatnica() {
            Long idIzdatnice = MockEntityDataValues.givenIzdatnicaDataRecords().get(1).getId();

            izdatnicaRepository.deleteIzdatnicaEntityById(idIzdatnice);

            verify(izdatnicaRepository, times(1)).deleteIzdatnicaEntityById(idIzdatnice);
        }
    }
}
