package br.com.vianuvem.business;

import br.com.vianuvem.client.ApiClient;
import br.com.vianuvem.dto.FilmeEntradaDTO;
import br.com.vianuvem.dto.PilotoDTO;
import br.com.vianuvem.dto.PlanetaEntradaDTO;
import br.com.vianuvem.exceptions.CreateErrorException;
import br.com.vianuvem.exceptions.NaoEncontradoException;
import br.com.vianuvem.model.FilmeEntity;
import br.com.vianuvem.model.NaveEntity;
import br.com.vianuvem.model.PlanetaEntity;
import br.com.vianuvem.services.PlanetaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlanetaBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlanetaBusiness.class);

    @Autowired
    private ApiClient client;
    @Autowired
    private PlanetaService service;

    /**
     * Busca o Objeto no banco de dados, caso não exista,
     * consulta na API e inserta no banco.
     *
     * @param id Chave que sera usada para busca
     * @return o valor Objeto correspondente da busca
     */
    public PlanetaEntity getPlanetByIdentifier(int id) {
        try {
            PlanetaEntity entidade = service.getByIdentifier(id);
            return entidade;
        } catch (NaoEncontradoException ex) {
            LOGGER.warn(" Não foi possivel encontrar o planeta no banco de dados, consultando o SWAPI...");
            ResponseEntity<PlanetaEntradaDTO> teste;
            try {
                teste = client.getPlanet(String.valueOf(id));
            } catch (Exception exx) {
                throw new NaoEncontradoException("Não foi possivel encontrar um planeta compativel com este IDenficador!");
            }
            if (teste.getStatusCodeValue() == 200) {
                PlanetaEntradaDTO planeta = teste.getBody();
                PlanetaEntity entidade = new PlanetaEntity();

                List<FilmeEntity> filmes = new ArrayList<>();
                try {
                    planeta.getFilms().forEach((value) -> {
                        filmes.add(getFilm(value.split("/")[5]));
                    });
                } catch (Exception e) {
                    LOGGER.error("getPlanet(String id) - Ocorreu um erro na consulta pelos filmes.");
                }

                entidade.setIdentifier(id);
                entidade.setFilms(filmes);
                entidade.setName(planeta.getName());
                entidade.setClimate(planeta.getClimate());
                entidade.setTerrain(planeta.getTerrain());

                return service.create(entidade);
            } else {
                throw new NaoEncontradoException("Não foi possivel encontrar um planeta compativel com este IDenficador!");
            }
        }
    }

    /**
     * Busca a entidade Film na API, pela chave correspondente.
     *
     * @param id Chave que sera usada para busca
     * @return o valor Objeto correspondente da busca
     */
    public FilmeEntity getFilm(String id) {
        ResponseEntity<FilmeEntradaDTO> teste = client.getFilm(id);
        FilmeEntradaDTO filme = teste.getBody();
        FilmeEntity entidade = new FilmeEntity();

        List<NaveEntity> naves = new ArrayList<>();
        try {
            filme.getStarships().forEach((value) -> {
                naves.add(getStarShip(value.split("/")[5]));
            });
        } catch (Exception e) {
            LOGGER.error("getFilm(String id) - Ocorreu um erro na consulta pelas naves.");
        }
        entidade.setNaves(naves);
        entidade.setTitulo(filme.getTitle());

        return entidade;
    }

    /**
     * Busca a entidade StarShip(Nave) na API, pela chave correspondente.
     *
     * @param id Chave que sera usada para busca
     * @return o valor Objeto correspondente da busca
     */
    public NaveEntity getStarShip(String id) {
        ResponseEntity<NaveEntity> teste = client.getStarShip(id);
        NaveEntity entidade = teste.getBody();

        List<String> pilots = new ArrayList<>();
        try {
            entidade.getPilots().forEach((value) -> {
                pilots.add(getPilot(value.split("/")[5]).getName());
            });
        } catch (Exception e) {
            LOGGER.error("getStarShip(String id) - Ocorreu um erro na consulta pelos pilotos.");
        }
        entidade.setPilots(pilots);
        return entidade;
    }

    /**
     * Busca a entidade Piloto na API, pela chave correspondente.
     *
     * @param id Chave que sera usada para busca
     * @return o valor Objeto correspondente da busca
     */
    public PilotoDTO getPilot(String id) {
        ResponseEntity<PilotoDTO> teste = client.getPeople(id);
        return teste.getBody();
    }

    /**
     * Cria um planeta, seguindo uma serie de validaçoes
     *
     * @param entity Entidade que sera insertada no banco de dados
     * @return um valor copia que foi inserido no banco de dados
     */
    public PlanetaEntity createPlanet(PlanetaEntity entity) {
        if (entity.getIdentifier() != 0) {
            try {
                if (service.getByIdentifier(entity.getIdentifier()) == null) {
                    return service.create(entity);
                } else {
                    throw new CreateErrorException("Erro ao criar o planeta. Verifique se já existe um Planeta com este `Identificador` ou `Nome`.");
                }
            } catch (NaoEncontradoException e) {
                try {
                    if (service.getByName(entity.getName()) == null) {
                        return service.create(entity);
                    } else {
                        throw new CreateErrorException("Erro ao criar o planeta. Verifique se já existe um Planeta com este `Nome`.");
                    }
                } catch (NaoEncontradoException ex) {
                    return service.create(entity);
                }
            }
        } else {
            throw new CreateErrorException("Erro ao criar o planeta. Verifique se o parametro `identifier` não é null, ou valor 0!");
        }
    }
}
