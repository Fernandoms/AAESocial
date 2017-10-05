package br.com.aaesocial.strategy;

public class BusinessAccountStrategy implements PriceStrategy {
    @Override
    public double valueToPay(int friendsNumber) {
        return friendsNumber * 0.5;
    }
}
