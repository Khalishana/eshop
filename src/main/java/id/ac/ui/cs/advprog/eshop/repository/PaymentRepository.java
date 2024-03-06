package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepository {
    private List<Payment> payments = new ArrayList<>();
    public Payment save(Payment payment) {
        int i = 0;
        for (Payment savedPayment : payments) {
            if (savedPayment.getId().equals(payment.getId())) {
                payments.set(i, payment);
                return payment;
            }
            i += 1;
        }
        payments.add(payment);
        return payment;
    }

    public Payment findById(String paymentId) {
        for (Payment savedPayment : payments) {
            if (savedPayment.getId().equals(paymentId)) {
                return savedPayment;
            }
        }
        return null;
    }
    public List<Payment> getAllPayment() {
        List<Payment> result = new ArrayList<>();
        for (Payment savedPayment : payments) {
            result.add(savedPayment);
        }
        return result;
    }
}
