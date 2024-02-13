package id.ac.ui.cs.advprog.eshop.controller;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createProductPage() {
        String result = productController.createProductPage(model);
        assertEquals("createProduct", result);
        Product product = new Product();
        when(model.addAttribute("product", product)).thenReturn(model);
    }

    @Test
    void createProductPost() {
        Product product = new Product();
        String result = productController.createProductPost(product, model);
        assertEquals("redirect:list", result);
        productService.create(product);
    }

    @Test
    void productListPage() {
        List<Product> productList = new ArrayList<>();
        when(productService.findAll()).thenReturn(productList);
        String result = productController.productListPage(model);
        assertEquals("productList", result);
        when(model.addAttribute("products", productList)).thenReturn(model);
    }
    @Test
    void editProductPage_ExistingProduct() {
        String productName = "Test Product";
        Product existingProduct = new Product();
        existingProduct.setProductName(productName);
        when(productService.findByName(productName)).thenReturn(existingProduct);
        String result = productController.editProductPage(productName, model);
        assertEquals("editProduct", result);
        when(model.addAttribute("product", existingProduct)).thenReturn(model);
    }
    @Test
    void editProductPage_NonExistingProduct() {
        String productName = "Nonexistent Product";
        when(productService.findByName(productName)).thenReturn(null);
        String result = productController.editProductPage(productName, model);
        assertEquals("redirect:/product/list", result);
    }

    @Test
    void editProductPost_ExistingProduct() {
        String productName = "Test Product";
        Product updatedProduct = new Product();
        updatedProduct.setProductName(productName);
        Product existingProduct = new Product();
        existingProduct.setProductName(productName);
        when(productService.findByName(productName)).thenReturn(existingProduct);
        String result = productController.editProductPost(productName, updatedProduct, model);
        assertEquals("redirect:/product/list", result);
        existingProduct.setProductName(updatedProduct.getProductName());
        existingProduct.setProductQuantity(updatedProduct.getProductQuantity());
    }

    @Test
    void editProductPost_NonExistingProduct() {
        String productName = "Nonexistent Product";
        Product updatedProduct = new Product();
        updatedProduct.setProductName(productName);
        when(productService.findByName(productName)).thenReturn(null);
        String result = productController.editProductPost(productName, updatedProduct, model);
        assertEquals("redirect:/product/list", result);
    }
    @Test
    void deleteProduct() {
        String productName = "Test Product";
        String result = productController.deleteProduct(productName);
        assertEquals("redirect:/product/list", result);
        productService.deleteByName(productName);
    }
}

