package com.loans.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import com.loans.commom.exception.ExceptionMessageUtils;
import com.loans.constants.LoansConstants;
import com.loans.domain.dto.LoansDto;
import com.loans.domain.entity.Loans;
import com.loans.repository.LoansRepository;
import com.loans.utils.LoansUtils;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito.*;
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
        String errorMessage = "Loan already exists";

        when(loansRepository.findByMobileNumber(mobileNumber)).thenReturn(Optional.of(new Loans()));
        when(messageSourceAccessor.getMessage(LoansConstants.RESOURCE_ALREADY_EXISTS))
                .thenReturn(errorMessage);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            loansService.createLoan(mobileNumber);
        });

        assert (thrown.getMessage()).equals(errorMessage);
    }

    @Test
    public void testCreateLoan_whenUserDoesNotExists_thenSaveNewLoan() {
        String mobileNumber = "321654987654";
        when(loansRepository.findByMobileNumber(mobileNumber)).thenReturn(Optional.empty());
        loansService.createLoan(mobileNumber);
        verify(loansRepository, times(1)).save(any(Loans.class));
    }

    @Test
    public void testFetchLoan_whenThereIsNoLoan_thenThrowResourceNotFoundException() {
        String mobileNumber = "321654987654";
        String errorMessage = "Loan not found";

        when(loansRepository.findByMobileNumber(mobileNumber)).thenReturn(Optional.empty());
        when(messageSourceAccessor.getMessage(
                        LoansConstants.RESOURCE_NOT_FOUND_WITH_DATA,
                        new Object[] {"Loan", "mobileNumber", mobileNumber}))
                .thenReturn(errorMessage);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            loansService.fetchLoan(mobileNumber);
        });

        assert (thrown.getMessage()).equals(errorMessage);
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

    /*    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Loans loanFound = findOrThrow(
                loansRepository.findByMobileNumber(loansDto.getMobileNumber()),
                "Loan",
                "mobileNumber",
                loansDto.getMobileNumber());

        var loanToUpdate = LoansMapper.mapToLoans(loansDto, loanFound);
        Loans updatedLoan = loansRepository.save(loanToUpdate);

        return updatedLoan.equals(loanToUpdate);
    }    public static Loans mapToLoans(LoansDto loansDto, Loans loans) {
        loans.setLoanNumber(loansDto.getLoanNumber());
        loans.setLoanType(loansDto.getLoanType());
        loans.setTotalLoan(loansDto.getTotalLoan());
        loans.setAmountPaid(loansDto.getAmountPaid());
        loans.setOutstandingAmount(loansDto.getOutstandingAmount());

        return loans;
    }*/
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
        loansDto.setMobileNumber(mobileNumber);

        String errorMessage = "Loan not found";

        when(loansRepository.findByMobileNumber(mobileNumber)).thenReturn(Optional.empty());
        when(messageSourceAccessor.getMessage(
                        LoansConstants.RESOURCE_NOT_FOUND_WITH_DATA,
                        new Object[] {"Loan", "mobileNumber", mobileNumber}))
                .thenReturn(errorMessage);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            loansService.updateLoan(loansDto);
        });

        assert (thrown.getMessage()).equals(errorMessage);
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
}
