package mk.ukim.finki.wp.project.epetshop.demo.service;

import mk.ukim.finki.wp.project.epetshop.demo.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAllProducts();
    Product findProduct(Long id);
    List<Product> findMostSellingProducts();
    Product updateProduct(Long id, Product newProduct);
    Product addProduct(Product product);
    List<Product> findSimilarProducts(Long id);
}
