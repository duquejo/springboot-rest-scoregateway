package com.duquejo.ScoreGateway.util;

import com.duquejo.ScoreGateway.entity.Score;
import com.duquejo.ScoreGateway.model.ScoreModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Converter {
  public List<ScoreModel> convertList(List<Score> scores){
    List<ScoreModel> scoreModel = new ArrayList<>();
    for (Score s: scores) {
      scoreModel.add(new ScoreModel(s));
    }
    return scoreModel;
  }
}
