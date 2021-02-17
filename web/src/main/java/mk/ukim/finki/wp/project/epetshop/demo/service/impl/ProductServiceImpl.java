package mk.ukim.finki.wp.project.epetshop.demo.service.impl;


import mk.ukim.finki.wp.project.epetshop.demo.model.Product;
import mk.ukim.finki.wp.project.epetshop.demo.model.Type;
import mk.ukim.finki.wp.project.epetshop.demo.model.exceptions.InvalidProduct;
import mk.ukim.finki.wp.project.epetshop.demo.repository.ProductRepo;
import mk.ukim.finki.wp.project.epetshop.demo.repository.TypeRepo;
import mk.ukim.finki.wp.project.epetshop.demo.service.ProductService;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<Product> findAllProducts() {
        return this.productRepo.findAll();
    }

    @Override
    public Product findProduct(Long id) {
        return this.productRepo.findById(id).orElseThrow(InvalidProduct::new);
    }

    @Override
    public List<Product> findMostSellingProducts() {
        List<Product> products=productRepo.findAll(Sort.by(Sort.Direction.ASC, "sold"));
        if(products.size()<6){
            return products;
        }
        else{
            List<Product> firstSix=new ArrayList<>();
            for(int i=0;i<6;i++){
                firstSix.add(products.get(i));
            }
            return firstSix;
        }
    }

    @Override
    public Product updateProduct(Long id, Product newProduct) {
        Product product=this.findProduct(id);
        product.setImages(newProduct.getImages());
        product.setName(newProduct.getName());
        product.setPrice(newProduct.getPrice());
        product.setQuantity(newProduct.getQuantity());
        product.setSale(newProduct.getSale());
        product.setSharingMembers(newProduct.getSharingMembers());
        product.setSold(newProduct.getSold());
        product.setType(newProduct.getType());
        return this.productRepo.save(product);
    }

    @Override
    public Product addProduct(Product product) {
        return this.productRepo.save(product);
    }

    @Override
    public List<Product> findSimilarProducts(Long id) {
        Product product=this.findProduct(id);
        Type type=product.getType();
        List<Product> products=this.productRepo.findAllByTypeLike(type);
        products.sort(Comparator.comparing(Product::getSold).reversed());
        List<Product> firstFour=new ArrayList<>();
        for(int i=0;i<4;i++){
            firstFour.add(products.get(i));
        }
        return firstFour;
    }
}
