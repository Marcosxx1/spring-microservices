package com.loans.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc*/
class LoansControllerImplTest {

    /* @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoansService loansService;

    @MockBean
    private MessageSourceAccessor messageSourceAccessor;

    @MockBean
    private Environment environment;  // Mock the Environment

    private MockEnvironment mockEnvironment;

    @BeforeEach
    public void setUp() {
        Mockito.reset(loansService);
        MockitoAnnotations.openMocks(this);

        // Mock environment properties
        mockEnvironment = new MockEnvironment();
        mockEnvironment.setProperty("build.version", "1.0.0"); // Required property
        mockEnvironment.setProperty("spring.validation.enabled", "true"); // Ensure validation is enabled
        mockEnvironment.setProperty("spring.messages.basename", "messages"); // Example for MessageSource

        // Set the mock environment in the context
        ReflectionTestUtils.setField(environment, "target", mockEnvironment);
        ReflectionTestUtils.setField(ExceptionMessageUtils.class, "staticMessageSourceAccessor", messageSourceAccessor);
    }


    @Test
    void testCreateLoan_ValidRequest_ReturnsCreatedStatus() throws Exception {
        String mobileNumber = "1234567890";

        mockMvc.perform(post("/api/create").param("mobileNumber", mobileNumber).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void testCreateLoan_InvalidMobileNumber_ThrowsValidationException() throws Exception {
        String invalidMobileNumber = "123";

        */
    /* String[] invalidParams = {"mobileNumber", "loanNumber", "loanType", "totalLoan", "amountPaid", "outstandingAmount"};

    var resultActions =*/
    /* mockMvc.perform(post("/api/create")
                    .param("mobileNumber", invalidMobileNumber)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    */
    /*
    for (String invalidParam : invalidParams) {
        resultActions.andExpect(jsonPath("$.params", hasKey(invalidParam)));
    }*/
    /*
    }

    @Test
    void testFetchLoanDetails_ValidRequest_ReturnsOkStatus() throws Exception {
        String mobileNumber = "1234567890";
        LoansDto loansDto = LoansDto.builder()
                .mobileNumber(mobileNumber)
                .loanNumber("321654")
                .loanType("233232")
                .totalLoan(1000)
                .amountPaid(1000)
                .outstandingAmount(10000)
                .build();

        Mockito.when(loansService.fetchLoan(mobileNumber)).thenReturn(loansDto);

        mockMvc.perform(get("/api/fetch").param("mobileNumber", mobileNumber).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(loansDto)));
    }

    @Test
    void testUpdateLoanDetails_ValidRequest_ReturnsOkStatus() throws Exception {
        LoansDto loansDto = LoansDto.builder()
                .mobileNumber("6701234567")
                .loanNumber("123456789521")
                .loanType("233232")
                .totalLoan(1000)
                .amountPaid(1000)
                .outstandingAmount(10000)
                .build();

        Mockito.when(loansService.updateLoan(loansDto)).thenReturn(true);

        mockMvc.perform(put("/api/update")
                        .content(objectMapper.writeValueAsString(loansDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteLoanDetails_ValidRequest_ReturnsOkStatus() throws Exception {
        String mobileNumber = "1234567890";

        Mockito.when(loansService.deleteLoan(mobileNumber)).thenReturn(true);

        mockMvc.perform(delete("/api/delete")
                        .param("mobileNumber", mobileNumber)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        //  .andExpect(jsonPath("$.status").value(HttpStatus.OK.name()));
        //  .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(HttpStatus.OK.name()));
    }

    @Test
    void testDeleteLoanDetails_InvalidRequest_ReturnsExpectationFailedStatus() throws Exception {
        String mobileNumber = "1234567890";

        Mockito.when(loansService.deleteLoan(mobileNumber)).thenReturn(false);

        mockMvc.perform(delete("/api/delete")
                        .param("mobileNumber", mobileNumber)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isExpectationFailed()) */
    /*
    .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(HttpStatus.EXPECTATION_FAILED.name()))*/
    /*;
    }*/
}
