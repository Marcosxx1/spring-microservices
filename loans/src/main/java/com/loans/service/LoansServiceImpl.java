package com.loans.service;

import com.loans.commom.exception.ExceptionMessageUtils;
import com.loans.constants.LoansConstants;
import com.loans.domain.dto.LoansDto;
import com.loans.domain.entity.Loans;
import com.loans.mapper.LoansMapper;
import com.loans.repository.LoansRepository;
import com.loans.utils.LoansUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoansServiceImpl implements LoansService {

    private final LoansRepository loansRepository;

    @Override
    public void createLoan(String mobileNumber) {
        loansRepository
                .findByMobileNumber(mobileNumber)
                .orElseThrow(
                        () -> ExceptionMessageUtils.resourceNotFoundException("Loan", "Mobile number", mobileNumber));

        loansRepository.save(LoansUtils.createNewLoan(mobileNumber, LoansConstants.HOME_LOAN));
    }

    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Loans loans = loansRepository
                .findByMobileNumber(mobileNumber)
                .orElseThrow(
                        () -> ExceptionMessageUtils.resourceNotFoundException("Loan", "Mobile number", mobileNumber));

        return LoansMapper.mapToLoansDto(loans);
    }

    @Override
    public boolean updateLoan(LoansDto loansDto) {
        return false;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        return false;
    }
}
