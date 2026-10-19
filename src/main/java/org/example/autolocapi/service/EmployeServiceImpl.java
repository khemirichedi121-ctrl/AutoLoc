package org.example.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.example.autolocapi.domain.Employe;
import org.example.autolocapi.exception.ResourceNotFoundException;
import org.example.autolocapi.repository.IEmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements IEmployeService {

    // ⚠️ Attention : ton repository s'appelle IEmployeeRepository (avec un 'e')
    private final IEmployeeRepository employeRepository;

    @Override
    public Employe create(Employe employe) {
        // 1. Garde sur l'identifiant
        if (employe.getIdEmploye() != null) {
            throw new IllegalArgumentException("Un nouvel employé ne doit pas avoir d'identifiant");
        }
        // 2. Règle de gestion : nom et prenom obligatoires
        if (employe.getNom() == null || employe.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de l'employé est obligatoire");
        }
        if (employe.getPrenom() == null || employe.getPrenom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le prénom de l'employé est obligatoire");
        }

        return employeRepository.save(employe);
    }

    @Override
    public Employe findById(Long id) {
        return employeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employe", id));
    }

    @Override
    public List<Employe> findAll() {
        return employeRepository.findAll();
    }

    @Override
    public Employe update(Long id, Employe employe) {
        Employe existant = findById(id);

        // Règle de gestion
        if (employe.getNom() == null || employe.getNom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom de l'employé est obligatoire");
        }
        if (employe.getPrenom() == null || employe.getPrenom().trim().isEmpty()) {
            throw new IllegalArgumentException("Le prénom de l'employé est obligatoire");
        }

        // Recopie des attributs modifiables
        existant.setNom(employe.getNom());
        existant.setPrenom(employe.getPrenom());
        existant.setRole(employe.getRole());

        // On met à jour l'agence si elle est fournie (sans toucher à son ID)
        if (employe.getAgence() != null) {
            existant.setAgence(employe.getAgence());
        }

        return employeRepository.save(existant);
    }

    @Override
    public void deleteById(Long id) {
        if (!employeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employe", id);
        }
        employeRepository.deleteById(id);
    }
}