package br.com.etechas.tarefas.entity;

import br.com.etechas.tarefas.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.Data;

@Table
@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private RoleEnum role;
}
