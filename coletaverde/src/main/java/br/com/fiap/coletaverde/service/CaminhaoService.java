package br.com.fiap.coletaverde.service;

import br.com.fiap.coletaverde.dto.CaminhaoCadastroDTO;
import br.com.fiap.coletaverde.dto.CaminhaoExibicaoDTO;
import br.com.fiap.coletaverde.exception.CaminhaoNaoEncontradoException;
import br.com.fiap.coletaverde.model.Caminhao;
import br.com.fiap.coletaverde.repository.CaminhaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CaminhaoService {

    @Autowired
    private CaminhaoRepository caminhaoRepository;


    public CaminhaoExibicaoDTO salvarCaminhao(CaminhaoCadastroDTO caminhaoDTO) {
        Caminhao caminhao = new Caminhao();
        BeanUtils.copyProperties(caminhaoDTO, caminhao);
        Caminhao caminhaoSalvo = caminhaoRepository.save(caminhao);
        return new CaminhaoExibicaoDTO(caminhaoSalvo);
    }

    public CaminhaoExibicaoDTO buscarCaminhaoPorId(Long id) {
        Optional<Caminhao> caminhaoOptional = caminhaoRepository.findById(id);
        if (caminhaoOptional.isPresent()){
            return new CaminhaoExibicaoDTO(caminhaoOptional.get());
        }else{
            throw new CaminhaoNaoEncontradoException("Caminhão não existe!");
        }
    }
    public List<CaminhaoExibicaoDTO> listarTodos() {
        return StreamSupport.stream(caminhaoRepository.findAll().spliterator(), false)
                .map(CaminhaoExibicaoDTO::new)
                .collect(Collectors.toList());
    }

    public void excluirCaminhao(Long id) {
        Optional<Caminhao> caminhaoOptional = caminhaoRepository.findById(id);
        if (caminhaoOptional.isPresent()){
            caminhaoRepository.delete(caminhaoOptional.get());
        }else{
            throw new CaminhaoNaoEncontradoException("Caminhão não encontrado!");
        }
    }

    public CaminhaoExibicaoDTO atualizarCaminhao(CaminhaoCadastroDTO caminhaoDTO) {
        Optional<Caminhao> caminhaoOptional =
                caminhaoRepository.findById(caminhaoDTO.id());
        if (caminhaoOptional.isPresent()){
            Caminhao caminhao = new Caminhao();
            BeanUtils.copyProperties(caminhaoDTO, caminhao);
            return new CaminhaoExibicaoDTO(caminhaoRepository.save(caminhao));

        } else{
            throw new CaminhaoNaoEncontradoException("Caminhão não encontrado!");
        }
    }

}
