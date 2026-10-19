package org.example.autolocapi.service;

import lombok.RequiredArgsConstructor;
import org.example.autolocapi.domain.Vehicule;
import org.example.autolocapi.exception.ResourceNotFoundException;
import org.example.autolocapi.repository.IVehiculeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculeServiceImpl implements IVehiculeService {

    private final IVehiculeRepository vehiculeRepository;

    @Override
    public Vehicule create(Vehicule vehicule) {
        // 1. Garde sur l'identifiant
        if (vehicule.getIdVehicule() != null) {
            throw new IllegalArgumentException("Un nouveau véhicule ne doit pas avoir d'identifiant");
        }
        // 2. Règle de gestion : tarif >= 0
        validerTarif(vehicule.getTarifJournalier());

        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule findById(Long id) {
        return vehiculeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicule", id));
    }

    @Override
    public List<Vehicule> findAll() {
        return vehiculeRepository.findAll();
    }

    @Override
    public Vehicule update(Long id, Vehicule vehicule) {
        Vehicule existant = findById(id);

        // Règle de gestion
        validerTarif(vehicule.getTarifJournalier());

        // Recopie des attributs modifiables (sans toucher aux listes)
        existant.setImmatriculation(vehicule.getImmatriculation());
        existant.setMarque(vehicule.getMarque());
        existant.setModele(vehicule.getModele());
        existant.setCategorie(vehicule.getCategorie());
        existant.setTarifJournalier(vehicule.getTarifJournalier());
        existant.setStatut(vehicule.getStatut());

        // Mise à jour de l'agence si fournie
        if (vehicule.getAgence() != null) {
            existant.setAgence(vehicule.getAgence());
        }

        return vehiculeRepository.save(existant);
    }

    @Override
    public void deleteById(Long id) {
        if (!vehiculeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Vehicule", id);
        }
        vehiculeRepository.deleteById(id);
    }

    // Méthode privée pour valider le tarif
    private void validerTarif(BigDecimal tarif) {
        if (tarif != null && tarif.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Le tarif journalier ne peut pas être négatif");
        }
    }
}