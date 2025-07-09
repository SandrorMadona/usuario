package com.sandrojava.usuario.business;

import com.sandrojava.usuario.business.converter.UsuarioConverter;
import com.sandrojava.usuario.business.dto.UsuarioDTO;
import com.sandrojava.usuario.infrastructure.entity.Usuario;
import com.sandrojava.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){ //recebeu um usuario DTO
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO); //transformou em usuario "Entity" que [e o padrao
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario)); //Salvou a informacao no banco de dados, o banco de dados nos deu a informacao, desse usuario "Entity" do banco de dados foi convertido para DTO
    }
}
