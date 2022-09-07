package com.example.SecondProject.repo;


import com.example.SecondProject.models.Profile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
    List<Profile> findBySurname(String surname); //Точный поиск
    List<Profile> findBySurnameContains(String surname);
}
