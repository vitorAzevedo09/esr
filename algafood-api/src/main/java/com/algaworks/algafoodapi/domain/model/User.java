package com.algaworks.algafoodapi.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;

/**
 * 
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime registrationDate;

    @ManyToMany
    @JoinTable(name = "user_group", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<Group> groups = new ArrayList<>();


    public User(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
        this.password= builder.password;
        this.registrationDate = builder.registrationDate;
        this.groups = builder.groups;
    }

    public static class Builder {

        private Long id;

        private String name;

        private String email;

        private String password;

        private LocalDateTime registrationDate;

        private List<Group> groups = new ArrayList<>();

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setGroups(List<Group> groups) {
            this.groups = groups;
            return this;
        }

        public Builder setRegistrationDate(LocalDateTime registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }

        public static Builder newInstance(){
            return new Builder();
        }

        private Builder(){}

        public User build(){
            return new User(this);
        }
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGrupos(List<Group> grupos) {
        this.groups = grupos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean passwordMatches(String password) {
        return getPassword().equals(password);
    }

    public boolean passwordDontMatches(String password) {
        return !passwordMatches(password);
    }

}
