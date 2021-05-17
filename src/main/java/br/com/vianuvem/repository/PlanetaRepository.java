package br.com.vianuvem.repository;

import br.com.vianuvem.model.PlanetaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PlanetaRepository extends MongoRepository<PlanetaEntity, String> {

    /**
     * Metodo que busca no banco de dados, com String name como chave de busca.
     *
     * @param name Chave de busca
     * @return Entidade que encontrar pela chave de busca
     */
    Optional<PlanetaEntity> findByName(String name);

    /**
     * Metodo que busca no banco de dados, com Int identifier como chave de busca.
     *
     * @param identifier Chave de busca
     * @return Entidade que encontrar pela chave de busca
     */
    Optional<PlanetaEntity> findByIdentifier(int identifier);

}
