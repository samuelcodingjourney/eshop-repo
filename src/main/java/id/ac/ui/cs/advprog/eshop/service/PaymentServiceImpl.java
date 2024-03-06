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
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    private static final String SUCCESS_STATUS = "SUCCESS";
    private static final String REJECTED_STATUS = "REJECTED";
    private static final String WAITING_PAYMENT_STATUS = "WAITING_PAYMENT";

    public PaymentServiceImpl(PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        String voucherCode = paymentData.get("voucherCode");
        String paymentStatus = isValidVoucherCode(voucherCode) ? "SUCCESS" : "REJECTED";
        Payment payment = new Payment(UUID.randomUUID().toString(), method, paymentStatus, paymentData);
        payment.setOrder(order);
        paymentRepository.save(payment);
        return payment;
    }

    private boolean isValidVoucherCode(String voucherCode) {
        if (voucherCode == null || voucherCode.length() != 16) {
            return false;
        }
        if (!voucherCode.startsWith("ESHOP")) {
            return false;
        }
        String numericalPart = voucherCode.substring(5);
        return numericalPart.matches("\\d{8}");
    }



    @Override
    public void setStatus(Payment payment, String status) {
        payment.setStatus(status);
        updateOrderStatus(payment, status);
    }

    private void updateOrderStatus(Payment payment, String status) {
        if (SUCCESS_STATUS.equals(status)) {
            updateOrder(payment, SUCCESS_STATUS);
        } else if (REJECTED_STATUS.equals(status)) {
            updateOrder(payment, "FAILED");
        }
    }

    private void updateOrder(Payment payment, String status) {
        Order order = orderRepository.findById(payment.getOrder().getId());
        if (order != null) {
            order.setStatus(status);
            orderRepository.save(order);
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



