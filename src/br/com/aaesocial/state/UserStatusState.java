package br.com.aaesocial.state;

public interface UserStatusState {
    UserStatusState accountActivity();

    UserStatusState idleAccount();
}
