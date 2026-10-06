package org.example.autolocapi.repository;

import org.example.autolocapi.domain.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContratRepository extends JpaRepository<Contrat, Long> {
}