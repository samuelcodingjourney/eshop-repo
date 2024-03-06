package id.ac.ui.cs.advprog.eshop.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;

class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private OrderRepository orderRepository;

    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        paymentService = new PaymentServiceImpl(paymentRepository, orderRepository);
    }

    @Test
    void testAddPayment() {
        // Create test data
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        Order order = new Order("1", products, 123456L, "John Doe");
        String method = "Credit Card";
        Map<String, String> paymentData = new HashMap<>();

        doNothing().when(paymentRepository).save(any(Payment.class));
        Payment actualPayment = paymentService.addPayment(order, method, paymentData);
        assertNotNull(actualPayment);
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testSetStatusSuccess() {
        // Create a dummy payment with associated order
        List<Product> products = new ArrayList<>();
        products.add(new Product(/* Add necessary product details */));
        Order order = new Order("1", products, 123456L, "John Doe"); // Ensure products list is non-empty
        Payment payment = new Payment("123", "Credit Card", "WAITING_PAYMENT", new HashMap<>());
        payment.setOrder(order);
        when(orderRepository.findById(anyString())).thenReturn(order);

        paymentService.setStatus(payment, "SUCCESS");

        assertEquals("SUCCESS", payment.getStatus());
        assertEquals("SUCCESS", order.getStatus());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void testSetStatusRejected() {
        // Create a dummy payment with associated order
        List<Product> products = new ArrayList<>();
        products.add(new Product(/* Add necessary product details */));
        Order order = new Order("1", products, 123456L, "John Doe"); // Ensure products list is non-empty
        Payment payment = new Payment("123", "Credit Card", "WAITING_PAYMENT", new HashMap<>());
        payment.setOrder(order);
        when(orderRepository.findById(anyString())).thenReturn(order);
        paymentService.setStatus(payment, "REJECTED");

        assertEquals("REJECTED", payment.getStatus());
        assertEquals("FAILED", order.getStatus());
        verify(orderRepository, times(1)).save(order);
    }



    @Test
    void testGetPayment() {
        // Create a dummy payment
        Payment expectedPayment = new Payment("123", "Credit Card", "SUCCESS", new HashMap<>());
        when(paymentRepository.findById("123")).thenReturn(expectedPayment);
        Payment actualPayment = paymentService.getPayment("123");
        verify(paymentRepository, times(1)).findById("123");
        assertEquals(expectedPayment, actualPayment);
    }

    @Test
    void testGetAllPayments() {
        // Create dummy payments
        List<Payment> expectedPayments = new ArrayList<>();
        expectedPayments.add(new Payment("123", "Credit Card", "SUCCESS", new HashMap<>()));
        expectedPayments.add(new Payment("456", "PayPal", "REJECTED", new HashMap<>()));
        when(paymentRepository.findAll()).thenReturn(expectedPayments);
        List<Payment> actualPayments = paymentService.getAllPayments();
        verify(paymentRepository, times(1)).findAll();
        assertEquals(expectedPayments.size(), actualPayments.size());
        assertEquals(expectedPayments, actualPayments);
    }
}




