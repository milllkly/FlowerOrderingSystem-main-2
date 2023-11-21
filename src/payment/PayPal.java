package payment;

// PayPal class (simulated external service)
public class PayPal {
    public void sendPayment(String amount) {
        System.out.println("Payment of $" + amount + " processed via PayPal.");
    }
}

