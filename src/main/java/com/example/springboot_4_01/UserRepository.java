package com.example.springboot_4_01;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
  User findByUsername(String username);
  User findByEmail(String email);
  User findByEmailAndPassword(String email, String password);
  Long countByEmail(String email);
  Long countByUsername(String username);
}
