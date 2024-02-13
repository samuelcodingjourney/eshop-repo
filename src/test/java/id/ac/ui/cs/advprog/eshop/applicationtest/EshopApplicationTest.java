package id.ac.ui.cs.advprog.eshop.applicationtest;
import id.ac.ui.cs.advprog.eshop.EshopApplication;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
class EshopApplicationTest {
    @Test
    void testMain() {
        assertDoesNotThrow(() -> EshopApplication.main(new String[]{}));
    }
}
