package id.ac.ui.cs.advprog.eshop.model;
import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class PaymentTest {
    private Order order;
    private List<Product> products;
    private List<Order> orders;
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<>();
        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(1);
        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(2);
        this.products.add(product1);
        this.products.add(product2);

        this.orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b", this.products, 1708560000L, "Safira Sudrajat",
                OrderStatus.SUCCESS.getValue());
        Order order2 = new Order("19613522-022b-4c08-b146-54eb1396d79a", this.products, 170856000L, "Bambang Sudrajat",
                OrderStatus.SUCCESS.getValue());
        this.orders.add(order1);
        this.orders.add(order2);
    }

    @Test
    void testPaymentByVoucherCodeSuccessStatus() {
        this.paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Order matchedOrder = findOrderById("13652556-012a-4c07-b546-54eb1396d79b");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", matchedOrder, this.paymentData);

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testVoucherCodeRejectedStatusUnderSixteen() {
        this.paymentData.put("voucherCode", "ESHOP123");
        Order matchedOrder = findOrderById("13652556-012a-4c07-b546-54eb1396d79b");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", matchedOrder, this.paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testVoucherCodeRejectedStatusNoEshop() {
        this.paymentData.put("voucherCode", "12345678ABCDEFGH");
        Order matchedOrder = findOrderById("13652556-012a-4c07-b546-54eb1396d79b");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", matchedOrder, this.paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testVoucherCodeRejectedStatusUnderEight() {
        this.paymentData.put("voucherCode", "ESHOP123ABCDEFGH");
        Order matchedOrder = findOrderById("13652556-012a-4c07-b546-54eb1396d79b");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", matchedOrder, this.paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testBankTransferSuccessStatus() {
        this.paymentData.put("bankName", "BCA");
        this.paymentData.put("referenceCode", "11100");
        Order matchedOrder = findOrderById("13652556-012a-4c07-b546-54eb1396d79b");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "BANK_TRANSFER", matchedOrder, this.paymentData);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testBankTransferRejectedStatusNoBankName() {
        this.paymentData.put("referenceCode", "11100");
        Order matchedOrder = findOrderById("13652556-012a-4c07-b546-54eb1396d79b");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "BANK_TRANSFER", matchedOrder, this.paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testBankTransferRejectedStatusNoReferenceCode() {
        this.paymentData.put("bankName", "BCA");
        Order matchedOrder = findOrderById("13652556-012a-4c07-b546-54eb1396d79b");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "BANK_TRANSFER", matchedOrder, this.paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }
}
