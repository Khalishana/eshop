package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentRepository paymentRepository;
    @Override
    public Payment addPayment(String paymentId, Order order, String method, Map<String, String> paymentData) {
        if (paymentRepository.findById(paymentId) == null){
            Payment payment = new Payment(paymentId, method, order, paymentData);
            paymentRepository.save(payment);
            return payment;
        }
        return null;
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        PaymentStatus paymentStatus = PaymentStatus.valueOf(status);
        payment.setStatus(paymentStatus);
        Order currentOrder = payment.getOrder();
        if (paymentStatus == PaymentStatus.SUCCESS) {
            currentOrder.setStatus(OrderStatus.SUCCESS.getValue());
        } else if (paymentStatus == PaymentStatus.REJECTED) {
            currentOrder.setStatus(OrderStatus.FAILED.getValue());
        } else {
            throw new IllegalArgumentException("Invalid status payment");
        }
        paymentRepository.save(payment);
        return payment;
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.getAllPayment();
    }

}
