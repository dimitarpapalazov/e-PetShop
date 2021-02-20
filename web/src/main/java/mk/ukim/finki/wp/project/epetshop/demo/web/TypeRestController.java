package mk.ukim.finki.wp.project.epetshop.demo.web;

import mk.ukim.finki.wp.project.epetshop.demo.model.Product;
import mk.ukim.finki.wp.project.epetshop.demo.model.ProductType;
import mk.ukim.finki.wp.project.epetshop.demo.service.ProductService;
import mk.ukim.finki.wp.project.epetshop.demo.service.TypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5000")
@RequestMapping("/api/types")
public class TypeRestController {

    private final TypeService typeService;
    private final ProductService productService;

    public TypeRestController(TypeService typeService, ProductService productService) {
        this.typeService = typeService;
        this.productService = productService;
    }

    @GetMapping
    public List<ProductType> findAllTypes(){
        return this.typeService.findAllTypes();
    }

    @GetMapping("/collections")
    public List<Product> findProductsByType(@RequestParam ProductType type) {
        return this.productService.findAllByTypeLike(type);
    }

    @PostMapping("/add")
    public ResponseEntity<ProductType> save(@RequestBody ProductType type) {
        return this.typeService.addProductType(type)
                .map(t -> ResponseEntity.ok().body(t))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
