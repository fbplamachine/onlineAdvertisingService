package com.fbplamachine.onlineAdvertisingService.repositories;

import com.fbplamachine.onlineAdvertisingService.models.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListingRepository extends JpaRepository<Listing, Long> {
    List<Listing> findListingByDealerId(Long dealerId);
}
