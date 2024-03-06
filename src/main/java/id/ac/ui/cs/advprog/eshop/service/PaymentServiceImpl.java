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
        if (isCashOnDeliveryValid(paymentData)) {
            return createCashOnDeliveryPayment(order, method, paymentData);
        } else {
            return createRejectedPayment(order, method, paymentData);
        }
    }

    private boolean isCashOnDeliveryValid(Map<String, String> paymentData) {
        return paymentData.containsKey("address") && paymentData.containsKey("deliveryFee")
                && !paymentData.get("address").isEmpty() && !paymentData.get("deliveryFee").isEmpty();
    }

    private Payment createCashOnDeliveryPayment(Order order, String method, Map<String, String> paymentData) {
        return new Payment(UUID.randomUUID().toString(), method, "SUCCESS", paymentData);
    }

    private Payment createRejectedPayment(Order order, String method, Map<String, String> paymentData) {
        return new Payment(UUID.randomUUID().toString(), method, "REJECTED", paymentData);
    }

    private String determinePaymentStatus(String voucherCode) {
        return isValidVoucherCode(voucherCode) ? "SUCCESS" : "REJECTED";
    }

    private Payment createPayment(String method, String paymentStatus, Map<String, String> paymentData, Order order) {
        String paymentId = UUID.randomUUID().toString();
        return new Payment(paymentId, method, paymentStatus, paymentData);
    }

    private void savePayment(Payment payment) {
        paymentRepository.save(payment);
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



