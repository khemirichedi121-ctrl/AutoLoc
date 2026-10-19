package org.example.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.example.autolocapi.domain.Equipement;
import org.example.autolocapi.exception.ResourceNotFoundException;
import org.example.autolocapi.repository.IEquipementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipementServiceImpl implements IEquipementService {

    private final IEquipementRepository equipementRepository;

    @Override
    public Equipement create(Equipement equipement) {
        // 1. Garde sur l'identifiant
        if (equipement.getIdEquipement() != null) {
            throw new IllegalArgumentException("Un nouvel équipement ne doit pas avoir d'identifiant");
        }
        // 2. Règle de gestion : libellé obligatoire
        if (equipement.getLibelle() == null || equipement.getLibelle().trim().isEmpty()) {
            throw new IllegalArgumentException("Le libellé de l'équipement est obligatoire");
        }

        return equipementRepository.save(equipement);
    }

    @Override
    public Equipement findById(Long id) {
        return equipementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipement", id));
    }

    @Override
    public List<Equipement> findAll() {
        return equipementRepository.findAll();
    }

    @Override
    public Equipement update(Long id, Equipement equipement) {
        Equipement existant = findById(id);

        // Règle de gestion
        if (equipement.getLibelle() == null || equipement.getLibelle().trim().isEmpty()) {
            throw new IllegalArgumentException("Le libellé de l'équipement est obligatoire");
        }

        // Recopie UNIQUEMENT des attributs modifiables
        // (on ne touche pas à la liste des véhicules)
        existant.setLibelle(equipement.getLibelle());

        return equipementRepository.save(existant);
    }

    @Override
    public void deleteById(Long id) {
        if (!equipementRepository.existsById(id)) {
            throw new ResourceNotFoundException("Equipement", id);
        }
        equipementRepository.deleteById(id);
    }
}