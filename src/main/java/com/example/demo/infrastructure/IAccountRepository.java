package com.example.demo.infrastructure;

import com.example.demo.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IAccountRepository extends JpaRepository<Account, Long> {
}
