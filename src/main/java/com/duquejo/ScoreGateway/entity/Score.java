package com.duquejo.ScoreGateway.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Table(name = "SCORE")
@Entity
public class Score implements Serializable {

  @GeneratedValue
  @Id
  @Column(name = "SCORE_ID")
  private Long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "TITLE")
  private String title;

  @Column(name = "CONTENT")
  private String content;

  public Score() {
  }

  public Score(Long id, String name, String title, String content) {
    this.id = id;
    this.name = name;
    this.title = title;
    this.content = content;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
