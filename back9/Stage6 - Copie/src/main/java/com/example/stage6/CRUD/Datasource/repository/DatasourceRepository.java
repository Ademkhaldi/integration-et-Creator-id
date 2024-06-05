package com.example.stage6.CRUD.Datasource.repository;
import com.example.stage6.CRUD.Datasource.entity.Datasource;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatasourceRepository extends MongoRepository<Datasource, String> {

}
