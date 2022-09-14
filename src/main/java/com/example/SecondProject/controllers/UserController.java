package com.example.SecondProject.controllers;

import com.example.SecondProject.models.*;
import com.example.SecondProject.repo.AddressRepository;
import com.example.SecondProject.repo.PassportRepository;
import com.example.SecondProject.repo.UniversityRepository;
import com.example.SecondProject.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private PassportRepository passportRepository;
    @GetMapping
    public String userList(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "userList";
    }

    @GetMapping("/{id}/edit")
    public String userEdit(@PathVariable(value = "id") long id, Model model){
        Optional<User> user = userRepository.findById(id);
        ArrayList<User> res = new ArrayList<>();
        user.ifPresent(res::add);
        model.addAttribute("user", res);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping
    public String userSave(@RequestParam String username, @RequestParam String number, @RequestParam String university, @RequestParam String street, @RequestParam(name="roles[]", required = false) String[] roles,
                           @RequestParam("userId") User user){
        user.setUsername(username);
        user.getRoles().clear();
        University university2 = universityRepository.findByName(university);
        Address address = addressRepository.findByStreet(street);
        Passport passport = passportRepository.findByNumber(number);
        user.setAddress(address);
        user.getUniversities().add(university2);
        user.setPasport(passport);
        if(roles!=null)
        {
            Arrays.stream(roles).forEach(r->user.getRoles().add(Role.valueOf(r)));
        }
        userRepository.save(user);
        universityRepository.save(university2);

        return "redirect:/admin";
    }
}
