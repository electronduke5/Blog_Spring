package com.example.SecondProject.models;

import net.bytebuddy.implementation.bind.annotation.Default;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class UserGroup {
    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 2, max = 50, message = "Размер данного поля должен быть в диапазоне от 2 до 50!")
    private String name, description;
    private Integer subscribers;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFoundation;
    @NonNull
    @Column(nullable = false)
    private boolean isPrivate;

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

    public String getDateFoundationString() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd.MM.yyyy");
        return sdfDate.format(dateFoundation);
    }

    public Date getDateFoundation() {
        return dateFoundation;
    }

    public void setDateFoundation(Date dateFoundation) {
        this.dateFoundation = dateFoundation;
    }

    public String getPrivateString() {
        return isPrivate ? "Приватная группа" : "Публичная группа";
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }
}
