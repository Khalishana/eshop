package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {
    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;
    List<Order> orders;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708570000L, "Safira Sudrajat");
        orders.add(order1);
        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078",
                products, 1708570000L, "Safira Sudrajat");
        orders.add(order2);

        payments = new ArrayList<>();

        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP1234ABC567A");
        Payment payment1 = new Payment("8044cdbf-167c-4432-ae5b-0ce23d750401", PaymentMethod.VOUCHER_CODE.getMethod(), orders.get(1), paymentData1);
        payments.add(payment1);

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("bankName", "BCA");
        paymentData2.put("referenceCode", "11100");
        Payment payment2 = new Payment("d0a06bee-d457-4e62-8b30-e3c2d250dc8b", PaymentMethod.BANK_TRANSFER.getMethod(), orders.get(1), paymentData2);
        payments.add(payment2);
    }

    @Test
    void testAddPayment() {
        UUID uuid = UUID.randomUUID();
        String paymentId = uuid.toString();
        Payment payment = new Payment(paymentId, PaymentMethod.VOUCHER_CODE.getMethod(), orders.get(1), payments.get(1).getPaymentData());

        doReturn(null).when(paymentRepository).findById(paymentId);
        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(paymentId, orders.get(1), PaymentMethod.VOUCHER_CODE.getMethod(), payments.get(1).getPaymentData());

        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testAddPaymentIfAlreadyExist() {
        UUID uuid = UUID.randomUUID();
        String paymentId = uuid.toString();
        Payment payment = new Payment(paymentId, PaymentMethod.VOUCHER_CODE.getMethod(), orders.get(1), payments.get(1).getPaymentData());

        doReturn(payment).when(paymentRepository).findById(paymentId);
        Payment result = paymentService.addPayment(paymentId, orders.get(1), PaymentMethod.VOUCHER_CODE.getMethod(), payments.get(1).getPaymentData());
        verify(paymentRepository, times(1)).findById(paymentId);
        verify(paymentRepository, times(0)).save(any(Payment.class));
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testSetValidStatus() {
        Payment payment = payments.get(1);

        Payment resultSuccess = paymentService.setStatus(payment, PaymentStatus.SUCCESS);
        assertEquals(PaymentStatus.SUCCESS, resultSuccess.getStatus());
        assertEquals(OrderStatus.SUCCESS, resultSuccess.getOrder().getStatus());

        Payment resultRejected = paymentService.setStatus(payment, PaymentStatus.REJECTED);
        assertEquals(PaymentStatus.REJECTED, resultRejected.getStatus());
        assertEquals(OrderStatus.FAILED, resultRejected.getOrder().getStatus());
    }


    @Test
    void testFindByIdIfIdFound() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        Payment result = paymentService.getPayment(payment.getId());
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        doReturn(null).when(paymentRepository).findById("zczc");
        assertNull(paymentService.getPayment("zczc"));
    }

    @Test
    void testGetAllPayments() {
        doReturn(payments).when(paymentRepository).getAllPayment();

        List<Payment> results = paymentService.getAllPayments();
        assertEquals(payments, results);
    }

}