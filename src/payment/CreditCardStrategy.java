package payment;

public class CreditCardStrategy implements PaymentStrategy {
    @Override
    public void pay(String amount) {
        System.out.println("Paid $" + amount + " using Credit Card.");
    }
}
