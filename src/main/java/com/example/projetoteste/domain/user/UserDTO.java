package com.example.projetoteste.domain.user;

import java.math.BigDecimal;

public  record UserDTO (
                String nome,
                String document,
                BigDecimal balance,
                String email,
                String password,
                UserType userType ){

}
