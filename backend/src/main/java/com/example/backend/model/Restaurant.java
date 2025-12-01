package com.example.backend.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "restaurants")
@com.fasterxml.jackson.annotation.JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String cuisines; // e.g., "Chinese, Indian"
    private double rating;
    private String openingHours;
    private boolean open;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus status = ApprovalStatus.PENDING; // Default to PENDING

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    // A restaurant has menu categories
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<MenuCategory> categories;

    // Approval Status Enum
    public enum ApprovalStatus {
        PENDING, // Waiting for admin approval
        APPROVED, // Approved by admin, visible to customers
        REJECTED // Rejected by admin
    }

    // GETTERS & SETTERS
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCuisines() {
        return cuisines;
    }

    public double getRating() {
        return rating;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public boolean isOpen() {
        return open;
    }

    public ApprovalStatus getStatus() {
        return status;
    }

    public User getOwner() {
        return owner;
    }

    public List<MenuCategory> getCategories() {
        return categories;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void setStatus(ApprovalStatus status) {
        this.status = status;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setCategories(List<MenuCategory> categories) {
        this.categories = categories;
    }
}
