package br.com.fiap.coletaverde.service;

import br.com.fiap.coletaverde.dto.ColetaCadastroDTO;
import br.com.fiap.coletaverde.dto.ColetaExibicaoDTO;
import br.com.fiap.coletaverde.exception.ColetaNaoEncontradaException;
import br.com.fiap.coletaverde.model.Coleta;
import br.com.fiap.coletaverde.repository.ColetaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ColetaService {
    @Autowired
    private ColetaRepository coletaRepository;

    public ColetaExibicaoDTO salvarColeta(ColetaCadastroDTO coletaDTO) {
        Coleta coleta = new Coleta();
        BeanUtils.copyProperties(coletaDTO, coleta);
        Coleta coletaSalvo = coletaRepository.save(coleta);
        return new ColetaExibicaoDTO(coletaSalvo);
    }

    public ColetaExibicaoDTO buscarColetaPorId(Long id) {
        Optional<Coleta> coletaOptional = coletaRepository.findById(id);
        if (coletaOptional.isPresent()){
            return new ColetaExibicaoDTO(coletaOptional.get());
        }else{
            throw new ColetaNaoEncontradaException("Coleta não existe!");
        }
    }

    public List<ColetaExibicaoDTO> listarTodos() {
        return StreamSupport.stream(coletaRepository.findAll().spliterator(), false)
                .map(ColetaExibicaoDTO::new)
                .collect(Collectors.toList());
    }


    public void excluirColeta(Long id) {
        Optional<Coleta> coletaOptional = coletaRepository.findById(id);
        if (coletaOptional.isPresent()){
            coletaRepository.delete(coletaOptional.get());
        }else{
            throw new ColetaNaoEncontradaException("Coleta não encontrada!");
        }
    }

    public ColetaExibicaoDTO atualizarColeta(ColetaCadastroDTO coletaDTO) {
        Optional<Coleta> coletaOptional =
                coletaRepository.findById(coletaDTO.id());
        if (coletaOptional.isPresent()){
            Coleta coleta = new Coleta();
            BeanUtils.copyProperties(coletaDTO, coleta);
            return new ColetaExibicaoDTO(coletaRepository.save(coleta));

        } else{
            throw new ColetaNaoEncontradaException("Coleta não encontrada!");
        }
    }

}
