package dev.sandrocaseiro.springbootitExample.models.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Usuario")
@Table(name = "usuario")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class EUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "email", nullable = false, length = 150)
    private String email;
}
