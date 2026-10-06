package org.example.autolocapi.repository;

import org.example.autolocapi.domain.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaiementRepository extends JpaRepository<Paiement, Long> {
}