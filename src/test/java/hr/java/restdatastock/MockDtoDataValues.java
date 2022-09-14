package hr.java.restdatastock;

import hr.java.restdatastock.models.dtos.FirmeDto;

import java.util.Arrays;
import java.util.List;

public class MockDtoDataValues {

    public static final FirmeDto FIRMA_PRIME_SOFTWARE = FirmeDto.builder().id(1L).oibFirme("45485474525").nazivFirme("Prime Software").build();
    public static final FirmeDto FIRMA_TECH_FOOT = FirmeDto.builder().id(2L).oibFirme("586510002144").nazivFirme("TechFoot").build();
    public static final FirmeDto FIRMA_KILOBYTE = FirmeDto.builder().id(3L).oibFirme("02013025652").nazivFirme("KiloByte").build();
    public static final FirmeDto FIRMA_CYBER_TECH= FirmeDto.builder().id(4L).oibFirme("47459652365").nazivFirme("CyberTech").build();
    public static final FirmeDto FIRMA_LUMINUM= FirmeDto.builder().id(5L).oibFirme("54526589110").nazivFirme("Luminum").build();
    public static final FirmeDto FIRMA_KILOBYTE_TEST= FirmeDto.builder().id(6L).oibFirme("02013025652").nazivFirme("KiloByteTest").build();

    public static List<FirmeDto> givenFirmaDtoDataRecords() {
        return Arrays.asList(
                FIRMA_PRIME_SOFTWARE, FIRMA_TECH_FOOT, FIRMA_KILOBYTE,
                FIRMA_CYBER_TECH, FIRMA_LUMINUM, FIRMA_KILOBYTE_TEST
        );
    }
}
