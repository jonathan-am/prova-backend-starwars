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

    /**
     * Metodo responsavel por salvar o objeto no banco.
     *
     * @param entity Entidade que sera insertada no banco
     * @return o valor copia que foi insertado no banco
     */
    public PlanetaEntity create(PlanetaEntity entity) {
        return repo.save(entity);
    }

    /**
     * Metodo que busca todos os registros de objetos no banco
     *
     * @return um Array de objetos
     */
    public List<PlanetaEntity> getPlanets() {
        return repo.findAll();
    }

    /**
     * Metodo responsavel por buscar o Objeto a partir de uma chave primaria, String name
     *
     * @param name Parametro usado para busca
     * @return o Objeto com a chave de busca, ou lança uma exceção { NãoEncontradoException.class}
     */
    @Cacheable(value = "planets_name", key = "#name")
    public PlanetaEntity getByName(String name) {
        return repo.findByName(name).orElseThrow(() -> new NaoEncontradoException("Planeta não encontrado no banco de dados! Tente usando o IDentificador."));
    }

    /**
     * Metodo responsavel por buscar o Objeto a partir de uma chave primaria, int Id
     *
     * @param id Parametro usado para busca
     * @return o Objeto com a chave de busca, ou lança uma exceção { NãoEncontradoException.class}
     */
    @Cacheable(value = "planets_id", key = "#id")
    public PlanetaEntity getByIdentifier(int id) {
        return repo.findByIdentifier(id).orElseThrow(() -> new NaoEncontradoException("Planeta não encontrado no banco de dados."));
    }

    /**
     * Metodo que deleta o valor do banco de dados, e limpa o Cache
     *
     * @param entity que sera removida do banco de dados.
     */
    public void delete(PlanetaEntity entity) {
        evictByName(entity.getName());
        evictByIdentifier(entity.getIdentifier());
        repo.delete(entity);
    }

    /**
     * Metodo que limpa o cache pelas chaves Name
     *
     * @param cacheKey Chave de busca para deletar do cache
     */
    public void evictByName(String cacheKey) {
        if (cacheKey != null)
            cacheManager.getCache("planets_name").evict(cacheKey);
    }

    /**
     * Metodo que limpa o cache pelas chaves Identifier
     *
     * @param cacheKey Chave de busca para deletar do cache
     */
    public void evictByIdentifier(int cacheKey) {
        cacheManager.getCache("planets_id").evict(cacheKey);
    }

}
