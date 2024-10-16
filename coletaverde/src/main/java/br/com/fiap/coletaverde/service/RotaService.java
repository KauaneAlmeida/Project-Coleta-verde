package br.com.fiap.coletaverde.service;

import br.com.fiap.coletaverde.dto.*;
import br.com.fiap.coletaverde.exception.MoradorNaoEncontradoException;
import br.com.fiap.coletaverde.exception.RotaNaoEncontradaException;
import br.com.fiap.coletaverde.model.Morador;
import br.com.fiap.coletaverde.model.Rota;
import br.com.fiap.coletaverde.repository.RotaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RotaService {

    @Autowired
    private RotaRepository rotaRepository;

    public RotaExibicaoDTO salvarRota(RotaCadastroDTO rotaDTO) {
        Rota rota = new Rota();
        BeanUtils.copyProperties(rotaDTO, rota);
        Rota rotaSalvo = rotaRepository.save(rota);
        return new RotaExibicaoDTO(rotaSalvo);
    }

    public RotaExibicaoDTO buscarRotaPorId(Long id) {
        Optional<Rota> rotaOptional = rotaRepository.findById(id);
        if (rotaOptional.isPresent()){
            return new RotaExibicaoDTO(rotaOptional.get());
        }else{
            throw new RotaNaoEncontradaException("Rota não existe!");
        }
    }

    public RotaExibicaoDTO buscarPorNome(String nome){
        Optional<Rota> rotaOptional =
                rotaRepository.buscarPorNome(nome);

        if (rotaOptional.isPresent()){
            return new RotaExibicaoDTO(rotaOptional.get());
        } else {
            throw new RotaNaoEncontradaException("Rota não existe!");
        }
    }

    public List<RotaExibicaoDTO> listarTodos() {
        return StreamSupport.stream(rotaRepository.findAll().spliterator(), false)
                .map(RotaExibicaoDTO::new)
                .collect(Collectors.toList());
    }

    public void excluirRota(Long id) {
        Optional<Rota> rotaOptional = rotaRepository.findById(id);
        if (rotaOptional.isPresent()){
            rotaRepository.delete(rotaOptional.get());
        }else{
            throw new RuntimeException("Rota não encontrada!");
        }
    }

    public RotaExibicaoDTO atualizarRota(RotaCadastroDTO rotaDTO) {
        Optional<Rota> rotaOptional =
                rotaRepository.findById(rotaDTO.id());
        if (rotaOptional.isPresent()){
            Rota rota = new Rota();
            BeanUtils.copyProperties(rotaDTO, rota);
            return new RotaExibicaoDTO(rotaRepository.save(rota));

        } else{
            throw new RuntimeException("Rota não encontrada!");
        }
    }
}
