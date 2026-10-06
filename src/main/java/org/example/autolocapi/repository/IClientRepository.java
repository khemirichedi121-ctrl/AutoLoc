package org.example.autolocapi.repository;

import org.example.autolocapi.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientRepository extends JpaRepository<Client, Long> {
}