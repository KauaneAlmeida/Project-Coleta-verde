package br.com.fiap.coletaverde.exception;

public class NotificacaoNaoEncontradaException extends RuntimeException{
    public NotificacaoNaoEncontradaException(String mensagem){
        super(mensagem);
    }
}
