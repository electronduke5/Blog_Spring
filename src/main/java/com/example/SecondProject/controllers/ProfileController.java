package com.example.SecondProject.controllers;

import com.example.SecondProject.models.Profile;
import com.example.SecondProject.repo.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private ProfileRepository profileRepository;

    @GetMapping("/profiles")
    public String profilesMain(Model model) {
        Iterable<Profile> profiles = profileRepository.findAll();
        model.addAttribute("profiles", profiles);
        return "profile/main";
    }

    @GetMapping("/profiles/add")
    public String profileAdd(Model model) {
        return "profile/add";
    }

    @PostMapping("/profiles/add")
    public String profilePostAdd(
            @RequestParam String surname,
            @RequestParam String name,
            @RequestParam String patronymic,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth,
            Model model
    ) {
        Profile profile = new Profile(surname, name, patronymic, dateOfBirth, 0);
        profileRepository.save(profile);

        return "redirect:/profiles";
    }

    @GetMapping("/profile/filter")
    public String profileFilter(Model model) {
        return "profile/filter";
    }

    @PostMapping("profile/filter/result")
    public String profileResult(
            @RequestParam String surname,
            Model model
    ){
        List<Profile> result = profileRepository.findBySurnameContains(surname);
        model.addAttribute("result", result);
        return "profile/filter";
    }
    @PostMapping("profile/filter/allResult")
    public String profileAllResult(
            @RequestParam String surname,
            Model model
    ){
        List<Profile> result = profileRepository.findBySurname(surname);
        model.addAttribute("result", result);
        return "profile/filter";
    }

}