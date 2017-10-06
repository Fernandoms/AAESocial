package br.com.aaesocial.model;

import br.com.aaesocial.state.UserStatusState;

public class StatusOnline implements UserStatusState {
    @Override
    public UserStatusState accountActivity() {
        return this;
    }

    @Override
    public UserStatusState idleAccount() {
        return new StatusBusy();
    }

    @Override
    public String getNotification(User user, Object o) {
        Notification notification;

        if (o instanceof UserStatusState) {
            notification = new NotificationStatusChanged();
        } else {
            notification = new NotificationMessageReceived();
        }

        return notification.getNotificationMessage(user);
    }
}

