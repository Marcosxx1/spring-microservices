package com.loans.service;

import com.loans.commom.exception.ExceptionMessageUtils;
import com.loans.constants.LoansConstants;
import com.loans.domain.entity.Loans;
import com.loans.repository.LoansRepository;
import com.loans.utils.LoansUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {LoansServiceImpl.class, LoansUtils.class})
public class LoansServiceTest {

    @Autowired
    private LoansService loansService;

    @MockBean
    private LoansRepository loansRepository;

    @MockBean
    private MessageSourceAccessor messageSourceAccessor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        messageSourceAccessor = mock(MessageSourceAccessor.class);
        ReflectionTestUtils.setField(ExceptionMessageUtils.class, "staticMessageSourceAccessor", messageSourceAccessor);
    }

    @Test
    public void testCreate_whenLoanAlreadyExists_thenReturnResourceAlreadyExistsException() {
        String mobileNumber = "321654987654";
        String errorMessage = "Loan already exists";

        when(loansRepository.findByMobileNumber(mobileNumber)).thenReturn(Optional.of(new Loans()));
        when(messageSourceAccessor.getMessage(LoansConstants.RESOURCE_ALREADY_EXISTS)).thenReturn(errorMessage);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            loansService.createLoan(mobileNumber);
        });

        assert(thrown.getMessage()).equals(errorMessage);
    }
}
