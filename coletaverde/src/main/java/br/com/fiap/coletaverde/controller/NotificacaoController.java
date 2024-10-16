package br.com.fiap.coletaverde.controller;


import br.com.fiap.coletaverde.dto.MoradorCadastroDTO;
import br.com.fiap.coletaverde.dto.MoradorExibicaoDTO;
import br.com.fiap.coletaverde.dto.NotificacaoCadastroDTO;
import br.com.fiap.coletaverde.dto.NotificacaoExibicaoDTO;
import br.com.fiap.coletaverde.model.Notificacao;
import br.com.fiap.coletaverde.service.NotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NotificacaoController {

    @Autowired
    private NotificacaoService notificacaoService;

    @PostMapping("/notificacao")
    @ResponseStatus(HttpStatus.CREATED)
    public NotificacaoExibicaoDTO salvar(@RequestBody NotificacaoCadastroDTO notificacaoDTO) {
        return notificacaoService.salvarNotificacao(notificacaoDTO);
    }

    @GetMapping("/notificacao")
    @ResponseStatus(HttpStatus.OK)
    public List<NotificacaoExibicaoDTO> listarTodos() {
        return notificacaoService.listarTodos();
    }

    @GetMapping("/notificacao/{notificacaoId}")
    public ResponseEntity<NotificacaoExibicaoDTO> buscarPorId(@PathVariable Long notificacaoId) {
        try {
            return ResponseEntity.ok(notificacaoService.buscarNotificacaoPorId(notificacaoId));
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/notificacao/{notificacaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long notificacaoId) {
        notificacaoService.excluirNotificacao(notificacaoId);
    }

    @PutMapping("/notificacao")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<NotificacaoExibicaoDTO> atualizar
            (@RequestBody NotificacaoCadastroDTO notificacaoDTO) {
        try {
            NotificacaoExibicaoDTO notificacaoExibicaoDTO =
                    notificacaoService.atualizarNotificacao(notificacaoDTO);
            return ResponseEntity.ok(notificacaoExibicaoDTO);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }
}
