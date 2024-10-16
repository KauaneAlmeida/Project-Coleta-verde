package br.com.fiap.coletaverde.service;

import br.com.fiap.coletaverde.dto.EnderecoCadastroDTO;
import br.com.fiap.coletaverde.dto.EnderecoExibicaoDTO;
import br.com.fiap.coletaverde.exception.EnderecoNaoEncontradoException;
import br.com.fiap.coletaverde.model.Endereco;
import br.com.fiap.coletaverde.repository.EnderecoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public EnderecoExibicaoDTO salvarEndereco(EnderecoCadastroDTO enderecoDTO) {
        Endereco endereco = new Endereco();
        BeanUtils.copyProperties(enderecoDTO, endereco);
        Endereco enderecoSalvo = enderecoRepository.save(endereco);
        return new EnderecoExibicaoDTO(enderecoSalvo);
    }


    public EnderecoExibicaoDTO buscarEnderecoPorId(Long id) {
        Optional<Endereco> enderecoOptional = enderecoRepository.findById(id);
        if (enderecoOptional.isPresent()){
            return new EnderecoExibicaoDTO(enderecoOptional.get());
        }else{
            throw new EnderecoNaoEncontradoException("Endereço não existe!");
        }
    }


    public List<EnderecoExibicaoDTO> listarTodos() {
        return StreamSupport.stream(enderecoRepository.findAll().spliterator(), false)
                .map(EnderecoExibicaoDTO::new)
                .collect(Collectors.toList());
    }

    public void excluirEndereco(Long id) {
        Optional<Endereco> enderecoOptional = enderecoRepository.findById(id);
        if (enderecoOptional.isPresent()){
            enderecoRepository.delete(enderecoOptional.get());
        }else{
            throw new EnderecoNaoEncontradoException("Endereço não encontrado!");
        }
    }

    public EnderecoExibicaoDTO atualizarEndereco(EnderecoCadastroDTO enderecoDTO) {
        Optional<Endereco> enderecoOptional =
                enderecoRepository.findById(enderecoDTO.id());
        if (enderecoOptional.isPresent()){
            Endereco endereco = new Endereco();
            BeanUtils.copyProperties(enderecoDTO, endereco);
            return new EnderecoExibicaoDTO(enderecoRepository.save(endereco));

        } else{
            throw new EnderecoNaoEncontradoException("Endereço não encontrado!");
        }
    }
}
