package br.com.aaesocial.model;

public class FactoryUser {
    public User getUser(boolean isCoorporative) {
        ProfileLayout layout ;
        User newUser;

        if (isCoorporative) {
            layout = new ProfileLayout("badge.jpg");
            newUser = new BusinessUser();
        } else {
            layout = new ProfileLayout();
            newUser = new PersonalUser();
        }

        newUser.setLayout(layout);

        return newUser;
    }
}
