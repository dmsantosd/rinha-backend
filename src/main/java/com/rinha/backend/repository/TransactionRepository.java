package com.rinha.backend.repository;

import com.rinha.backend.entity.Transaction;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<List<Transaction>> findByClientIdOrderByCreatedAtDesc(final Long clientId, final Limit limit);
}
