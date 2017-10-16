package br.com.aaesocial.model;

public class FactoryUser {
    public User getUser(boolean isCoorporative) {
        ProfileLayout layout = new ProfileLayout();
        User newUser;

        if (isCoorporative) {
            newUser = new BusinessUser();
        } else {
            newUser = new PersonalUser();
        }

        newUser.setLayout(layout);

        return newUser;
    }
}
