package br.com.aaesocial.model;

import br.com.aaesocial.strategy.PriceStrategy;

import java.time.LocalDate;

public abstract class User {

    public User(PriceStrategy priceStrategy) {
        this.priceStrategy = priceStrategy;
    }

    private PriceStrategy priceStrategy;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String photoUrl;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

}
