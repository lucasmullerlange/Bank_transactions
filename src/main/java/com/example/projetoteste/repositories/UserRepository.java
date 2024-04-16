package com.example.projetoteste.repositories;

import com.example.projetoteste.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
