package com.loans.repository;

import com.loans.domain.entity.Loans;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoansRepository extends JpaRepository<Loans, Long> {

    Optional<Loans> findByMobileNumber(String mobileNumber);
}
