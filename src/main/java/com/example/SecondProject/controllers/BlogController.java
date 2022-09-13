package com.example.SecondProject.controllers;

import com.example.SecondProject.models.Post;
import com.example.SecondProject.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/blog")
    public String blogMain(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "blog/main";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Post post, Model model) {
        return "blog/add";
    }

    @PostMapping("/blog/add")
    public String blogAdd(@ModelAttribute("post") @Valid Post post,
                          BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "blog/add";
        }
        postRepository.save(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/filter")
    public String blogFilter(Model model) {
        return "blog/filter";
    }

    @PostMapping("blog/filter/result")
    public String blogResult(@RequestParam String title, Model model) {
        List<Post> result = postRepository.findByTitleContains(title);
        model.addAttribute("result", result);
        return "blog/filter";
    }

    @PostMapping("blog/filter/allResult")
    public String blogAllResult(@RequestParam String title, Model model) {
        List<Post> result = postRepository.findByTitle(title);
        model.addAttribute("result", result);
        return "blog/filter";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()) {
            return "redirect:/blog";
        }
        model.addAttribute("post", post.get());
        return "blog/details";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable("id") Long id,
                           Model model) {

        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()) {
            return "redirect:/blog";
        }
        model.addAttribute("post", post.get());

        return "blog/edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@ModelAttribute("post") @Valid Post post,
                                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "blog/edit";
        }
        postRepository.save(post);
        return "redirect:/blog";
    }

    @PostMapping("/blog/{id}/remove")
    public String blogBlogDelete(@PathVariable("id") long id, Model model) {
        postRepository.deleteById(id);
        return "redirect:/blog";
    }
}