package br.com.fiap.coletaverde.exception;

public class MoradorNaoEncontradoException extends RuntimeException{
    public MoradorNaoEncontradoException(String mensagem ){
        super(mensagem);
    }
}
