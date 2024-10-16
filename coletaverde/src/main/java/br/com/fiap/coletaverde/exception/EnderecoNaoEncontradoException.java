package br.com.fiap.coletaverde.exception;

public class EnderecoNaoEncontradoException extends RuntimeException{
    public EnderecoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
