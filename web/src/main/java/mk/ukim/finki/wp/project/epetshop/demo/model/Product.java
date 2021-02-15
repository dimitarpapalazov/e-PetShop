package mk.ukim.finki.wp.project.epetshop.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Type type;

    @OneToMany
    private List<Image> images;

    private String name;

    private Double price;

    private Integer quantity;

    private Integer sale;

    private Integer sold;

    public Product() {
    }
}
