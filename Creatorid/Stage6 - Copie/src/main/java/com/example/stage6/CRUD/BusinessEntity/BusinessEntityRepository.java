package com.example.stage6.CRUD.BusinessEntity;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessEntityRepository extends MongoRepository<BusinessEntity, String> {
}
