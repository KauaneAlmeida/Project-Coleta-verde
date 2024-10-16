package br.com.fiap.coletaverde.exception;

public class TipoResiduoNaoEncontradoException extends RuntimeException{
    public TipoResiduoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
