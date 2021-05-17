package br.com.vianuvem.dto;

import java.util.List;

/**
 * DTO com dados de entrada pela API ou client response
 */
public class FilmeEntradaDTO {

    private String title;
    private List<String> starships;

    public List<String> getStarships() {
        return starships;
    }

    public void setStarships(List<String> starships) {
        this.starships = starships;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
