package br.com.fiap.coletaverde.exception;

public class CaminhaoNaoEncontradoException extends RuntimeException {
    public CaminhaoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
