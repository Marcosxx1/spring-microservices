package com.loans.service;

import com.loans.commom.exception.handler.ExceptionMessageUtils;
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
    private final LoansUtils loansUtils;

    @Override
    public void createLoan(String mobileNumber) {
        loansRepository.findByMobileNumber(mobileNumber).ifPresent(c -> {
            throw ExceptionMessageUtils.loanAlreadyExistsException(mobileNumber);
        });
        loansRepository.save(loansUtils.returnNewLoan(mobileNumber, LoansConstants.HOME_LOAN));
    }

    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Loans loan = loansRepository
                .findByMobileNumber(mobileNumber)
                .orElseThrow(() -> ExceptionMessageUtils.resourceNotFoundException("LOAN", mobileNumber));

        return LoansMapper.mapToLoansDto(loan);
    }

    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Loans loanFound = loansRepository
                .findByMobileNumber(loansDto.getMobileNumber())
                .orElseThrow(() -> ExceptionMessageUtils.resourceNotFoundException("LOAN", loansDto.getMobileNumber()));

        var loanToUpdate = LoansMapper.mapToLoans(loansDto, loanFound);
        Loans updatedLoan = loansRepository.save(loanToUpdate);

        return updatedLoan.equals(loanToUpdate);
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        var loans = loansRepository
                .findByMobileNumber(mobileNumber)
                .orElseThrow(() -> ExceptionMessageUtils.resourceNotFoundException("LOAN", mobileNumber));
        loansRepository.deleteById(loans.getLoanId());

        return loansRepository.findById(loans.getLoanId()).isEmpty();
    }
}
