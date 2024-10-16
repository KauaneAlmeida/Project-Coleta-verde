package br.com.fiap.coletaverde.controller;

import br.com.fiap.coletaverde.dto.*;
import br.com.fiap.coletaverde.model.Rota;
import br.com.fiap.coletaverde.service.RotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RotaController {

    @Autowired
    private RotaService rotaService;

    @PostMapping("/rotas")
    @ResponseStatus(HttpStatus.CREATED)
    public RotaExibicaoDTO salvar(@RequestBody RotaCadastroDTO rotaDTO) {
        return rotaService.salvarRota(rotaDTO);
    }

    @GetMapping("/rotas")
    @ResponseStatus(HttpStatus.OK)
    public List<RotaExibicaoDTO> listarTodos() {
        return rotaService.listarTodos();
    }

    @GetMapping("/rotas/{rotaId}")
    public ResponseEntity<RotaExibicaoDTO> buscarPorId(@PathVariable Long rotaId) {
        try {
            return ResponseEntity.ok(rotaService.buscarRotaPorId(rotaId));
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/rotas/{rotaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long rotaId) {
        rotaService.excluirRota(rotaId);
    }

    @PutMapping("/rotas")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RotaExibicaoDTO> atualizar
            (@RequestBody RotaCadastroDTO rotaDTO) {
        try {
            RotaExibicaoDTO rotaExibicaoDTO =
                    rotaService.atualizarRota(rotaDTO);
            return ResponseEntity.ok(rotaExibicaoDTO);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @RequestMapping(value = "/rotas", params = "nome")
    public ResponseEntity<RotaExibicaoDTO> buscarPorNome(
            @RequestParam String nome){
        try {
            return ResponseEntity
                    .ok(rotaService.buscarPorNome(nome));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
