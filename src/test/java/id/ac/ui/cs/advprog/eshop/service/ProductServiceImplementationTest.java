package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        productService.create(product);
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAllProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());
        productList.add(new Product());
        when(productRepository.findAll()).thenReturn(productList.iterator());
        List<Product> retrievedProducts = productService.findAll();
        assertEquals(productList.size(), retrievedProducts.size());
    }
    @Test
    void testFindProductByName() {
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);
        ProductService productService = new ProductServiceImpl(productRepository);
        Product product1 = new Product();
        product1.setProductName("Test Product 1");

        Product product2 = new Product();
        product2.setProductName("Test Product 2");

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        when(productRepository.findAll()).thenReturn(productList.iterator());

        Product foundProduct = productService.findByName("Test Product 1");
        assertNotNull(foundProduct);
        assertEquals("Test Product 1", foundProduct.getProductName());
        foundProduct = productService.findByName("Non-existent Product");
        assertNull(foundProduct);
    }
    @Test
    void testFindProductByNameWhenNotFound() {
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);
        ProductService productService = new ProductServiceImpl(productRepository);

        Product product1 = new Product();
        product1.setProductName("Test Product 1");

        Product product2 = new Product();
        product2.setProductName("Test Product 2");
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        when(productRepository.findAll()).thenReturn(productList.iterator());
        System.out.println("Finding non-existent product...");
        assertNull(productService.findByName("Non-existent Product"));
    }
    @Test
    void testDeleteProductByName() {
        productService.deleteByName("Test Product");
        verify(productRepository, times(1)).deleteByName("Test Product");
    }

}



