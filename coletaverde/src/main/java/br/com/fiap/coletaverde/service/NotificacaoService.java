package br.com.fiap.coletaverde.service;

import br.com.fiap.coletaverde.dto.NotificacaoCadastroDTO;
import br.com.fiap.coletaverde.dto.NotificacaoExibicaoDTO;
import br.com.fiap.coletaverde.exception.NotificacaoNaoEncontradaException;
import br.com.fiap.coletaverde.model.Notificacao;
import br.com.fiap.coletaverde.repository.NotificacaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.StreamSupport;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    public NotificacaoExibicaoDTO salvarNotificacao(NotificacaoCadastroDTO notificacaoDTO) {
        Notificacao notificacao = new Notificacao();
        BeanUtils.copyProperties(notificacaoDTO, notificacao);
        Notificacao notificacaoSalvo = notificacaoRepository.save(notificacao);
        return new NotificacaoExibicaoDTO(notificacaoSalvo);
    }

    public NotificacaoExibicaoDTO buscarNotificacaoPorId(Long id) {
        Optional<Notificacao> notificacaoOptional = notificacaoRepository.findById(id);
        if (notificacaoOptional.isPresent()){
            return new NotificacaoExibicaoDTO(notificacaoOptional.get());
        }else{
            throw new NotificacaoNaoEncontradaException("Notificacao não existe!");
        }
    }

    public List<NotificacaoExibicaoDTO> listarTodos() {
        return StreamSupport.stream(notificacaoRepository.findAll().spliterator(), false)
                .map(NotificacaoExibicaoDTO::new)
                .collect(Collectors.toList());
    }

    public void excluirNotificacao(Long id) {
        Optional<Notificacao> notificacaoOptional = notificacaoRepository.findById(id);
        if (notificacaoOptional.isPresent()){
            notificacaoRepository.delete(notificacaoOptional.get());
        }else{
            throw new NotificacaoNaoEncontradaException("Notificacao não encontrado!");
        }
    }

    public NotificacaoExibicaoDTO atualizarNotificacao(NotificacaoCadastroDTO notificacaoDTO) {
        Optional<Notificacao> notificacaoOptional =
                notificacaoRepository.findById(notificacaoDTO.id());
        if (notificacaoOptional.isPresent()){
            Notificacao notificacao = new Notificacao();
            BeanUtils.copyProperties(notificacaoDTO, notificacao);
            return new NotificacaoExibicaoDTO(notificacaoRepository.save(notificacao));

        } else{
            throw new NotificacaoNaoEncontradaException("Notificacao não encontrado!");
        }
    }
}
