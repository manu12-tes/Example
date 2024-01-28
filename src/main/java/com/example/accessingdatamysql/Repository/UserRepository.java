package com.example.accessingdatamysql.Repository;

import com.example.accessingdatamysql.Models.User;
import org.springframework.data.repository.CrudRepository;
public interface UserRepository extends CrudRepository <User,Integer> {

}
