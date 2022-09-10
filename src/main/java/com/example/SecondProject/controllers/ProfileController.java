package com.example.SecondProject.controllers;

import com.example.SecondProject.models.Post;
import com.example.SecondProject.models.Profile;
import com.example.SecondProject.repo.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public String profileAdd(Profile profile, Model model) {
        return "profile/add";
    }

    @PostMapping("/profiles/add")
    public String profilePostAdd(
            @ModelAttribute("profile") @Valid Profile profile,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "profile/add";
        }
        profile.setFollower(0);
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
    ) {
        List<Profile> result = profileRepository.findBySurnameContains(surname);
        model.addAttribute("result", result);
        return "profile/filter";
    }

    @PostMapping("profile/filter/allResult")
    public String profileAllResult(
            @RequestParam String surname,
            Model model
    ) {
        List<Profile> result = profileRepository.findBySurname(surname);
        model.addAttribute("result", result);
        return "profile/filter";
    }

    @GetMapping("/profile/{id}")
    public String profileDetails(@PathVariable("id") Long id, Model model) {
        Optional<Profile> profile = profileRepository.findById(id);
        if (profile.isEmpty()) {
            return "redirect:/profiles";
        }
        model.addAttribute("profile", profile.get());
        return "profile/details";
    }

    @GetMapping("/profile/{id}/edit")
    public String profileGetEdit(@PathVariable("id") Long id, Model model) {
        Optional<Profile> profile = profileRepository.findById(id);
        if (profile.isEmpty()) {
            return "redirect:/profiles";
        }
        model.addAttribute("profile", profile.get());
        return "profile/edit";
    }

    @PostMapping("/profile/{id}/edit")
    public String profilePostEdit(@ModelAttribute("profile") @Valid Profile profile,
                                  BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "profile/edit";
        }
        profileRepository.save(profile);
        return "redirect:/profiles";
    }

    @PostMapping("/profile/{id}/remove")
    public String profileRemove(@PathVariable("id") Long id, Model model) {
        Profile profile = profileRepository.findById(id).orElseThrow();
        profileRepository.delete(profile);
        return "redirect:/profiles";
    }

}