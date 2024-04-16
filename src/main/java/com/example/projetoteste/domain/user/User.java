package com.example.projetoteste.domain.user;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
@Entity(name = "users")
@Table(name= "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String email;

    private String password;

    private UserType UserType;

    private BigDecimal balance;




    public User(UserDTO dto){
        this.name = dto.nome();
        this.document = dto.document();
        this.email = dto.email();
        this.password = dto.password();
        this.balance = dto.balance();

    }

}
