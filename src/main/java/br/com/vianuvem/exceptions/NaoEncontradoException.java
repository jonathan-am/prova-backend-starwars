package br.com.vianuvem.exceptions;

public class NaoEncontradoException extends RuntimeException{

    public NaoEncontradoException(String msg) {
        super(msg);
    }
}
