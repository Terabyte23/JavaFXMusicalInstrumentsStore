package com.example.MusicalInstrumentStoreFX.model.entity;

import jakarta.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Instruments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "instrument_brands",
            joinColumns = @JoinColumn(name = "instrument_id"),
            inverseJoinColumns = @JoinColumn(name = "brand_id")
    )
    private Set<Brand> brands = new HashSet<>();

    private int publicationYear;
    private int quantity;
    private int count;

    // Новое поле для сортировки инструментов
    private Integer orderNumber;

    public Instruments() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Brand> getBrand() {
        return brands;
    }

    public void setBrand(Set<Brand> brands) {
        this.brands = brands;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    // Геттер и сеттер для orderNumber
    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instruments instruments = (Instruments) o;
        return publicationYear == instruments.publicationYear &&
                quantity == instruments.quantity &&
                count == instruments.count &&
                Objects.equals(id, instruments.id) &&
                Objects.equals(title, instruments.title) &&
                Objects.equals(brands, instruments.brands) &&
                Objects.equals(orderNumber, instruments.orderNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, brands, publicationYear, quantity, count, orderNumber);
    }

    @Override
    public String toString() {
        return "Instrument{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", brands=" + Arrays.toString(brands.toArray()) +
                ", publicationYear=" + publicationYear +
                ", quantity=" + quantity +
                ", count=" + count +
                ", orderNumber=" + orderNumber +
                '}';
    }
}
