package com.example.backend.dto;

public class RestaurantRequest {

    private String name;
    private String address;
    private String cuisines;
    private double rating;
    private String openingHours;
    private boolean open;
    private Long ownerId;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCuisines() { return cuisines; }
    public void setCuisines(String cuisines) { this.cuisines = cuisines; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public String getOpeningHours() { return openingHours; }
    public void setOpeningHours(String openingHours) { this.openingHours = openingHours; }

    public boolean isOpen() { return open; }
    public void setOpen(boolean open) { this.open = open; }

    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }
}
