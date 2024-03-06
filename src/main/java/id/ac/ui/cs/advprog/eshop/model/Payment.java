package id.ac.ui.cs.advprog.eshop.model;


import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.HashSet;
import java.util.Set;

@Getter
public class Payment {
    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;
    private Order order;

    private static final Set<String> VALID_STATUSES = new HashSet<>();

    static {
        VALID_STATUSES.add("SUCCESS");
        VALID_STATUSES.add("REJECTED");
        VALID_STATUSES.add("WAITING_PAYMENT");
    }

    @Builder
    public Payment(String id, String method, String status, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;
        this.setStatus(status);
        this.paymentData = paymentData;
    }

    public void setStatus(String status) {
        if (isValidStatus(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Invalid payment status: " + status);
        }
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    private boolean isValidStatus(String status) {
        return VALID_STATUSES.contains(status);
    }
}









