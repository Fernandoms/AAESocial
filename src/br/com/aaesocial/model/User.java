package br.com.aaesocial.model;

import br.com.aaesocial.strategy.PriceStrategy;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public abstract class User extends Observable implements HttpSessionBindingListener, Observer {

    public User(PriceStrategy priceStrategy) {
        this.priceStrategy = priceStrategy;
        this.setNotifications(new ArrayList<>());
    }

    private List<String> notifications;

    private PriceStrategy priceStrategy;

    private Integer id;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getNotifications() {
        ArrayList<String> copy = new ArrayList<>();

        for (String s: notifications) {
            copy.add(s);
        }

        notifications.clear();

        return copy;
    }

    public void setNotifications(List<String> notifications) { this.notifications = notifications; }

    public void notifyAction() {
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        ServletContext context = event.getSession().getServletContext();
        List<User> logged = (List<User>) context.getAttribute("loggedUsers");

        for(User u: logged) {
            this.addObserver(u);
            u.addObserver(this);
        }

        logged.add(this);
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        ServletContext context = event.getSession().getServletContext();
        List<User> logged = (List<User>) context.getAttribute("loggedUsers");

        this.deleteObservers();
        logged.remove(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        notifications.add("I'm mr. meeseeks! Look at me! " + this.id);
    }
}
