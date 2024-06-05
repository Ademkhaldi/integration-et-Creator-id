package com.example.stage6.CRUD.Portlet.repository;
import com.example.stage6.CRUD.Portlet.entity.Portlet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortletRepository extends MongoRepository<Portlet, String> {

}
