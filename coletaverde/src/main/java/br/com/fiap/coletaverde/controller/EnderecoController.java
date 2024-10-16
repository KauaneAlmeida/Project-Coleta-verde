package br.com.fiap.coletaverde.controller;

import br.com.fiap.coletaverde.dto.ColetaCadastroDTO;
import br.com.fiap.coletaverde.dto.ColetaExibicaoDTO;
import br.com.fiap.coletaverde.dto.EnderecoCadastroDTO;
import br.com.fiap.coletaverde.dto.EnderecoExibicaoDTO;
import br.com.fiap.coletaverde.model.Endereco;
import br.com.fiap.coletaverde.service.ColetaService;
import br.com.fiap.coletaverde.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping("/enderecos")
    @ResponseStatus(HttpStatus.CREATED)
    public EnderecoExibicaoDTO salvar(@RequestBody EnderecoCadastroDTO enderecoDTO) {
        return enderecoService.salvarEndereco(enderecoDTO);
    }

    @GetMapping("/enderecos")
    @ResponseStatus(HttpStatus.OK)
    public List<EnderecoExibicaoDTO> listarTodos() {
        return enderecoService.listarTodos();
    }

    @GetMapping("/enderecos/{enderecoId}")
    public ResponseEntity<EnderecoExibicaoDTO> buscarPorId(@PathVariable Long enderecoId) {
        try {
            return ResponseEntity.ok(enderecoService.buscarEnderecoPorId(enderecoId));
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/enderecos/{enderecoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long enderecoId) {
        enderecoService.excluirEndereco(enderecoId);
    }

    @PutMapping("/enderecos")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EnderecoExibicaoDTO> atualizar
            (@RequestBody EnderecoCadastroDTO enderecoDTO) {
        try {
            EnderecoExibicaoDTO enderecoExibicaoDTO =
                    enderecoService.atualizarEndereco(enderecoDTO);
            return ResponseEntity.ok(enderecoExibicaoDTO);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }
}
