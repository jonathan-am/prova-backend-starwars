package br.com.vianuvem.controller;

import br.com.vianuvem.business.PlanetaBusiness;
import br.com.vianuvem.exceptions.CreateErrorException;
import br.com.vianuvem.exceptions.NaoEncontradoException;
import br.com.vianuvem.model.PlanetaEntity;
import br.com.vianuvem.services.PlanetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe Controller da aplicação
 */
@RestController
public class PlanetaController {

    @Autowired
    private PlanetaBusiness business;
    @Autowired
    private PlanetaService service;

    @PostMapping("/planet")
    @ResponseBody
    public PlanetaEntity createPlanet(@RequestBody PlanetaEntity entity) {
        return business.createPlanet(entity);
    }

    @DeleteMapping("/planet/{identifier}")
    @ResponseBody
    public void deletePlanet(@PathVariable int identifier) {
        PlanetaEntity entidade = service.getByIdentifier(identifier);
        service.delete(entidade);
    }

    @GetMapping("/planet")
    @ResponseBody
    public List<PlanetaEntity> getPlanets() {
        return service.getPlanets();
    }

    @GetMapping("/planet/v1/{name}")
    @ResponseBody
    public PlanetaEntity getPlanetByName(@PathVariable String name) {
        return service.getByName(name);
    }

    @GetMapping("/planet/v2/{identifier}")
    @ResponseBody
    public PlanetaEntity getPlanetByIdentifier(@PathVariable int identifier) {
        return business.getPlanetByIdentifier(identifier);
    }

}
