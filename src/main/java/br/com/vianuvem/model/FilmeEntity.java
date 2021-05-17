package br.com.vianuvem.model;


import java.util.List;

public class FilmeEntity {

    private String titulo;
    private List<NaveEntity> naves;

    public List<NaveEntity> getNaves() {
        return naves;
    }

    public void setNaves(List<NaveEntity> naves) {
        this.naves = naves;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}