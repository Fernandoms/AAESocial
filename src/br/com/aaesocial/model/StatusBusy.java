package br.com.aaesocial.model;

import br.com.aaesocial.state.UserStatusState;

public class StatusBusy implements UserStatusState {
    @Override
    public UserStatusState accountActivity() {
        return new StatusOnline();
    }

    @Override
    public UserStatusState idleAccount() {
        return new StatusAway();
    }

    @Override
    public String getNotification(User user, Object o) {
        if (o instanceof UserStatusState) {
            return null;
        }

        Notification notification = new NotificationMessageReceived();
        return notification.getNotificationMessage(user);
    }
}
