package br.com.aaesocial.strategy;

public class PersonalAccountStrategy implements PriceStrategy {
    @Override
    public double valueToPay(int friendsNumber) {
        return friendsNumber * 0.2;
    }
}
