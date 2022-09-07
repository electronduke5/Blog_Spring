package com.example.SecondProject.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class UserGroup {
    @Id
    @GeneratedValue
    private Long id;
    private String name, description;
    private Integer subscribers;
    private Date dateFoundation;
    private Boolean isPrivate;

    public UserGroup() {

    }

    public UserGroup(String name, String description, Integer subscribers, Date dateFoundation, Boolean isPrivate) {
        this.name = name;
        this.description = description;
        this.subscribers = subscribers;
        this.dateFoundation = dateFoundation;
        this.isPrivate = isPrivate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Integer subscribers) {
        this.subscribers = subscribers;
    }

    public String getDateFoundation() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd.MM.yyyy");
        return sdfDate.format(dateFoundation);
    }

    public void setDateFoundation(Date dateFoundation) {
        this.dateFoundation = dateFoundation;
    }

    public String getPrivate() {
        return isPrivate ? "Приватная группа" : "Публичная группа";
    }

    public Boolean getPrivateBoolean() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }
}
