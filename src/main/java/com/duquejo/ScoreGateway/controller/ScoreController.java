package com.duquejo.ScoreGateway.controller;


import com.duquejo.ScoreGateway.entity.Score;
import com.duquejo.ScoreGateway.model.ScoreModel;
import com.duquejo.ScoreGateway.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class ScoreController {

  @Autowired
  @Qualifier("service")
  private ScoreService service;

  @PutMapping("/score")
  public boolean addScore(@RequestBody Score score) {
    return service.create(score);
  }

  @PostMapping("/score")
  public boolean updateScore(@RequestBody Score score) {
    return service.update(score);
  }

  @DeleteMapping("/score/{id}/{name}")
  public boolean deleteScore(
      @PathVariable("id") Long id,
      @PathVariable("name") String name) {
    return service.delete(name, id);
  }

  @GetMapping("/score")
  public List<ScoreModel> getScore(Pageable pageable) {
    return service.findByPagination(pageable);
  }
}
