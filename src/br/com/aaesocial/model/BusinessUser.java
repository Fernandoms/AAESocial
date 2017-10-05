package br.com.aaesocial.model;

import br.com.aaesocial.strategy.BusinessAccountStrategy;
import br.com.aaesocial.strategy.PriceStrategy;

public class BusinessUser extends User {

    public BusinessUser() {
        super(new BusinessAccountStrategy());
    }
}
