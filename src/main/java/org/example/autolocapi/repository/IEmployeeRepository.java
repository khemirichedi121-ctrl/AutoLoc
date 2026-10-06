package org.example.autolocapi.repository;

import org.example.autolocapi.domain.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepository extends JpaRepository<Employe, Long> {
}