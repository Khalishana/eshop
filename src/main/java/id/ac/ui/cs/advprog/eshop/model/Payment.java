package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Arrays;

@Getter
@Setter
public class Payment {
    String id;
    PaymentMethod method;
    Order order;
    Map<String, String> paymentData;
    PaymentStatus status;


    public Payment(String id, String method, Order order, Map<String, String> paymentData) {
        this.id = id;
        this.method = PaymentMethod.valueOf(method);
        String[] methodList = {"VOUCHER_CODE", "BANK_TRANSFER"};
        this.order = order;
        this.paymentData = paymentData;
        this.status = PaymentStatus.WAITING_PAYMENT;

        if (order == null) {
            throw new IllegalArgumentException("Order must not be null");
        }

        if (paymentData == null || paymentData.isEmpty()) {
            throw new IllegalArgumentException("Payment data cannot be empty");
        }

        if (Arrays.stream(methodList).noneMatch(item -> item.equals(method))) {
            throw new IllegalArgumentException("Invalid payment method");
        }

        if (this.method == PaymentMethod.VOUCHER_CODE) {
            this.status = verifyVoucherCode();
        } else if (this.method == PaymentMethod.BANK_TRANSFER) {
            this.status = verifyBankTransfer();
        }
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }


    private PaymentStatus verifyVoucherCode() {
        String voucherCode = this.paymentData.get("voucherCode");
        if (voucherCode == null) {
            return PaymentStatus.REJECTED;
        }

        if (voucherCode.length() != 16) {
            return PaymentStatus.REJECTED;
        }

        if (!voucherCode.startsWith("ESHOP")) {
            return PaymentStatus.REJECTED;
        }

        int numCount = 0;
        for (char character: voucherCode.toCharArray()) {
            if (Character.isDigit(character)) {
                numCount += 1;
            }
        }
        if (numCount != 8) {
            return PaymentStatus.REJECTED;
        }

        return PaymentStatus.SUCCESS;
    }

    private PaymentStatus verifyBankTransfer() {
        String bankName = this.paymentData.get("bankName");
        String referenceCode = this.paymentData.get("referenceCode");

        if (bankName == null || bankName.isEmpty()) {
            return PaymentStatus.REJECTED;
        }

        if (referenceCode == null || referenceCode.isEmpty()) {
            return PaymentStatus.REJECTED;
        }

        return PaymentStatus.SUCCESS;
    }
}
