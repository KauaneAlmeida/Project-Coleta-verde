package br.com.fiap.coletaverde.exception;

public class ColetaNaoEncontradaException extends RuntimeException {
    public ColetaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
