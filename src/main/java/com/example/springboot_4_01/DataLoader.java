package com.example.springboot_4_01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  public void run(String... strings) throws Exception{
      System.out.println("Loading data...");

      roleRepository.save(new Role("USER"));
      roleRepository.save(new Role("ADMIN"));

      Role userRole = roleRepository.findByRole("USER");
      Role adminRole = roleRepository.findByRole("ADMIN");

      User user = new User("bob@bob.com", "password",
              "Bob", "Bobberson", true, "bob");
      user.setRoles(Arrays.asList(userRole));
      userRepository.save(user);

      user = new User("admin@adm.com", "password",
              "Admin", "User", true, "admin");
      user.setRoles(Arrays.asList(adminRole));
      userRepository.save(user);
    }
}
