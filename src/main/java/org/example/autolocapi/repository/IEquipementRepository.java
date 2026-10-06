package org.example.autolocapi.repository;

import org.example.autolocapi.domain.Equipement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEquipementRepository extends JpaRepository<Equipement, Long> {
}