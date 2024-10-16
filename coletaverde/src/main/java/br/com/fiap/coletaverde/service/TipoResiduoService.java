package br.com.fiap.coletaverde.service;

import br.com.fiap.coletaverde.dto.RotaExibicaoDTO;
import br.com.fiap.coletaverde.dto.TipoResiduoCadastroDTO;
import br.com.fiap.coletaverde.dto.TipoResiduoExibicaoDTO;
import br.com.fiap.coletaverde.exception.RotaNaoEncontradaException;
import br.com.fiap.coletaverde.exception.TipoResiduoNaoEncontradoException;
import br.com.fiap.coletaverde.model.Rota;
import br.com.fiap.coletaverde.model.TipoResiduo;
import br.com.fiap.coletaverde.repository.TipoResiduoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TipoResiduoService {

    @Autowired
    private TipoResiduoRepository tipoResiduoRepository;

    public TipoResiduoExibicaoDTO salvarTipoResiduo(TipoResiduoCadastroDTO tipoResiduoDTO) {
        TipoResiduo tipoResiduo = new TipoResiduo();
        BeanUtils.copyProperties(tipoResiduoDTO, tipoResiduo);
        TipoResiduo tipoResiduoSalvo = tipoResiduoRepository.save(tipoResiduo);
        return new TipoResiduoExibicaoDTO(tipoResiduoSalvo);
    }

    public TipoResiduoExibicaoDTO buscarTipoResiduoPorId(Long id) {
        Optional<TipoResiduo> tipoResiduoOptional = tipoResiduoRepository.findById(id);
        if (tipoResiduoOptional.isPresent()){
            return new TipoResiduoExibicaoDTO(tipoResiduoOptional.get());
        }else{
            throw new TipoResiduoNaoEncontradoException("TipoResiduo não existe!");
        }
    }

    public TipoResiduoExibicaoDTO buscarPorNome(String nome){
        Optional<TipoResiduo> tipoResiduoOptional =
                tipoResiduoRepository.buscarPorNome(nome);

        if (tipoResiduoOptional.isPresent()){
            return new TipoResiduoExibicaoDTO(tipoResiduoOptional.get());
        } else {
            throw new TipoResiduoNaoEncontradoException("TipoResiduo não existe!");
        }
    }

    public List<TipoResiduoExibicaoDTO> listarTodos() {
        return StreamSupport.stream(tipoResiduoRepository.findAll().spliterator(), false)
                .map(TipoResiduoExibicaoDTO::new)
                .collect(Collectors.toList());
    }

    public void excluirTipoResiduo(Long id) {
        Optional<TipoResiduo> tipoResiduoOptional = tipoResiduoRepository.findById(id);
        if (tipoResiduoOptional.isPresent()){
            tipoResiduoRepository.delete(tipoResiduoOptional.get());
        }else{
            throw new TipoResiduoNaoEncontradoException("TipoResiduo não encontrado!");
        }
    }

    public TipoResiduoExibicaoDTO atualizarTipoResiduo(TipoResiduoCadastroDTO tipoResiduoDTO) {
        Optional<TipoResiduo> tipoResiduoOptional =
                tipoResiduoRepository.findById(tipoResiduoDTO.id());
        if (tipoResiduoOptional.isPresent()){
            TipoResiduo tipoResiduo = new TipoResiduo();
            BeanUtils.copyProperties(tipoResiduoDTO, tipoResiduo);
            return new TipoResiduoExibicaoDTO(tipoResiduoRepository.save(tipoResiduo));

        } else{
            throw new TipoResiduoNaoEncontradoException("TipoResiduo não encontrado!");
        }
    }


}
