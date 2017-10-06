package br.com.aaesocial.state;

import br.com.aaesocial.model.User;

public interface UserStatusState {
    UserStatusState accountActivity();

    UserStatusState idleAccount();

    String getNotification(User user, Object o);
}
