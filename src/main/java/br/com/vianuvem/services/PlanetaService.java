package br.com.vianuvem.services;

import br.com.vianuvem.exceptions.NaoEncontradoException;
import br.com.vianuvem.model.PlanetaEntity;
import br.com.vianuvem.repository.PlanetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanetaService {

    @Autowired
    private PlanetaRepository repo;
    @Autowired
    CacheManager cacheManager;

    public PlanetaEntity create(PlanetaEntity entity) {
        return repo.save(entity);
    }

    public List<PlanetaEntity> getPlanets() {
        return repo.findAll();
    }

    @Cacheable(value = "planets_name", key = "#name")
    public PlanetaEntity getByName(String name) {
        return repo.findByName(name).orElseThrow(() -> new NaoEncontradoException("Planeta não encontrado no banco de dados! Tente usando o IDentificador."));
    }

    @Cacheable(value = "planets_id", key = "#id")
    public PlanetaEntity getByIdentifier(int id) {
        return repo.findByIdentifier(id).orElseThrow(() -> new NaoEncontradoException("Planeta não encontrado no banco de dados."));
    }

    public PlanetaEntity update(PlanetaEntity entity) {
        evictByName(entity.getName());
        evictByIdentifier(entity.getIdentifier());
        return create(entity);
    }

    public void delete(PlanetaEntity entity) {
        evictByName(entity.getName());
        evictByIdentifier(entity.getIdentifier());
        repo.delete(entity);
    }

    public void evictByName(String cacheKey) {
        if (cacheKey != null)
            cacheManager.getCache("planets_name").evict(cacheKey);
    }

    public void evictByIdentifier(int cacheKey) {
        cacheManager.getCache("planets_id").evict(cacheKey);
    }

}
