package br.com.aaesocial.model;

import br.com.aaesocial.strategy.PersonalAccountStrategy;

public class PersonalUser extends User {
    public PersonalUser() {
        super(new PersonalAccountStrategy());
    }
}
