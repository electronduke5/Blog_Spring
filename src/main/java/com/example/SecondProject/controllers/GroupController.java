package com.example.SecondProject.controllers;

import com.example.SecondProject.models.UserGroup;
import com.example.SecondProject.repo.UserGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Controller
public class GroupController {
    @Autowired
    private UserGroupRepository userGroupRepository;

    @GetMapping("/groups")
    public String groupMain(Model model) {
        Iterable<UserGroup> groups = userGroupRepository.findAll();
        model.addAttribute("groups", groups);
        return "group/main";
    }

    @GetMapping("/groups/add")
    public String groupAdd(UserGroup group, Model model) {
        model.addAttribute("group", group);
        return "group/add";
    }

    @PostMapping("/groups/add")
    public String groupPostAdd(
            @ModelAttribute("group") @Valid UserGroup group,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "group/add";
        }
        group.setSubscribers(0);
        group.setDateFoundation(Calendar.getInstance().getTime());
        userGroupRepository.save(group);
        return "redirect:/groups";
    }

    @GetMapping("/groups/filter")
    public String groupFilter(Model model) {
        return "group/filter";
    }

    @PostMapping("groups/filter/result")
    public String groupFilterResult(
            @RequestParam String name,
            Model model
    ) {
        List<UserGroup> result = userGroupRepository.findByNameContains(name);
        model.addAttribute("result", result);
        return "group/filter";
    }

    @PostMapping("groups/filter/allResult")
    public String groupAllFilterResult(
            @RequestParam String name,
            Model model
    ) {
        List<UserGroup> result = userGroupRepository.findByName(name);
        model.addAttribute("result", result);
        return "group/filter";
    }

    @GetMapping("/group/{id}")
    public String groupDetails(@PathVariable(value = "id") Long id, Model model) {
        Optional<UserGroup> group = userGroupRepository.findById(id);
        if (group.isEmpty()) {
            return "redirect:/groups";
        }
        model.addAttribute("group", group.get());
        return "group/details";
    }

    @GetMapping("/group/{id}/edit")
    public String groupEdit(@PathVariable("id") Long id, Model model) {
        Optional<UserGroup> group = userGroupRepository.findById(id);
        if (group.isEmpty()) {
            return "redirect:/groups";
        }
        model.addAttribute("group", group.get());
        return "group/edit";
    }

    @PostMapping("/group/{id}/edit")
    public String groupPostEdit(@PathVariable("id") Long id,
                                @ModelAttribute("group") @Valid UserGroup group,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            return "group/edit";
        }
        userGroupRepository.save(group);
        return "redirect:/groups";
    }

    @PostMapping("/group/{id}/remove")
    public String groupRemove(@PathVariable("id") Long id, Model model) {
        userGroupRepository.deleteById(id);
        return "redirect:/groups";
    }

}
