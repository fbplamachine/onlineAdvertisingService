package com.fbplamachine.onlineAdvertisingService.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
public class Listing {
    @Id
    @SequenceGenerator(
            name = "listing_sequence",
            sequenceName = "listing_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "listing_sequence"
    )
    private Long id;

    @ManyToOne(optional = false)
    private Dealer dealer;

    @Column(name = "dealerId")
    private Long dealerId;

    @Column(name = "vehicle")
    private String vehicle;

    @Column(name = "price")
    private Double price;

    @CreatedDate
    @Column(name = "createdAt", insertable = true, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updatedAt", insertable = false, updatable = true)
    private LocalDateTime updatedAt;

    @Column(name = "publishedAt")
    private LocalDateTime publishedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private ListingState listingState;

    public Listing() {
    }

    public Listing(Long id, Long dealerId, String vehicle, Double price, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime publishedAt, ListingState listingState) {
        this.id = id;
        this.dealerId = dealerId;
        this.vehicle = vehicle;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.publishedAt = publishedAt;
        this.listingState = listingState;
    }

    public Listing(Long dealerId, String vehicle, Double price, ListingState listingState) {
        this.dealerId = dealerId;
        this.vehicle = vehicle;
        this.price = price;
        this.listingState = listingState;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDealerId() {
        return dealerId;
    }

    public void setDealerId(Long dealerId) {
        this.dealerId = dealerId;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }

    public ListingState getState() {
        return listingState;
    }

    public void setState(ListingState listingState) {
        this.listingState = listingState;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    @Override
    public String toString() {
        return "Listing{" +
                "id=" + id +
                ", dealerId=" + dealerId +
                ", vehicle='" + vehicle + '\'' +
                ", price=" + price +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", publishedAt=" + publishedAt +
                ", state=" + listingState +
                ", dealer=" + dealer +
                '}';
    }
}
