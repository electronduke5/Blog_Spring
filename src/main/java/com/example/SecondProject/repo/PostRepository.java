package com.example.SecondProject.repo;

import com.example.SecondProject.models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findByTitle(String title); //Точный поиск
    List<Post> findByTitleContains(String title);
}
