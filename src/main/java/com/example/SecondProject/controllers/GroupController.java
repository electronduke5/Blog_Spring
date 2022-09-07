package com.example.SecondProject.controllers;

import com.example.SecondProject.models.UserGroup;
import com.example.SecondProject.repo.UserGroupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class GroupController {
    @Autowired
    private UserGroupRepository userGroupRepository;

    @GetMapping("/groups")
    public String groupMain(Model model){
        Iterable<UserGroup> groups = userGroupRepository.findAll();
        model.addAttribute("groups", groups);
        return "group/main";
    }

    @GetMapping("/groups/add")
    public String groupAdd(Model model){return "group/add";}

    @PostMapping("/groups/add")
    public String groupPostAdd(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam (required = false, defaultValue = "false") Boolean isPrivate,
            Model model
    ){
        UserGroup group = new UserGroup(name,description,0, Calendar.getInstance().getTime(),isPrivate);
        userGroupRepository.save(group);
        return "redirect:/groups";
    }

    @GetMapping("/groups/filter")
    public String groupFilter(Model model) {return "group/filter";}

    @PostMapping("groups/filter/result")
    public String groupFilterResult(
            @RequestParam String name,
            Model model
    ){
        List<UserGroup> result = userGroupRepository.findByNameContains(name);
        model.addAttribute("result", result);
        return "group/filter";
    }

    @PostMapping("groups/filter/allResult")
    public String groupAllFilterResult(
            @RequestParam String name,
            Model model
    ){
        List<UserGroup> result = userGroupRepository.findByName(name);
        model.addAttribute("result", result);
        return "group/filter";
    }
}
