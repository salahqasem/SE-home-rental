package com.se.rental.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Token {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String previousRefreshToken;
  private String refreshToken;

  public Token(String previousRefreshToken, String refreshToken) {
    this.previousRefreshToken = previousRefreshToken;
    this.refreshToken = refreshToken;
  }
}
