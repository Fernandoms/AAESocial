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
}
