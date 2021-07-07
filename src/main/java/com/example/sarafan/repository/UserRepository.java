package com.example.sarafan.repository;

import com.example.sarafan.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

}
