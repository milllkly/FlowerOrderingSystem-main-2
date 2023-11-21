package payment;

public class PayPalStrategy implements PaymentStrategy {
    @Override
    public void pay(String amount) {
        PayPalAdapter payPalAdapter = new PayPalAdapter();
        payPalAdapter.processPayment(amount);
    }
}
