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
}
