package br.com.aaesocial.model;

public class FactoryUser {
    public User getUser(boolean isCoorporative) {
        if (isCoorporative) {
            return new BusinessUser();
        } else {
            return new PersonalUser();
        }
    }
}
