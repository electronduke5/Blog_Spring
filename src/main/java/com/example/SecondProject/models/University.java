package com.example.SecondProject.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String name;
    @ManyToMany
    @JoinTable(name="user_university",
            joinColumns=@JoinColumn(name="university_id"),
            inverseJoinColumns=@JoinColumn(name="user_id"))
    private List<User> users;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getStudents() {
        return users;
    }

    public void setStudents(List<User> users) {
        this.users = users;
    }
}
