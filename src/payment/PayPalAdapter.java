package payment;

// Adapter for PayPal
public class PayPalAdapter implements PaymentProcessor {
    private final PayPal payPal;

    public PayPalAdapter() {
        payPal = new PayPal();
    }

    @Override
    public void processPayment(String amount) {
        payPal.sendPayment(amount);
    }
}
