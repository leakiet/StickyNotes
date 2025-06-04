package com.sticky.notes.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;
  @Column
  private String email;
  @Column
  private String name;
  @Column
  private String password;
  @Column
  private String about;

  public User() {
    super();
  }

  public User(String email, String name, String password,
      String about) {
    super();
    this.email = email;
    this.name = name;
    this.password = password;
    this.about = about;
  }

  public Long getId() {
    return userId;
  }

  public void setId(Long id) {
    this.userId = id;
  }

  public String getemail() {
    return email;
  }

  public void setemail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getAbout() {
    return about;
  }

  public void setAbout(String about) {
    this.about = about;
  }

  @Override
  public String toString() {
    return "User [email=" + email + ", name=" + name +
        ", password=" + password + ", about=" + about + "]";
  }
}