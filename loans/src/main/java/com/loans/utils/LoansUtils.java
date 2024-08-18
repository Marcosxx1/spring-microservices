package com.loans.utils;

import com.loans.constants.LoansConstants;
import com.loans.domain.entity.Loans;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LoansUtils {

    private Loans createNewLoan(String mobileNumber, String loanType) {
        String loanNumber = UUID.randomUUID().toString();
        return Loans.builder()
                .loanNumber(loanNumber)
                .mobileNumber(mobileNumber)
                .loanType(loanType)
                .totalLoan(LoansConstants.NEW_LOAN_LIMIT)
                .amountPaid(0)
                .outstandingAmount(LoansConstants.NEW_LOAN_LIMIT)
                .build();
    }

    public Loans returnNewLoan(String mobileNumber, String loanType) {
        return createNewLoan(mobileNumber, loanType);
    }
}
