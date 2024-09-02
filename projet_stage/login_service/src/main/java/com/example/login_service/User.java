package com.example.login_service;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Entity

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String username;
  private String email;
  private String password;
  private boolean isVerified;

  // Getters and Setters
}
