package com.example.stage6.CRUD.Dashboard.repository;
import com.example.stage6.CRUD.Dashboard.entity.Dashboard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DashboardRepository extends MongoRepository<Dashboard, String> {

}
