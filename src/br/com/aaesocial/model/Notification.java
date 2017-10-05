package br.com.aaesocial.model;

public abstract class Notification {

    final String getNotificationMessage(User user){
        return user.getFirstName() + ", " + notificationAction();
    }

    abstract String notificationAction();
}
