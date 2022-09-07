package com.example.SecondProject.controllers;

import com.example.SecondProject.models.Post;
import com.example.SecondProject.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BlogController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/blog")
    public String blogMain(Model model){
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "blog/main";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model){
        return "blog/add";
    }

    @PostMapping("/blog/add")
    public String blogAdd(
            @RequestParam String title,
            @RequestParam String anons,
            @RequestParam String full_text,
            Model model
    ){

        Post post = new Post(title,anons,full_text, 0);
        postRepository.save(post);

        return "redirect:/blog";
    }

    @GetMapping("/blog/filter")
    public String blogFilter(Model model) {return "blog/filter";}

    @PostMapping("blog/filter/result")
    public String blogResult(@RequestParam String title, Model model){
        List<Post> result = postRepository.findByTitleContains(title);
        model.addAttribute("result", result);
        return "blog/filter";
    }
    @PostMapping("blog/filter/allResult")
    public String blogAllResult(@RequestParam String title, Model model){
        List<Post> result = postRepository.findByTitle(title);
        model.addAttribute("result", result);
        return "blog/filter";
    }


}
