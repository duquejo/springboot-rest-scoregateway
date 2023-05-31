package com.duquejo.ScoreGateway.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Table(name = "`USER`")
@Entity
public class User implements Serializable {

  @GeneratedValue
  @Id
  @Column(name = "USER_ID")
  private Long id;

  @Column(name = "USERNAME", unique = true)
  private String username;

  @Column(name = "PASSWORD")
  private String password;

  @Column(name = "ROLE")
  private byte role;

  @Column(name = "ACTIVE")
  private boolean isActive;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public byte getRole() {
    return role;
  }

  public void setRole(byte role) {
    this.role = role;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }
}
