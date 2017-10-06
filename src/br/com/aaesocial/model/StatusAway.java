package br.com.aaesocial.model;

import br.com.aaesocial.state.UserStatusState;

public class StatusAway implements UserStatusState {

    @Override
    public UserStatusState accountActivity() {
        return new StatusOnline();
    }

    @Override
    public UserStatusState idleAccount() {
        return new StatusOffline();
    }

    @Override
    public String getNotification(User user, Object o) {
        if (o instanceof Message) {
            return null;
        }

        Notification notification = new NotificationStatusChanged();
        return notification.getNotificationMessage(user);
    }
}
