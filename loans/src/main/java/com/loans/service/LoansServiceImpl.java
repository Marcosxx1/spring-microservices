package com.loans.service;

import com.loans.commom.exception.ExceptionMessageUtils;
import com.loans.constants.LoansConstants;
import com.loans.domain.dto.LoansDto;
import com.loans.domain.entity.Loans;
import com.loans.mapper.LoansMapper;
import com.loans.repository.LoansRepository;
import com.loans.utils.LoansUtils;
import java.util.Optional;
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
        Loans loans =
                findOrThrow(loansRepository.findByMobileNumber(mobileNumber), "Loan", "mobileNumber", mobileNumber);

        return LoansMapper.mapToLoansDto(loans);
    }

    @Override
    public boolean updateLoan(LoansDto loansDto) {
        Loans loanFound = findOrThrow(
                loansRepository.findByMobileNumber(loansDto.getMobileNumber()),
                "Loan",
                "mobileNumber",
                loansDto.getMobileNumber());

        var loanToUpdate = LoansMapper.mapToLoans(loansDto, loanFound);
        Loans updatedLoan = loansRepository.save(loanToUpdate);

        return updatedLoan.equals(loanToUpdate);
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        var loans = findOrThrow(loansRepository.findByMobileNumber(mobileNumber), "Loan", "mobileNumber", mobileNumber);
        loansRepository.deleteById(loans.getLoanId());

        return loansRepository.findById(loans.getLoanId()).isEmpty();
    }

    private <T> T findOrThrow(Optional<T> optional, String entityName, String fieldName, String fieldValue) {
        return optional.orElseThrow(
                () -> ExceptionMessageUtils.resourceNotFoundException(entityName, fieldName, fieldValue));
    }
}
