package br.com.fiap.coletaverde.controller;

import br.com.fiap.coletaverde.dto.RotaCadastroDTO;
import br.com.fiap.coletaverde.dto.RotaExibicaoDTO;
import br.com.fiap.coletaverde.dto.TipoResiduoCadastroDTO;
import br.com.fiap.coletaverde.dto.TipoResiduoExibicaoDTO;
import br.com.fiap.coletaverde.model.TipoResiduo;
import br.com.fiap.coletaverde.service.TipoResiduoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TipoResiduoController {

    @Autowired
    private TipoResiduoService tipoResiduoService;

    @PostMapping("/tiporesiduo")
    @ResponseStatus(HttpStatus.CREATED)
    public TipoResiduoExibicaoDTO salvar(@RequestBody TipoResiduoCadastroDTO tiporesiduo) {
        return tipoResiduoService.salvarTipoResiduo(tiporesiduo);
    }

    @GetMapping("/tiporesiduo")
    @ResponseStatus(HttpStatus.OK)
    public List<TipoResiduoExibicaoDTO> listarTodos() {
        return tipoResiduoService.listarTodos();
    }

    @GetMapping("/tipoResiduo/{tiporesiduoId}")
    public ResponseEntity<TipoResiduoExibicaoDTO> buscarPorId(@PathVariable Long tiporesiduoId) {
        try {
            return ResponseEntity.ok(tipoResiduoService.buscarTipoResiduoPorId(tiporesiduoId));
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/tiporesiduo/{tiporesiduoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long tiporesiduoId) {
        tipoResiduoService.excluirTipoResiduo(tiporesiduoId);
    }

    @PutMapping("/tiporesiduo")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TipoResiduoExibicaoDTO> atualizar
            (@RequestBody TipoResiduoCadastroDTO tipoResiduoDTO) {
        try {
            TipoResiduoExibicaoDTO tipoResiduoExibicaoDTO =
                    tipoResiduoService.atualizarTipoResiduo(tipoResiduoDTO);
            return ResponseEntity.ok(tipoResiduoExibicaoDTO);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/tiporesiduo", params = "nome")
    public ResponseEntity<TipoResiduoExibicaoDTO> buscarPorNome(
            @RequestParam String nome){
        try {
            return ResponseEntity
                    .ok(tipoResiduoService.buscarPorNome(nome));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
