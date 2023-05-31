package com.duquejo.ScoreGateway.model;

import com.duquejo.ScoreGateway.entity.Score;

public class ScoreModel {
  private Long id;
  private String name;
  private String title;
  private String content;

  /**
   * Convert JPA Entity to Model
   */
  public ScoreModel(Score score) {
    this.id = score.getId();
    this.name = score.getName();
    this.title = score.getTitle();
    this.content = score.getContent();
  }

  public ScoreModel(Long id, String name, String title, String content) {
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
