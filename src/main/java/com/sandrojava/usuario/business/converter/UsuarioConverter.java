package com.sandrojava.usuario.business.converter;

import com.sandrojava.usuario.business.dto.EnderecoDTO;
import com.sandrojava.usuario.business.dto.TelefoneDTO;
import com.sandrojava.usuario.business.dto.UsuarioDTO;
import com.sandrojava.usuario.infrastructure.entity.Endereco;
import com.sandrojava.usuario.infrastructure.entity.Telefone;
import com.sandrojava.usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioConverter {

    // Converte um objeto UsuarioDTO para um objeto da entidade Usuario
    public Usuario paraUsuario(UsuarioDTO usuarioDTO){
        return Usuario.builder()
                .nome(usuarioDTO.getNome()) // Copia o nome
                .email(usuarioDTO.getEmail()) // Copia o e-mail
                .senha(usuarioDTO.getSenha()) // Copia a senha
                .enderecos(paraListaEndereco(usuarioDTO.getEnderecos())) // Converte a lista de EnderecoDTO para Endereco
                .telefones(paraListaTelefones(usuarioDTO.getTelefones())) // Converte a lista de TelefoneDTO para Telefone
                .build(); // Cria o objeto Usuario com os dados fornecidos
    }

    // Converte uma lista de objetos EnderecoDTO em uma lista de objetos Endereco
    public List<Endereco> paraListaEndereco(List<EnderecoDTO> enderecoDTOS){
        List<Endereco> enderecos = new ArrayList<>();
        // Percorre cada EnderecoDTO e converte para Endereco
        for(EnderecoDTO enderecoDto : enderecoDTOS){
            enderecos.add(paraEndereco(enderecoDto));
        }
        return enderecos;
    }

    // Converte um único objeto EnderecoDTO para Endereco
    public Endereco paraEndereco(EnderecoDTO enderecoDTO){
        return Endereco.builder()
                .rua(enderecoDTO.getRua()) // Copia a rua
                .numero(enderecoDTO.getNumero()) // Copia o número
                .cidade(enderecoDTO.getCidade()) // Copia a cidade
                .complemento(enderecoDTO.getComplemento()) // Copia o complemento
                .cep(enderecoDTO.getCep()) // Copia o CEP
                .estado(enderecoDTO.getEstado()) // Copia o estado
                .build(); // Cria o Endereco com os dados copiados
    }

    // Converte uma lista de objetos TelefoneDTO para uma lista de objetos Telefone usando Stream API
    public List<Telefone> paraListaTelefones(List<TelefoneDTO> telefoneDTOS){
        // Aplica a função de conversão em cada item da lista e retorna como nova lista
        return telefoneDTOS.stream().map(this::paraTelefone).toList();
    }

    // Converte um único objeto TelefoneDTO para Telefone
    public Telefone paraTelefone(TelefoneDTO telefoneDTO){
        return Telefone.builder()
                .numero(telefoneDTO.getNumero()) // Copia o número do telefone
                .ddd(telefoneDTO.getDdd()) // Copia o DDD
                .build(); // Cria o Telefone com os dados fornecidos
    }


    // ----------------------------------------------------------------------------------------------------------------------------------
    // build de conversao para DTO
    // Converte um objeto da entidade Usuario para um objeto UsuarioDTO
    public UsuarioDTO paraUsuarioDTO(Usuario usuarioDTO){
        return UsuarioDTO.builder()
                .nome(usuarioDTO.getNome()) // Copia o nome
                .email(usuarioDTO.getEmail()) // Copia o e-mail
                .senha(usuarioDTO.getSenha()) // Copia a senha
                .enderecos(paraListaEnderecoDTO(usuarioDTO.getEnderecos())) // Converte a lista de Endereco para EnderecoDTO
                .telefones(paraListaTelefonesDTO(usuarioDTO.getTelefones())) // Converte a lista de Telefone para TelefoneDTO
                .build(); // Cria o objeto UsuarioDTO usando o padrão Builder
    }
    // Converte uma lista de objetos Endereco em uma lista de EnderecoDTO
    public List<EnderecoDTO> paraListaEnderecoDTO(List<Endereco> enderecoDTOS){
        List<EnderecoDTO> enderecos = new ArrayList<>();
        // Percorre cada Endereco e converte para EnderecoDTO
        for(Endereco enderecoDTO : enderecoDTOS){
            enderecos.add(paraEnderecoDTO(enderecoDTO));
        }
        return enderecos;
    }

    // Converte um único objeto Endereco em EnderecoDTO
    public EnderecoDTO paraEnderecoDTO(Endereco enderecoDTO){
        return EnderecoDTO.builder()
                .rua(enderecoDTO.getRua()) // Copia a rua
                .numero(enderecoDTO.getNumero()) // Copia o número
                .cidade(enderecoDTO.getCidade()) // Copia a cidade
                .complemento(enderecoDTO.getComplemento()) // Copia o complemento
                .cep(enderecoDTO.getCep()) // Copia o CEP
                .estado(enderecoDTO.getEstado()) // Copia o estado
                .build(); // Cria o EnderecoDTO com os dados copiados
    }

    // Converte uma lista de objetos Telefone em uma lista de TelefoneDTO usando Stream API
    public List<TelefoneDTO> paraListaTelefonesDTO(List<Telefone> telefoneDTOS){
        // Aplica a função de conversão para cada item da lista e retorna como nova lista
        return telefoneDTOS.stream().map(this::paraTelefoneDTO).toList();
    }

    // Converte um único objeto Telefone em TelefoneDTO
    public TelefoneDTO paraTelefoneDTO(Telefone telefoneDTO){
        return TelefoneDTO.builder()
                .numero(telefoneDTO.getNumero()) // Copia o número
                .ddd(telefoneDTO.getDdd()) // Copia o DDD
                .build(); // Cria o TelefoneDTO com os dados copiados
    }
 //metodo de update
    public Usuario updateUsuario (UsuarioDTO usuarioDTO, Usuario entity){
        return Usuario.builder()
                .nome(usuarioDTO.getNome() != null ? usuarioDTO.getNome() : entity.getNome())
                .id(entity.getId())
                .senha(usuarioDTO.getSenha() != null ? usuarioDTO.getSenha() : entity.getSenha())
                .email(usuarioDTO.getEmail() != null ? usuarioDTO.getEmail() : entity.getEmail())
                .enderecos(entity.getEnderecos())
                .telefones(entity.getTelefones())
                .build();
    }
}
