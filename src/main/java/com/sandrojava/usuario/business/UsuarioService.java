package com.sandrojava.usuario.business;

import com.sandrojava.usuario.business.converter.UsuarioConverter;
import com.sandrojava.usuario.business.dto.UsuarioDTO;
import com.sandrojava.usuario.infrastructure.entity.Usuario;
import com.sandrojava.usuario.infrastructure.exceptions.ConflictException;
import com.sandrojava.usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.sandrojava.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){ //recebeu um usuario DTO
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO); //transformou em usuario "Entity" que [e o padrao
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario)); //Salvou a informacao no banco de dados, o banco de dados nos deu a informacao, desse usuario "Entity" do banco de dados foi convertido para DTO
    }

    public void emailExiste(String email) {
        try {
            Boolean existe = verificaEmailJaExistente(email);
            if (existe) {
                throw new ConflictException("Email já existente: " + email);
            }
        } catch (ConflictException e) {
            throw new ConflictException(e.getMessage(), e.getCause());
        }

    }

    public Boolean verificaEmailJaExistente(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario buscarUsuarioPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email nao encontrado" + email));
    }
    public void deletaUsuarioPorEmail(String email){ usuarioRepository.deleteByEmail(email);}
}
