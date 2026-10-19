package org.example.autolocapi.web.controller;

import lombok.RequiredArgsConstructor;
import org.example.autolocapi.domain.Contrat;
import org.example.autolocapi.service.IContratService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contrats")
@RequiredArgsConstructor
public class ContratController {

    private final IContratService contratService;

    @GetMapping
    public List<Contrat> getAll() {
        return contratService.findAll();
    }

    @GetMapping("/{id}")
    public Contrat getById(@PathVariable Long id) {
        return contratService.findById(id);
    }

    @PostMapping
    public Contrat create(@RequestBody Contrat contrat) {
        return contratService.create(contrat);
    }

    @PutMapping("/{id}")
    public Contrat update(@PathVariable Long id, @RequestBody Contrat contrat) {
        return contratService.update(id, contrat);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        contratService.deleteById(id);
    }
}