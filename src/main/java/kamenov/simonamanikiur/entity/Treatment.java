package kamenov.simonamanikiur.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "treatments")
public class Treatment extends BaseEntity {
    private String name;
    private String description;
    private Double price;
    private String category; // "manicure" или "pedicure"
    private String imageUrl;

    public Treatment(String name, String description, Double price, String category, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    public Treatment() {

    }

    public String getName() {
        return name;
    }

    public Treatment setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Treatment setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Treatment setPrice(Double price) {
        this.price = price;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Treatment setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Treatment setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
