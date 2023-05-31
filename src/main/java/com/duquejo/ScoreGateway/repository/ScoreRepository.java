package com.duquejo.ScoreGateway.repository;

import com.duquejo.ScoreGateway.entity.Score;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("repository")
public interface ScoreRepository extends JpaRepository<Score, Serializable>, PagingAndSortingRepository<Score, Serializable> {
  public abstract Score findByName(String name);
  public abstract List<Score> findByTitle(String title);
  public abstract Score findByNameAndTitle(String name, String title);
  public abstract Score findByNameAndId(String name, Long id);
  public abstract Page<Score> findAll(Pageable pageable);
}
