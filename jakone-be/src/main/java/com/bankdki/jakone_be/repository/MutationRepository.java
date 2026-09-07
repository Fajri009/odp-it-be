package com.bankdki.jakone_be.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankdki.jakone_be.entity.Mutation;

@Repository
public interface MutationRepository extends JpaRepository<Mutation, Long> {
    List<Mutation> findByAccountNumberOrderByCreatedAtDesc(String accountNumber);
}