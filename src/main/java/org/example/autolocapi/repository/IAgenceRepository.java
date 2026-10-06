package org.example.autolocapi.repository;

import org.example.autolocapi.domain.Agence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAgenceRepository extends JpaRepository<Agence, Long> {
}