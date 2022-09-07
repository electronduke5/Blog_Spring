package com.example.SecondProject.models;

import org.springframework.format.annotation.DateTimeFormat;
import org.thymeleaf.util.DateUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String surname, name, patronymic;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    private Integer follower;

    public Profile() {
    }

    public Profile(String surname, String name, String patronymic, Date dateOfBirth, Integer follower) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.follower = follower;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getDateOfBirth() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd.MM.yyyy");
        return sdfDate.format(dateOfBirth);
    }

    public String getDateOfBirthDate() throws ParseException {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        String date = DateUtils.format(dateOfBirth, "yyyy-MM-dd", Locale.ROOT);
        System.out.println("DATE: " + date);
        return date;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getFollower() {
        return follower;
    }

    public void setFollower(Integer follower) {
        this.follower = follower;
    }
}
