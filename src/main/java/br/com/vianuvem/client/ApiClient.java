package br.com.vianuvem.client;

import br.com.vianuvem.dto.FilmeEntradaDTO;
import br.com.vianuvem.dto.PilotoDTO;
import br.com.vianuvem.dto.PlanetaEntradaDTO;
import br.com.vianuvem.model.NaveEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Classe responsavel pelas requisiçoes com o SWAPI
 */
@FeignClient(name = "apiclient", url = "https://swapi.dev/api/")
public interface ApiClient {

    @GetMapping("/planets/{planetId}/")
    ResponseEntity<PlanetaEntradaDTO> getPlanet(@PathVariable("planetId") String planetId);

    @GetMapping("/films/{filmId}/")
    ResponseEntity<FilmeEntradaDTO> getFilm(@PathVariable("filmId") String filmId);

    @GetMapping("/starships/{starshipId}/")
    ResponseEntity<NaveEntity> getStarShip(@PathVariable("starshipId") String starshipId);

    @GetMapping("/people/{peopleId}/")
    ResponseEntity<PilotoDTO> getPeople(@PathVariable("peopleId") String peopleId);


}
