package br.com.fiap.coletaverde.controller;

import br.com.fiap.coletaverde.dto.LoginDTO;
import br.com.fiap.coletaverde.dto.MoradorCadastroDTO;
import br.com.fiap.coletaverde.dto.MoradorExibicaoDTO;
import br.com.fiap.coletaverde.service.MoradorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MoradorService moradorService;

    @PostMapping("/login")
    public ResponseEntity login(
            @RequestBody
            @Valid
            LoginDTO moradorDto
    ){
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(
                        moradorDto.email(),
                        moradorDto.senha());

        Authentication auth = authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity registrar(@RequestBody @Valid MoradorCadastroDTO moradorCadastroDTO){

        MoradorExibicaoDTO moradorSalvo = null;
        moradorSalvo = moradorService.salvarSenhaMorador(moradorCadastroDTO);

        return ResponseEntity.ok(moradorSalvo);

    }

}