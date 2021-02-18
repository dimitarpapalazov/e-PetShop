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
    private ProductType type;

    @OneToMany
    private List<Image> images;

    private String name;

    private Double price;

    private Integer quantity;

    private Integer sale;

    private Integer sold;

    @ManyToMany
    private List<Member> sharingMembers;

    public Product() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public void setSharingMembers(List<Member> sharingMembers) {
        this.sharingMembers = sharingMembers;
    }

    public Long getId() {
        return id;
    }

    public ProductType getType() {
        return type;
    }

    public List<Image> getImages() {
        return images;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getSale() {
        return sale;
    }

    public Integer getSold() {
        return sold;
    }

    public List<Member> getSharingMembers() {
        return sharingMembers;
    }
}
