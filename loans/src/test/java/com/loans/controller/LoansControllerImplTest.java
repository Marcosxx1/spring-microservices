package com.loans.controller;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loans.commom.exception.handler.ExceptionMessageUtils;
import com.loans.domain.dto.LoansDto;
import com.loans.service.LoansService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class LoansControllerImplTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoansService loansService;

    @MockBean
    private MessageSourceAccessor messageSourceAccessor;

    @BeforeEach
    public void setUp() {
        Mockito.reset(loansService);
        MockitoAnnotations.openMocks(this);
        messageSourceAccessor = mock(MessageSourceAccessor.class);
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

        /* String[] invalidParams = {"mobileNumber", "loanNumber", "loanType", "totalLoan", "amountPaid", "outstandingAmount"};

        var resultActions =*/ mockMvc.perform(post("/api/create")
                        .param("mobileNumber", invalidMobileNumber)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        /*
        for (String invalidParam : invalidParams) {
            resultActions.andExpect(jsonPath("$.params", hasKey(invalidParam)));
        }*/
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
                .andExpect(status().isExpectationFailed()) /*
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(HttpStatus.EXPECTATION_FAILED.name()))*/;
    }
}
