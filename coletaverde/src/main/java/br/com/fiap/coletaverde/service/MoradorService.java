package br.com.fiap.coletaverde.service;

import br.com.fiap.coletaverde.dto.MoradorCadastroDTO;
import br.com.fiap.coletaverde.dto.MoradorExibicaoDTO;
import br.com.fiap.coletaverde.exception.MoradorNaoEncontradoException;
import br.com.fiap.coletaverde.model.Morador;
import br.com.fiap.coletaverde.repository.MoradorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.stream.StreamSupport;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MoradorService {

    @Autowired
    private MoradorRepository moradorRepository;

    public MoradorExibicaoDTO salvarMorador(MoradorCadastroDTO moradorDTO) {
        Morador morador = new Morador();
        BeanUtils.copyProperties(moradorDTO, morador);
        Morador moradorSalvo = moradorRepository.save(morador);
        return new MoradorExibicaoDTO(moradorSalvo);
    }

    public MoradorExibicaoDTO buscarMoradorPorId(Long id) {
        Optional<Morador> moradorOptional = moradorRepository.findById(id);
        if (moradorOptional.isPresent()){
            return new MoradorExibicaoDTO(moradorOptional.get());
        }else{
            throw new MoradorNaoEncontradoException("Morador não existe!");
        }
    }



    public MoradorExibicaoDTO buscarPorNome(String nome){
        Optional<Morador> moradorOptional =
                moradorRepository.buscarPorNome(nome);

        if (moradorOptional.isPresent()){
            return new MoradorExibicaoDTO(moradorOptional.get());
        } else {
            throw new MoradorNaoEncontradoException("Morador não existe!");
        }
    }


    public List<MoradorExibicaoDTO> listarTodos() {
        return StreamSupport.stream(moradorRepository.findAll().spliterator(), false)
                .map(MoradorExibicaoDTO::new)
                .collect(Collectors.toList());
    }

    public void excluirMorador(Long id) {
        Optional<Morador> moradorOptional = moradorRepository.findById(id);
        if (moradorOptional.isPresent()){
            moradorRepository.delete(moradorOptional.get());
        }else{
            throw new MoradorNaoEncontradoException("Morador não encontrado!");
        }
    }

    public MoradorExibicaoDTO atualizarMorador(MoradorCadastroDTO moradorDTO) {
        Optional<Morador> moradorOptional =
                moradorRepository.findById(moradorDTO.id());
        if (moradorOptional.isPresent()){
            Morador morador = new Morador();
            BeanUtils.copyProperties(moradorDTO, morador);
            return new MoradorExibicaoDTO(moradorRepository.save(morador));

        }else{
            throw new MoradorNaoEncontradoException("Morador não encontrado!");
        }
    }


    public MoradorExibicaoDTO salvarSenhaMorador(MoradorCadastroDTO moradorDTO){

        String senhaCriptografada = new
                BCryptPasswordEncoder().encode(moradorDTO.senha());

        Morador morador = new Morador();
        BeanUtils.copyProperties(moradorDTO, morador);
        morador.setSenha(senhaCriptografada);

        Morador moradorSalvo = moradorRepository.save(morador);

        return new MoradorExibicaoDTO(moradorSalvo);

    }


}
