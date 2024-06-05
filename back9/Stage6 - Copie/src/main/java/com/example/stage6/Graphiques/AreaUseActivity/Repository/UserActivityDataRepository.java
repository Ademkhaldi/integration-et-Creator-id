package com.example.stage6.Graphiques.AreaUseActivity.Repository;

import com.example.stage6.Graphiques.AreaUseActivity.entity.UserActivityData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActivityDataRepository extends MongoRepository<UserActivityData, String> {
    // Ajoutez des méthodes personnalisées si nécessaire
    UserActivityData save(UserActivityData userActivityData);

}
