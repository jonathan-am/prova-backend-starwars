package br.com.vianuvem.dto;

import br.com.vianuvem.model.FilmeEntity;

import java.util.List;

/**
 * DTO com dados de entrada pela API ou client response
 */
public class PlanetaEntradaDTO {

    private String name;
    private String climate;
    private String terrain;
    private List<String> films;

    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

}
