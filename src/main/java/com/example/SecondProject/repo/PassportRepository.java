package com.example.SecondProject.repo;

import com.example.SecondProject.models.Passport;
import org.springframework.data.repository.CrudRepository;

public interface PassportRepository extends CrudRepository<Passport, Long> {
    Passport findByNumber(String number);
}
