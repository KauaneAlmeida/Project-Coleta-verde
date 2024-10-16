package br.com.fiap.coletaverde.controller;

import br.com.fiap.coletaverde.dto.MoradorCadastroDTO;
import br.com.fiap.coletaverde.dto.MoradorExibicaoDTO;
import br.com.fiap.coletaverde.service.MoradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MoradorController {

    @Autowired
    private MoradorService moradorService;

    @PostMapping("/moradores")
    @ResponseStatus(HttpStatus.CREATED)
    public MoradorExibicaoDTO salvar(@RequestBody MoradorCadastroDTO moradorCTO) {
        return moradorService.salvarMorador(moradorCTO);
    }

    @GetMapping("/moradores")
    @ResponseStatus(HttpStatus.OK)
    public List<MoradorExibicaoDTO> listarTodos() {
        return moradorService.listarTodos();
    }

    @GetMapping("/moradores/{moradorId}")
    public ResponseEntity<MoradorExibicaoDTO> buscarPorId(@PathVariable Long moradorId) {
        try {
            return ResponseEntity.ok(moradorService.buscarMoradorPorId(moradorId));
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/moradores/{moradorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long moradorId) {
        moradorService.excluirMorador(moradorId);
    }

    @PutMapping("/moradores")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MoradorExibicaoDTO> atualizar
            (@RequestBody MoradorCadastroDTO moradorDTO) {
        try {
            MoradorExibicaoDTO moradorExibicaoDTO =
                    moradorService.atualizarMorador(moradorDTO);
            return ResponseEntity.ok(moradorExibicaoDTO);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }


    @RequestMapping(value = "/moradores", params = "nome")
    public ResponseEntity<MoradorExibicaoDTO> buscarPorNome(
            @RequestParam String nome){
        try {
            return ResponseEntity
                    .ok(moradorService.buscarPorNome(nome));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}
