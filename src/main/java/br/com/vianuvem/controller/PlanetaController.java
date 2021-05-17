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

@RestController
public class PlanetaController {

    @Autowired
    private PlanetaBusiness business;
    @Autowired
    private PlanetaService service;

    @PostMapping("/planet")
    @ResponseBody
    public PlanetaEntity createPlanet(@RequestBody PlanetaEntity entity) {
        if (entity.getIdentifier() != 0) {
            try {
                if (service.getByIdentifier(entity.getIdentifier()) == null) {
                    return service.create(entity);
                } else {
                    throw new CreateErrorException("Erro ao criar o planeta. Verifique se já existe um Planeta com este `Identificador`.");
                }
            } catch (NaoEncontradoException e) {
                return service.create(entity);
            }
        } else {
            throw new CreateErrorException("Erro ao criar o planeta. Verifique se o parametro `identifier` não é null, ou valor 0!");
        }
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
        return business.getPlanetByName(name);
    }

    @GetMapping("/planet/v2/{identifier}")
    @ResponseBody
    public PlanetaEntity getPlanetByIdentifier(@PathVariable int identifier) {
        return business.getPlanetByIdentifier(identifier);
    }

}
