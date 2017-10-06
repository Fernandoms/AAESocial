package br.com.aaesocial.strategy;

public class PersonalAccountStrategy implements PriceStrategy {
    @Override
    public double valueToPay(int messageNumber) {
        return messageNumber * 0.2;
    }
}
