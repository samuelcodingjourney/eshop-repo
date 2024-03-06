package id.ac.ui.cs.advprog.eshop.model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.HashMap;

public class PaymentTest {
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<>();
        this.paymentData.put("voucherCode", "ESHOP1234ABC5678");
    }

    @Test
    void testCreatePayment() {
        Payment payment = new Payment("1", "Payment by Voucher Code", "WAITING_PAYMENT", paymentData);
        assertNotNull(payment);
        assertEquals("1", payment.getId());
        assertEquals("Payment by Voucher Code", payment.getMethod());
        assertEquals("WAITING_PAYMENT", payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testSetStatusSuccess() {
        Payment payment = new Payment("1", "Payment by Voucher Code", "WAITING_PAYMENT", paymentData);
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetStatusRejected() {
        Payment payment = new Payment("1", "Payment by Voucher Code", "WAITING_PAYMENT", paymentData);
        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetInvalidStatus() {
        Payment payment = new Payment("1", "Payment by Voucher Code", "WAITING_PAYMENT", paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("INVALID_STATUS"));
        assertEquals("WAITING_PAYMENT", payment.getStatus());
    }
}

