package com.sandrojava.usuario.controller;

import com.sandrojava.usuario.business.UsuarioService;
import com.sandrojava.usuario.business.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvaUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }
    /*
     * Define o endpoint para salvar um novo usuário.
     * Mapeia requisições HTTP POST para a URL base do controlador.
     * O corpo da requisição é desserializado para um objeto UsuarioDTO.
     * O serviço `usuarioService` é chamado para persistir o usuário.
     * Retorna o usuário salvo com status de sucesso (200 OK).
     */

}

