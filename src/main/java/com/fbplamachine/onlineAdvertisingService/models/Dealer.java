package com.fbplamachine.onlineAdvertisingService.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Dealer {
    @Id
    @SequenceGenerator(
            name = "dealer_sequence",
            sequenceName = "dealer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "dealer_sequence"
    )
    @Column(name="dealerId")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "publishedListingsLimit")
    private int limitOfDealerPublishedListings;

    @CreatedDate
    @Column(name = "createdAt", insertable = true, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updatedAt", insertable = false, updatable = true)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "dealer")
    List<Listing> listings = new ArrayList<>();

    public Dealer() {
    }

    public Dealer(Long id, String name, int limitOfDealerPublishedListings, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.limitOfDealerPublishedListings = limitOfDealerPublishedListings;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Dealer(String name, int limitOfDealerPublishedListings, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.name = name;
        this.limitOfDealerPublishedListings = limitOfDealerPublishedListings;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Dealer(String name, int limitOfDealerPublishedListings) {
        this.name = name;
        this.limitOfDealerPublishedListings = limitOfDealerPublishedListings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLimitOfDealerPublishedListings() {
        return limitOfDealerPublishedListings;
    }

    public void setLimitOfDealerPublishedListings(int limitOfDealerPublishedListings) {
        this.limitOfDealerPublishedListings = limitOfDealerPublishedListings;
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

    @Override
    public String toString() {
        return "Dealer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", limitOfPublishedListings=" + limitOfDealerPublishedListings +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
