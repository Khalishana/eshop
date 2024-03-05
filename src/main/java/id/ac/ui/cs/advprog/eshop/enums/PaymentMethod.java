package id.ac.ui.cs.advprog.eshop.enums;

import lombok.Getter;

@Getter
public enum PaymentMethod {
    VOUCHER_CODE("VOUCHER_CODE"),
    BANK_TRANSFER("BANK_TRANSFER");

    private final String method;

    PaymentMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return this.method;
    }

    public static boolean contains(String testMethod) {
        for (PaymentMethod paymentMethod : PaymentMethod.values()) {
            if (paymentMethod.method.equals(testMethod)) {
                return true;
            }
        }
        return false;
    }
}
