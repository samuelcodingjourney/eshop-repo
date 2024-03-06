package id.ac.ui.cs.advprog.eshop.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import id.ac.ui.cs.advprog.eshop.model.Payment;

public class PaymentRepository {
    private Map<String, Payment> paymentMap;

    public PaymentRepository() {
        this.paymentMap = new HashMap<>();
    }

    public Payment findById(String paymentId) {
        return paymentMap.get(paymentId);
    }

    public List<Payment> findAll() {
        return new ArrayList<>(paymentMap.values());
    }

    public void save(Payment payment) {
        paymentMap.put(payment.getId(), payment);
    }
}
