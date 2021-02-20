package mk.ukim.finki.wp.project.epetshop.demo.web;

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

    @GetMapping("/most_selling")
    private List<Product> findMostSelling() {
        return this.productService.findMostSellingProducts();
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        return this.productService.findSimilarProducts(id).map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return this.productService.addProduct(product)
                .map(produkt -> ResponseEntity.ok().body(produkt))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Product> save(@PathVariable Long id, @RequestBody Product product) {
        return this.productService.updateProduct(id, product)
                .map(produkt -> ResponseEntity.ok().body(produkt))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.productService.deleteById(id);
        if(this.productService.findProduct(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

}

