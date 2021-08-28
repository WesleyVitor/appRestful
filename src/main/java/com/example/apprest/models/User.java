package com.example.apprest.models;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TB_USER")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nome;
    @CPF(message = "Cpf inválido")
    private String cpf;

    @Email(message = "Email inválido")
    private String email;

    private String dataNascimento;

    public void atualizarUsuario(User novo_usuario){
        setCpf(novo_usuario.getCpf());
        setEmail(novo_usuario.getEmail());
        setDataNascimento(novo_usuario.getDataNascimento());
        setNome(novo_usuario.getNome());
    }

}
