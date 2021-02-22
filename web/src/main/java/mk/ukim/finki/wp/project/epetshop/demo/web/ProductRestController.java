package mk.ukim.finki.wp.project.epetshop.demo.web;

import mk.ukim.finki.wp.project.epetshop.demo.model.Order;
import mk.ukim.finki.wp.project.epetshop.demo.model.Product;
import mk.ukim.finki.wp.project.epetshop.demo.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5000")
@RequestMapping("/api/products")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    private List<Product> findAllProducts() {
        return this.productService.findAllProducts();
    }

    @GetMapping("/most-selling")
    private List<Product> findMostSelling() {
        return this.productService.findMostSellingProducts();
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        try {
            Product p = this.productService.findProduct(id);
            return ResponseEntity.ok().body(p);
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/similar/{id}")
    public ResponseEntity<List<Product>> findSimilarProducts(@PathVariable Long id) {
        try {
            List<Product> products = this.productService.findSimilarProducts(id);
            return ResponseEntity.ok().body(products);
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //TODO da se smeni
    @PostMapping("/add")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        try {
            Product p = this.productService.addProduct(product);
            return ResponseEntity.ok().body(p);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    //TODO i ovde productDto
    @PutMapping("/edit/{id}")
    public ResponseEntity<Product> save(@PathVariable Long id, @RequestBody Product product) {
        try {
            Product p = this.productService.updateProduct(id, product);
            return ResponseEntity.ok().body(p);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.productService.deleteById(id);
        try {
            Product p = this.productService.findProduct(id);
            if (p != null)
                return ResponseEntity.ok().body(p);
            else return ResponseEntity.notFound().build();
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}

