package com.duquejo.ScoreGateway.service;

import com.duquejo.ScoreGateway.entity.Score;
import com.duquejo.ScoreGateway.model.ScoreModel;
import com.duquejo.ScoreGateway.repository.ScoreRepository;
import com.duquejo.ScoreGateway.util.Converter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("service")
public class ScoreService {

  private static final Log logger = LogFactory.getLog(ScoreService.class);

  @Autowired
  @Qualifier("repository")
  private ScoreRepository repository;

  @Autowired
  @Qualifier("converter")
  private Converter converter;

  public boolean create(Score score) {
    logger.info("Creating score...");
    try {
      repository.save(score);
      logger.info("Operation successful");
      return true;
    }catch(Exception e) {
      logger.info("Something happened");
      return false;
    }
  }

  public boolean update(Score score) {
    logger.info("Updating score...");
    try {
      Score result = repository.findById(score.getId()).orElse(null);
      repository.save(score);
      logger.info("Operation successful: " + result);
      return true;
    }catch(Exception e) {
      logger.info("Something happened");
      return false;
    }
  }

  public boolean delete(String name, Long id) {
    logger.warn("Deleting score...");
    try {
      Score score = repository.findByNameAndId(name, id);
      repository.delete(score);
      logger.warn("Operation successful");
      return true;
    }catch(Exception e) {
      logger.warn("Something happened");
      return false;
    }
  }

  public List<ScoreModel> findAll() {
    return converter.convertList(repository.findAll());
  }

  public ScoreModel findByNameAndTitle(String name, String title) {
    return new ScoreModel(repository.findByNameAndTitle(name, title));
  }

  public List<ScoreModel> findByTitle(String title) {
    return converter.convertList(repository.findByTitle(title));
  }

  public ScoreModel findByName(String name) {
    return new ScoreModel(repository.findByName(name));
  }

  public List<ScoreModel> findByPagination(Pageable pageable) {
    return converter.convertList(repository.findAll(pageable).getContent());
  }
}
