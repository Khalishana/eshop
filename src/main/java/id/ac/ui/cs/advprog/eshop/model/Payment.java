package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Arrays;

@Getter
@Setter
public class Payment {
    String id;
    String method;
    Order order;
    Map<String, String> paymentData;
    String status;

    public Payment(String id, String method, Order order, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;
        String[] methodList = {"VOUCHER_CODE", "BANK_TRANSFER"};
        this.order = order;
        this.paymentData = paymentData;
        this.status = "WAITING_PAYMENT";

        if (order == null) {
            throw new IllegalArgumentException("Order must not be null");
        }

        if (paymentData == null || paymentData.isEmpty()) {
            throw new IllegalArgumentException("Payment data cannot be empty");
        }

        if (Arrays.stream(methodList).noneMatch(item -> item.equals(method))) {
            throw new IllegalArgumentException("Invalid payment method");
        }

        if (method.equals("VOUCHER_CODE")) {
            this.status = verifyVoucherCode();
        }
        else if (method.equals("BANK_TRANSFER")) {
            this.status = verifyBankTransfer();
        }
    }

    public void setStatus(String status) {

    }

    private String verifyVoucherCode() {
        String voucherCode = this.paymentData.get("voucherCode");
        if (voucherCode == null) {
            return "REJECTED";
        }

        if (voucherCode.length() != 16) {
            return "REJECTED";
        }

        if (!voucherCode.startsWith("ESHOP")) {
            return "REJECTED";
        }

        int numCount = 0;
        for (char character: voucherCode.toCharArray()) {
            if (Character.isDigit(character)) {
                numCount += 1;
            }
        }
        if (numCount != 8) {
            return "REJECTED";
        }

        return "SUCCESS";
    }

    private String verifyBankTransfer() {
        String bankName = this.paymentData.get("bankName");
        String referenceCode = this.paymentData.get("referenceCode");

        if (bankName == null || bankName.isEmpty()) {
            return "REJECTED";
        }

        if (referenceCode == null || referenceCode.isEmpty()) {
            return "REJECTED";
        }

        return "SUCCESS";
    }
}
