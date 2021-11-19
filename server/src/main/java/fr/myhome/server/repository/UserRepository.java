package fr.myhome.server.repository;

import java.util.Optional;

import fr.myhome.server.model.User;

public interface UserRepository extends AbstractRepository<User, Integer>{
    
    Optional<User> findByUserName(String userName);

}