package br.com.vianuvem.repository;

import br.com.vianuvem.model.PlanetaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PlanetaRepository extends MongoRepository<PlanetaEntity, String> {

    Optional<PlanetaEntity> findByName(String name);
    Optional<PlanetaEntity> findByIdentifier(int identifier);

}
