package com.example.SecondProject.repo;

import com.example.SecondProject.models.UserGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserGroupRepository extends CrudRepository<UserGroup, Long> {
    List<UserGroup> findByName(String surname); //Точный поиск
    List<UserGroup> findByNameContains(String name); //Поиск по совпадениям символов
}
