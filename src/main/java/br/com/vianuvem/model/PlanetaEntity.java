package br.com.vianuvem.model;

import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Entidade Planeta
 */
public class PlanetaEntity {

    @Id
    private String id;
    private int identifier;
    private String name;
    private String climate;
    private String terrain;
    private List<FilmeEntity> films;

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public List<FilmeEntity> getFilms() {
        return films;
    }

    public void setFilms(List<FilmeEntity> films) {
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
