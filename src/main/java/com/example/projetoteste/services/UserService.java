package com.example.projetoteste.services;

import com.example.projetoteste.domain.user.User;
import com.example.projetoteste.domain.user.UserDTO;
import com.example.projetoteste.domain.user.UserType;
import com.example.projetoteste.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void saveUser(User user){
        this.repository.save(user);

    }
    public User createUser(UserDTO user) {
        User newUser = new User(user);
        this.saveUser(newUser);
        return newUser;

    }

    public List<User> getAllUsers() {
            return this.repository.findAll();
    }

    public User findUserById(Long id) throws Exception {
        return this.repository.findById(id).orElseThrow(() -> new Exception ("usuario não encontrado"));
    }

    public boolean validateUser(User payer, BigDecimal amount ) throws Exception {
        if(payer.getUserType() == UserType.MERCHANT){
                   throw new Exception("o usuairo lojista não pode realizar transações");
        }

        if(payer.getBalance().compareTo(amount) < 0 ){
            throw  new Exception("saldo insuficiente");
        }

        return true;
    }
}
