package com.loans.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.loans.commom.exception.LoanAlreadyExistsException;
import com.loans.commom.exception.ResourceNotFoundException;
import com.loans.commom.exception.handler.ExceptionMessageUtils;
import com.loans.constants.LoansConstants;
import com.loans.domain.dto.LoansDto;
import com.loans.domain.entity.Loans;
import com.loans.mapper.LoansMapper;
import com.loans.repository.LoansRepository;
import com.loans.utils.LoansUtils;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

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
    public void testCreateLoan_whenLoanAlreadyExists_thenReturnResourceAlreadyExistsException() {
        String mobileNumber = "321654987654";

        when(loansRepository.findByMobileNumber(mobileNumber)).thenReturn(Optional.of(new Loans()));

         assertThrows(LoanAlreadyExistsException.class, () -> {
            loansService.createLoan(mobileNumber);
        });

     }

    @Test
    public void testCreateLoan_whenUserDoesNotExists_thenSaveNewLoan() {
        String mobileNumber = "321654987654";
        when(loansRepository.findByMobileNumber(mobileNumber)).thenReturn(Optional.empty());
        loansService.createLoan(mobileNumber);
        verify(loansRepository, times(1)).save(any(Loans.class));
    }

    @Test
    public void testFetchLoan_whenLoanExists_thenReturnLoan() {
        String mobileNumber = "321654987654";
        Loans loan = new Loans();
        loan.setMobileNumber(mobileNumber);

        when(loansRepository.findByMobileNumber(mobileNumber)).thenReturn(Optional.of(loan));

        LoansDto result = loansService.fetchLoan(mobileNumber);

        assert (result.getMobileNumber()).equals(mobileNumber);
    }

    @Test
    public void testUpdateLoan_whenLoanDoesNotExists_thenThrowResourceNotFoundException() {
        String mobileNumber = "321654987654";

        LoansDto loansDto = LoansDto.builder()
                .mobileNumber(mobileNumber)
                .loanNumber("321654")
                .loanType("233232")
                .totalLoan(1000)
                .amountPaid(1000)
                .outstandingAmount(10000)
                .build();

        when(loansRepository.findByMobileNumber(mobileNumber)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            loansService.updateLoan(loansDto);
        });
    }

    @Test
    public void testUpdateLoan_whenLoanExists_thenUpdateLoan() {
        String mobileNumber = "321654987654";

        LoansDto loansDto = LoansDto.builder()
                .mobileNumber(mobileNumber)
                .loanNumber("321654")
                .loanType("233232")
                .totalLoan(1000)
                .amountPaid(1000)
                .outstandingAmount(10000)
                .build();
        loansDto.setMobileNumber(mobileNumber);

        Loans loan = new Loans();
        loan.setMobileNumber(mobileNumber);

        when(loansRepository.findByMobileNumber(mobileNumber)).thenReturn(Optional.of(loan));
        when(loansRepository.save(any(Loans.class))).thenReturn(loan);

        boolean result = loansService.updateLoan(loansDto);

        verify(loansRepository, times(1)).save(any(Loans.class));
        verify(loansRepository, times(1)).findByMobileNumber(mobileNumber);
        verify(messageSourceAccessor, times(0)).getMessage(anyString(), any(Object[].class));

        assertTrue(result);
    }

    @Test
    public void testDeleteLoan_whenLoanDoesNotExist_thenThrowResourceNotFoundException() {
        String mobileNumber = "321654987654";

        when(loansRepository.findByMobileNumber(mobileNumber)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            loansService.fetchLoan(mobileNumber);
        });
     }

    @Test
    public void testDeleteLoan_whenLoanExists_thenDeleteLoan() {
        String mobileNumber = "321654987654";

        Loans loan = new Loans();
        loan.setLoanId(1L);
        loan.setMobileNumber(mobileNumber);

        when(loansRepository.findByMobileNumber(mobileNumber)).thenReturn(Optional.of(loan));

        doNothing().when(loansRepository).deleteById(loan.getLoanId());

        when(loansRepository.findById(loan.getLoanId())).thenReturn(Optional.empty());

        boolean result = loansService.deleteLoan(mobileNumber);

        verify(loansRepository, times(1)).findByMobileNumber(mobileNumber);
        verify(loansRepository, times(1)).deleteById(loan.getLoanId());
        verify(loansRepository, times(1)).findById(loan.getLoanId());

        assertTrue(result);
    }
    @Test
    void fetchLoan_whenLoanExists_returnsLoanDto() {
        String mobileNumber = "1234567890";
        Loans mockLoan = new Loans();
        LoansDto mockLoansDto =  LoansDto.builder().build();

        when(loansRepository.findByMobileNumber(mobileNumber)).thenReturn(Optional.of(mockLoan));

        try (MockedStatic<LoansMapper> mockedStatic = mockStatic(LoansMapper.class)) {
            mockedStatic.when(() -> LoansMapper.mapToLoansDto(mockLoan)).thenReturn(mockLoansDto);


            LoansDto result = loansService.fetchLoan(mobileNumber);


            assertNotNull(result);
            assertEquals(mockLoansDto, result);
            verify(loansRepository).findByMobileNumber(mobileNumber);
            mockedStatic.verify(() -> LoansMapper.mapToLoansDto(mockLoan));
        }
    }

    @Test
    public void testRegisterUser_whenLoanNotFound_thenThrowResourceNotFoundException() {
        String mobileNumber = "1234567890";

        when(loansRepository.findByMobileNumber(mobileNumber)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            loansService.fetchLoan(mobileNumber);
        });
    }

}
