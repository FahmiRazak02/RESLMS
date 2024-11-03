package com.afzrealty.springboot.slmsystem.entity;

import jakarta.persistence.*;

import java.util.Arrays;

@Entity
@Table(name = "house_listing")
public class HouseListing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "property_id")
    private String propertyId;
    @Column(name = "property_type")
    private String propertyType;
    @Column(name = "address")
    private String address;
    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public HouseListing() {
    }

    public HouseListing(String propertyId, String propertyType, String address, double price, byte[] image) {
        this.propertyId = propertyId;
        this.propertyType = propertyType;
        this.address = address;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "HouseListing{" +
                "propertyId='" + propertyId + '\'' +
                ", propertyType='" + propertyType + '\'' +
                ", address='" + address + '\'' +
                ", price=" + price +
                '}';
    }
}
