package com.example.stage6.Graphiques.Linear.Repository;

import com.example.stage6.Graphiques.Linear.entity.ChartData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChartDataRepository extends MongoRepository<ChartData, String> {

     ChartData save(ChartData chartData);



}
