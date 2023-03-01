package com.fbplamachine.onlineAdvertisingService.services;

import com.fbplamachine.onlineAdvertisingService.exceptions.MaximumListingCountReachedException;
import com.fbplamachine.onlineAdvertisingService.exceptions.StateException;
import com.fbplamachine.onlineAdvertisingService.exceptions.NotFoundException;
import com.fbplamachine.onlineAdvertisingService.models.Dealer;
import com.fbplamachine.onlineAdvertisingService.models.Listing;
import com.fbplamachine.onlineAdvertisingService.models.ManageListingState;
import com.fbplamachine.onlineAdvertisingService.models.ListingState;
import com.fbplamachine.onlineAdvertisingService.repositories.DealerRepository;
import com.fbplamachine.onlineAdvertisingService.repositories.ListingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ListingService {

    static final String LISTING_ID_NOT_FOUND_MESSAGE = "Listing not found with the id: {0}";
    static final String LISTING_STATE_MANAGEMENT_MESSAGE = "The listing is already at the requested state: {0}";
    static final String LISTING_PUBLISHED_REACHED_MESSAGE = "You have reached the number of listing allowed: {0}";

    private final ListingRepository listingRepository;
    private final DealerRepository dealerRepository;

    @Autowired
    public ListingService(ListingRepository listingRepository, DealerRepository dealerRepository) {
        this.listingRepository = listingRepository;
        this.dealerRepository = dealerRepository;
    }

//    fetch all listings method
    public List<Listing> getListings() {
        return listingRepository.findAll();
    }

//    add listing method
    public Listing addListing(Listing listing) {
        fillListingDealerId(listing);

//        set state to default value(draft) if state is different to draft
        if(!listing.getState().equals(ListingState.draft))
            listing.setState(ListingState.draft);

        listing.setCreatedAt(LocalDateTime.now());

//        creating the listing
        return listingRepository.save(listing);
    }

//    get listing detail method
    @Transactional(readOnly = true)
    public Listing listingDetail(Long listingId) {
        return   listingRepository.findById(listingId)
                .orElseThrow(() -> new NotFoundException(
                        MessageFormat.format(LISTING_ID_NOT_FOUND_MESSAGE, listingId),
                        HttpStatus.NOT_FOUND)
                );
    }

//    delete listing method
    public void deleteListing(Long listingId) {
//        check if the listing is existed
        boolean exists = listingRepository.existsById(listingId);
        if (!exists)
            throw new NotFoundException(MessageFormat.format(LISTING_ID_NOT_FOUND_MESSAGE, listingId), HttpStatus.NOT_FOUND);

//        deleting the listing
        listingRepository.deleteById(listingId);
    }

//    update listing method
    @Transactional
    public Listing updateListing(Listing listing) {
        if (!listingRepository.existsById(listing.getId()))
            throw new NotFoundException(MessageFormat.format(LISTING_ID_NOT_FOUND_MESSAGE, listing.getId()), HttpStatus.NOT_FOUND);

        fillListingDealerId(listing);

        listing.setUpdatedAt(LocalDateTime.now());

//        updated the listing
        return listingRepository.save(listing);
    }

    private void fillListingDealerId(Listing listing) {
        Optional<Dealer> optionalDealer = dealerRepository.findById(listing.getDealerId());

        optionalDealer.ifPresent(listing::setDealer);
    }

//    manage listing state method
    public Listing manageListingState(Long listingId, ManageListingState manageListingState) {
//        get the listing by listingId
        Optional<Listing> optionalListing = listingRepository.findById(listingId);

//        check if the listing is present
        if (!optionalListing.isPresent())
            throw new NotFoundException(MessageFormat.format(LISTING_ID_NOT_FOUND_MESSAGE, listingId), HttpStatus.NOT_FOUND);

//        check if the state is not the same
        if (optionalListing.get().getState().equals(manageListingState.getListingState()))
            throw new StateException(MessageFormat.format(LISTING_STATE_MANAGEMENT_MESSAGE, optionalListing.get().getId()), HttpStatus.BAD_REQUEST);

//        get the listing by dealerId
        List<Listing> listings = listingRepository.findListingByDealerId(optionalListing.get().getDealerId());

        listings = listings.stream().filter(listing -> listing.getState().equals(ListingState.published)).collect(Collectors.toList());

        listings.stream().sorted(Comparator.comparing(Listing::getPublishedAt).reversed());

//        get the max listing count
        int numberOfPublished = listings.size();

//        check if the max listing is not reached
        if( numberOfPublished >= optionalListing.get().getDealer().getLimitOfDealerPublishedListings()){
            if(manageListingState.isShowException()) {
                log.trace(MessageFormat.format(LISTING_PUBLISHED_REACHED_MESSAGE, numberOfPublished));
                throw new MaximumListingCountReachedException(MessageFormat.format(LISTING_PUBLISHED_REACHED_MESSAGE, numberOfPublished), HttpStatus.UNAUTHORIZED);
            }

//            published the listing
            return  publishedListing(optionalListing.get(), manageListingState.getListingState());
        }

        return publishedListing(optionalListing.get(), manageListingState.getListingState());

    }

//    change listing state
    private Listing changeState(Listing listingToBeUpdated, ListingState listingState){
//        change the state
        listingToBeUpdated.setState(listingState);

        return listingRepository.save(listingToBeUpdated);
    }

//    change listing published date method
    private Listing publishedListing(Listing listingToBeUpdated, ListingState listingState){
//        change the publishing date
        listingToBeUpdated.setPublishedAt(LocalDateTime.now());

        return changeState(listingToBeUpdated, listingState);
    }
}
