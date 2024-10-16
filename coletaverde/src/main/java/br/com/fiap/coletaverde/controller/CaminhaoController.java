package br.com.fiap.coletaverde.controller;

import br.com.fiap.coletaverde.dto.CaminhaoCadastroDTO;
import br.com.fiap.coletaverde.dto.CaminhaoExibicaoDTO;
import br.com.fiap.coletaverde.model.Caminhao;
import br.com.fiap.coletaverde.service.CaminhaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CaminhaoController {

    @Autowired
    private CaminhaoService caminhaoService;

    @PostMapping("/caminhoes")
    @ResponseStatus(HttpStatus.CREATED)
    public CaminhaoExibicaoDTO salvar(@RequestBody CaminhaoCadastroDTO caminhaoDTO) {
        return caminhaoService.salvarCaminhao(caminhaoDTO);
    }

    @GetMapping("/caminhoes")
    @ResponseStatus(HttpStatus.OK)
    public List<CaminhaoExibicaoDTO> listarTodos() {
        return caminhaoService.listarTodos();
    }

    @GetMapping("/caminhoes/{caminhaoId}")
    public ResponseEntity<CaminhaoExibicaoDTO> buscarPorId(@PathVariable Long caminhaoId) {
        try {
            return ResponseEntity.ok(caminhaoService.buscarCaminhaoPorId(caminhaoId));
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/caminhoes/{caminhaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long caminhaoId) {
        caminhaoService.excluirCaminhao(caminhaoId);
    }

    @PutMapping("/caminhoes")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CaminhaoExibicaoDTO> atualizar
            (@RequestBody CaminhaoCadastroDTO caminhaoDTO) {
        try {
            CaminhaoExibicaoDTO caminhaoExibicaoDTO =
                    caminhaoService.atualizarCaminhao(caminhaoDTO);
            return ResponseEntity.ok(caminhaoExibicaoDTO);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }


}
