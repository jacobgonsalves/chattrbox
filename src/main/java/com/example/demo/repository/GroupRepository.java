package com.example.demo.repository;

import com.example.demo.model.Group;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends MongoRepository<Group, String> {

    Optional<Group> findByGroupName(String groupName);

    @Query("{ 'groupCity' : ?0, 'groupState' : ?1, 'groupPrivacy' : { $in : ['PUBLIC','RESTRICTED'] } }")
    List<Group> findPublicGroups(String city, String state);

}
