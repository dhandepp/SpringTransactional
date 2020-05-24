package com.stud.st.model;

import javax.persistence.*;

@Entity
@Table(name = "pizza")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private float price;

    public Pizza() {
        super();
    }

    public Pizza(int id, String name, float price) {
        this();
        this.id = id;
        this.name = name;
        this.price = price;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Pizza[" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ']';
    }
}
