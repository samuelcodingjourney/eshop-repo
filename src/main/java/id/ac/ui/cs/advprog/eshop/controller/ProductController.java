package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    private ProductService service;
    
    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "createProduct";
    }
    
    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product, Model model) {
        service.create(product);
        return "redirect:list";
    }
    
    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "productList";
    }

    @GetMapping("/edit/{productName}")
    public String editProductPage(@PathVariable String productName, Model model) {
        Product existingProduct = service.findByName(productName);

        if (existingProduct != null) {
            model.addAttribute("product", existingProduct);
            return "editProduct";
        } else {
            return "redirect:/product/list";
        }
    }

    @PostMapping("/edit/{productName}")
    public String editProductPost(
            @PathVariable String productName,
            @ModelAttribute Product updatedProduct,
            Model model
    ) {

        Product existingProduct = service.findByName(productName);

        if (existingProduct != null) {
            existingProduct.setProductName(updatedProduct.getProductName());
            existingProduct.setProductQuantity(updatedProduct.getProductQuantity());

            return "redirect:/product/list";
        } else {
            return "redirect:/product/list";
        }
    }
    @GetMapping("/delete/{productName}")
    public String deleteProduct(@PathVariable String productName) {
        service.deleteByName(productName);
        return "redirect:/product/list";
    }
}
