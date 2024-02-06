package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }


    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findByName(String productName) {
        for (Product product : productData) {
            if (product.getProductName().equals(productName)) {
                return product;
            }
        }
        return null;
    }
    public void deleteByName(String productName) {
        Iterator<Product> iterator = productData.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProductName().equals(productName)) {
                iterator.remove();
                break;
            }
        }
    }
}
