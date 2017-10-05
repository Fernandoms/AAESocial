package br.com.aaesocial.model;

public class NotificationStatusChanged extends Notification {
    @Override
    String notificationAction() {
        return "postou um novo status!";
    }
}
