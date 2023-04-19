package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ms_account.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    
}
