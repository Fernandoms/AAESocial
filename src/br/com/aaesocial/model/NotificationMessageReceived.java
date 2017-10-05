package br.com.aaesocial.model;

public class NotificationMessageReceived extends Notification {
    @Override
    String notificationAction() {
        return "lhe enviou uma mensagem!";
    }
}
