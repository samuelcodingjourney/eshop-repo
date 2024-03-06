package id.ac.ui.cs.advprog.eshop.repository;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import id.ac.ui.cs.advprog.eshop.model.Payment;
class PaymentRepositoryTest {
    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        this.paymentRepository = new PaymentRepository();
    }

    @Test
    void testSaveAndGetPayment() {
        Payment payment = new Payment("1", "Payment by Voucher Code", "WAITING_PAYMENT", new HashMap<>());
        paymentRepository.save(payment);
        Payment savedPayment = paymentRepository.findById("1");
        assertNotNull(savedPayment);
        assertEquals(payment.getId(), savedPayment.getId());
        assertEquals(payment.getMethod(), savedPayment.getMethod());
        assertEquals(payment.getStatus(), savedPayment.getStatus());
        assertEquals(payment.getPaymentData(), savedPayment.getPaymentData());
    }

    @Test
    void testFindAllPayments() {
        Payment payment1 = new Payment("1", "Payment by Voucher Code", "WAITING_PAYMENT", new HashMap<>());
        Payment payment2 = new Payment("2", "Payment by Bank Transfer", "SUCCESS", new HashMap<>());
        paymentRepository.save(payment1);
        paymentRepository.save(payment2);
        List<Payment> payments = paymentRepository.findAll();
        assertEquals(2, payments.size());
        assertTrue(payments.contains(payment1));
        assertTrue(payments.contains(payment2));
    }
}

