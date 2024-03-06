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
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        Payment payment = new Payment(UUID.randomUUID().toString(), method, "WAITING_PAYMENT", paymentData);
        payment.setOrder(order);
        paymentRepository.save(payment);
        return payment;
    }

    @Override
    public void setStatus(Payment payment, String status) {
        payment.setStatus(status);
        if (status.equals("SUCCESS")) {
            Order order = orderRepository.findById(payment.getOrder().getId());
            if (order != null) {
                order.setStatus("SUCCESS");
                orderRepository.save(order);
            }
        } else if (status.equals("REJECTED")) {
            Order order = orderRepository.findById(payment.getOrder().getId());
            if (order != null) {
                order.setStatus("FAILED");
                orderRepository.save(order);
            }
        }
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}


