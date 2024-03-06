package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import java.util.UUID;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PaymentServiceImpl implements PaymentService {
    private PaymentRepository paymentRepository;
    private OrderRepository orderRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, OrderRepository orderRepository) {
    }

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
    }

    @Override
    public void setStatus(Payment payment, String status) {

    }

    @Override
    public Payment getPayment(String paymentId) {

    }

    @Override
    public List<Payment> getAllPayments() {
      
    }
}


