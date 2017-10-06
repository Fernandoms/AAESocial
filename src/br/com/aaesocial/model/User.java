package br.com.aaesocial.model;

import br.com.aaesocial.state.UserStatusState;
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
        this.notifications = new ArrayList<>();
        this.status = new StatusOffline();
        this.priceStrategy = priceStrategy;
    }

    private List<String> notifications;

    private UserStatusState status;

    private PriceStrategy priceStrategy;

    private Integer id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String photoUrl;

    private double valueToPay;

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

    public double getValueToPay() {
        return valueToPay;
    }

    public void setValueToPay(int messageNumber) {
        this.valueToPay = this.priceStrategy.valueToPay(messageNumber);
    }

    public void accountActivity() {
        this.status = this.status.accountActivity();
    }

    public void idle() {
        this.status = this.status.idleAccount();
        this.notify(this.status);
    }

    public List<String> getNotifications() {
        ArrayList<String> copy = new ArrayList<>();

        copy.addAll(notifications);

        notifications.clear();

        return copy;
    }

    public void notify(Object o) {
        this.setChanged();
        this.notifyObservers(o);
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        ServletContext context = event.getSession().getServletContext();
        List<User> logged = (List<User>) context.getAttribute("loggedUsers");

        this.status = this.status.accountActivity();

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

        this.status = this.status.idleAccount();

        this.deleteObservers();
        logged.remove(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        String notification = this.status.getNotification((User) observable, o);

        if (notification != null) {
            notifications.add(notification);
        }
    }
}
