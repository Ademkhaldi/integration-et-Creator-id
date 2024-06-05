package com.example.stage6.CRUD.Chart.repository;
import com.example.stage6.CRUD.Chart.entity.Chart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChartRepository extends MongoRepository<Chart, String> {

}
