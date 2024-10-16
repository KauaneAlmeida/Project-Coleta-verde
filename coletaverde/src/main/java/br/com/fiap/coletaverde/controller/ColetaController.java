package br.com.fiap.coletaverde.controller;

import br.com.fiap.coletaverde.dto.CaminhaoCadastroDTO;
import br.com.fiap.coletaverde.dto.CaminhaoExibicaoDTO;
import br.com.fiap.coletaverde.dto.ColetaCadastroDTO;
import br.com.fiap.coletaverde.dto.ColetaExibicaoDTO;
import br.com.fiap.coletaverde.model.Coleta;
import br.com.fiap.coletaverde.service.ColetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ColetaController {

    @Autowired
    private ColetaService coletaService;

    @PostMapping("/coletas")
    @ResponseStatus(HttpStatus.CREATED)
    public ColetaExibicaoDTO salvar(@RequestBody ColetaCadastroDTO coletaDTO) {
        return coletaService.salvarColeta(coletaDTO);
    }

    @GetMapping("/coletas")
    @ResponseStatus(HttpStatus.OK)
    public List<ColetaExibicaoDTO> listarTodos() {
        return coletaService.listarTodos();
    }

    @GetMapping("/coletas/{coletasId}")
    public ResponseEntity<ColetaExibicaoDTO> buscarPorId(@PathVariable Long coletaId) {
        try {
            return ResponseEntity.ok(coletaService.buscarColetaPorId(coletaId));
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/coletas/{coletaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long coletaId) {
        coletaService.excluirColeta(coletaId);
    }

    @PutMapping("/coletas")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ColetaExibicaoDTO> atualizar
            (@RequestBody ColetaCadastroDTO coletaDTO) {
        try {
            ColetaExibicaoDTO coletaExibicaoDTO =
                    coletaService.atualizarColeta(coletaDTO);
            return ResponseEntity.ok(coletaExibicaoDTO);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }
}
