package com.loans.mapper;

import com.loans.domain.dto.LoansDto;
import com.loans.domain.entity.Loans;

public class LoansMapper {

    public static LoansDto mapToLoansDto(Loans loans) {

        return LoansDto.builder()
                .loanNumber(loans.getLoanNumber())
                .loanType(loans.getLoanType())
                .totalLoan(loans.getTotalLoan())
                .amountPaid(loans.getAmountPaid())
                .outstandingAmount(loans.getOutstandingAmount())
                .build();
    }

    public static Loans mapToLoans(LoansDto loansDto, Loans loans) {
        loans.setLoanNumber(loansDto.getLoanNumber());
        loans.setLoanType(loansDto.getLoanType());
        loans.setTotalLoan(loansDto.getTotalLoan());
        loans.setAmountPaid(loansDto.getAmountPaid());
        loans.setOutstandingAmount(loansDto.getOutstandingAmount());

        return loans;
    }
}
