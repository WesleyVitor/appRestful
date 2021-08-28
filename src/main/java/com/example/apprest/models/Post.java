package com.example.apprest.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="TB_POST")
@Data
public class Post implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String corpo;

    @ManyToOne(optional = false)
    private User user;

    public void alterarPost(Post novo_post){
        setTitulo(novo_post.getTitulo());
        setCorpo(novo_post.getCorpo());
    }




}
