package br.com.fiap.coletaverde.exception;

public class RotaNaoEncontradaException extends RuntimeException{
    public RotaNaoEncontradaException(String mensagem){
        super(mensagem);
    }
}
