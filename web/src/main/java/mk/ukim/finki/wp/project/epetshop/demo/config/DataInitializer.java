package mk.ukim.finki.wp.project.epetshop.demo.config;

import mk.ukim.finki.wp.project.epetshop.demo.model.Member;
import mk.ukim.finki.wp.project.epetshop.demo.model.Product;
import mk.ukim.finki.wp.project.epetshop.demo.model.ProductType;
import mk.ukim.finki.wp.project.epetshop.demo.model.enumerations.MemberRole;
import mk.ukim.finki.wp.project.epetshop.demo.model.enumerations.VerificationStatus;
import mk.ukim.finki.wp.project.epetshop.demo.service.MemberService;
import mk.ukim.finki.wp.project.epetshop.demo.service.ProductService;
import mk.ukim.finki.wp.project.epetshop.demo.service.TypeService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    public static final String ADMIN = "admin";

    private final MemberService userService;

    private final TypeService typeService;
    private final ProductService productService;

    public DataInitializer(MemberService userService, TypeService typeService, ProductService productService) {
        this.userService = userService;
        this.typeService = typeService;
        this.productService = productService;
    }


    @PostConstruct
    public void initData() {
        Member admin =
                this.userService.register(ADMIN, ADMIN,ADMIN,
                        ADMIN,ADMIN,ADMIN, MemberRole.ROLE_ADMIN, VerificationStatus.VERIFIED);
        ProductType productType =null;
        for (int i = 1; i < 6; i++) {
            productType = this.typeService.addProductType("Tip " + i);
        }
        this.productService.addProduct(new Product(productType, "https://i" +
                ".guim.co" +
                ".uk/img/media/fe1e34da640c5c56ed16f76ce6f994fa9343d09d" +
                "/0_174_3408_2046/master/3408" +
                ".jpg?width=1200&height=900&quality=85&auto=format&fit=crop&s" +
                "=0d3f33fb6aa6e0154b7713a00454c83d", "kuce", 100.0, 10, 10,
                10));
    }
}
