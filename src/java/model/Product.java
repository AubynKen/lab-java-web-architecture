package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable {

    // for version compatibility good practice
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SELLING_PRICE")
    private Double price;

    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
