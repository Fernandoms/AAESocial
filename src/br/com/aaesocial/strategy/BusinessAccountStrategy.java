package br.com.aaesocial.strategy;

public class BusinessAccountStrategy implements PriceStrategy {
    @Override
    public double valueToPay(int messageNumber) {
        return messageNumber * 0.5;
    }
}
