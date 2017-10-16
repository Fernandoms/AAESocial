package br.com.aaesocial.memento;

import br.com.aaesocial.model.ProfileLayout;

public class ProfileLayoutMemento {

    private final ProfileLayout layout;

    public ProfileLayoutMemento(ProfileLayout layout) {
        this.layout = layout;
    }

    public ProfileLayout getLayout() {
        return this.layout;
    }
}
