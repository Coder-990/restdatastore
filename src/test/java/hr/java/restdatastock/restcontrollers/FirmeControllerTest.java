package hr.java.restdatastock.restcontrollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.java.restdatastock.repositories.FirmeRepository;
import hr.java.restdatastock.services.FirmeService;
import org.mockito.InjectMocks;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(FirmeController.class)
class FirmeControllerTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
//    @Autowired
//    private ObjectMapper objectMapper;
    @Autowired
    private ModelMapper modelMapper;
    @MockBean
    private FirmeService firmeService;
    @MockBean
    private FirmeRepository firmeRepository;
    @InjectMocks
    private FirmeController firmeController;

//    @BeforeEach
//    public void setUp() {
//        RestAssuredMockMvc.mockMvc(mockMvc);
////        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }
//
//    @Test
//    @DisplayName("GIVEN controller is registered as REST controller, WHEN all endpoint is called, THEN all entries from database has been returned to the client.")
//    void testGetAll() throws Exception {
//        final List<FirmeEntity> expectedList = MockEntityDataValues.givenFirmeDataRecords();
//
////        this.mockMvc.perform(get("/companies")).andExpect(status().isOk())
////                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
////                .andExpect(content().json(objectMapper.writeValueAsString(MockDtoDataValues.FIRMA_PRIME_SOFTWARE), true))
////                .andDo(print())
////                .andReturn();
//
//        when(this.firmeService.getAll()).thenReturn(expectedList);
//        RestAssuredMockMvc.given().auth().none()
//                .param("1L", "id", "oibFirme", "45485474525", "nazivFirme", "Prime Software")
//                .when()
//                .get("/companies")
//                .then()
//                .statusCode(200);
////                .body("$.size()", Matchers.equalTo(1));
////                .body("$[0].id", Matchers.equalTo(expectedList.get(0).getId()))
////                .body("$[0].oibFirme", Matchers.equalTo(expectedList.get(0).getOibFirme()))
////                .body("$[0].nazivFirme", Matchers.equalTo(expectedList.get(0).getNazivFirme()));
////        when(firmeRepository.findAll()).thenReturn(
////                List.of(
////                        modelMapper.map(expectedList, FirmeEntity.class)
////                )
////        );
////
////        mockMvc.perform(get("/companies")).andDo(print())
////                .andExpect(status().isOk())
////                .andExpect(jsonPath("$", hasSize(6)));
//
//        verify(this.firmeRepository, times(1)).findAll();
//    }
//
//    @Test
//    void testGetAllEmpty() {
//        final List<FirmeEntity> expectedListOfFirma = Collections.emptyList();
//
//        when(this.firmeService.getAll()).thenReturn(expectedListOfFirma);
//        RestAssuredMockMvc.given().auth().none()
//                .when()
//                .get("/companies")
//                .then()
//                .statusCode(200);
//    }
//
//    @Test
//    @DisplayName("GIVEN controller is registered as REST controller, WHEN one endpoint is called, THEN entry with specific ID has been returned to the client.")
//    void testGetById() {
//        final List<FirmeEntity> expectedList = MockEntityDataValues.givenFirmeDataRecords();
//
//        when(this.firmeService.getAll()).thenReturn(List.of(expectedList.get(0)));
//        RestAssuredMockMvc.given().auth().none()
//                .param("1L", "id", "oibFirme", "45485474525", "nazivFirme", "Prime Software")
//                .when()
//                .get("/companies/1")
//                .then()
//                .statusCode(200);
//
//        verify(this.firmeService, times(1)).getOneById(1L);
//    }
//
//    @Test
//    @DisplayName("GIVEN company record does not exists in database, WHEN a single comapny record is fetched, THEN not found error message is returned.")
//    void testGetOneNonExisting() throws Exception {
//        mockMvc.perform(get("/companies/999")).andDo(print())
//                .andExpect(status().isNotFound());
//
//
////        verify(this.firmeService, times(1)).getOneById(1L);
//    }
//
//    @Test
//    @DisplayName("GIVEN company record does not exist in database, WHEN new company record is created, THEN new record is created and returned.")
//    void testCreate() throws Exception {
//        final List<FirmeEntity> expectedList = MockEntityDataValues.givenFirmeDataRecords();
//        when(this.firmeService.createFirma(expectedList.get(0)))
//                .thenReturn(expectedList.get(0));
//        RestAssuredMockMvc.given().auth().none()
//                .param("1L", "id", "oibFirme", "45485474525", "nazivFirme", "Prime Software")
//                .when()
//                .post("/companies")
//                .getContentType()
//                .then()
//                .statusCode(200);
//        this.mockMvc.perform(post("/companies")
////                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(MockDtoDataValues.FIRMA_PRIME_SOFTWARE))
//                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated());

//    }
//
//    @Disabled
//    @DisplayName("GIVEN company record exists in database, WHEN new booking record is created, THEN bad request error message is returned.")
//    void testCreateExisting() throws Exception {
//        when(firmeRepository.checkIfExistsAllByOibAndIdNotEquals(Mockito.any(FirmeEntity.class))).thenReturn(MockEntityDataValues.givenFirmeDataRecords());
//
//        mockMvc.perform(post("/companies")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(MockDtoDataValues.FIRMA_PRIME_SOFTWARE))
//                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void testUpdate() throws Exception {
//        when(firmeRepository.checkIfExistsAllByOibAndIdNotEquals(Mockito.any(FirmeEntity.class))).thenReturn(Collections.emptyList());
//
//        mockMvc.perform(put("/companies/1")
//                        .content(objectMapper.writeValueAsString(MockDtoDataValues.FIRMA_PRIME_SOFTWARE))
//                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
////
//                .andExpect(jsonPath("$.oibFirme", is(MockDtoDataValues.FIRMA_PRIME_SOFTWARE.getOibFirme())))
//                .andExpect(jsonPath("$.nazivFirme", is(MockDtoDataValues.FIRMA_PRIME_SOFTWARE.getNazivFirme())));
//    }
//
//    @Test
//    @Order(99)
//    @DisplayName("GIVEN booking record either exist or not, WHEN a single booking record is deleted, THEN then OK status is returned.")
//    void testDelete() throws Exception {
//
//        mockMvc.perform(delete("companies/1"))
//                .andExpect(status().isOk());
//    }


}

