package com.fbplamachine.onlineAdvertisingService.services;

import com.fbplamachine.onlineAdvertisingService.exceptions.ResourceIsAlreadyExistException;
import com.fbplamachine.onlineAdvertisingService.exceptions.NotFoundException;
import com.fbplamachine.onlineAdvertisingService.models.Dealer;
import com.fbplamachine.onlineAdvertisingService.repositories.DealerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DealerService {


    static final String DEALER_ID_NOT_FOUND_MESSAGE = "Dealer not found with the id: {0} ";
    static final String DEALER_ALREADY_EXIST_MESSAGE = "Dealer is already exist with the name: {0} ";

    private final DealerRepository dealerRepository;

    @Autowired
    public DealerService(DealerRepository dealerRepository) {
        this.dealerRepository = dealerRepository;
    }

    //    fetch all dealers method
    public List<Dealer> getDealers() {
        return dealerRepository.findAll();
    }

    //    add dealer method
    public Dealer addDealer(Dealer dealer) {

//        check if dealer name is already exist
        if (dealerRepository.existsByName(dealer.getName()))
            throw new ResourceIsAlreadyExistException(MessageFormat.format(DEALER_ALREADY_EXIST_MESSAGE, dealer.getName()), HttpStatus.CONFLICT);

        dealer.setCreatedAt(LocalDateTime.now());

//        creating the listing
        return dealerRepository.save(dealer);
    }

    //    get dealer detail method
    public Dealer dealerDetail(Long dealerId) {
        return dealerRepository.findById(dealerId)
                .orElseThrow(() -> new NotFoundException(
                        MessageFormat.format(DEALER_ID_NOT_FOUND_MESSAGE, dealerId),
                        HttpStatus.NOT_FOUND));
    }

    public void deleteDealer(Long dealerId) {
        //        check if the dealer is existed
        boolean exists = dealerRepository.existsById(dealerId);
        if (!exists)
            throw new NotFoundException(MessageFormat.format(DEALER_ID_NOT_FOUND_MESSAGE, dealerId), HttpStatus.NOT_FOUND);

        //        deleting the dealer
        dealerRepository.deleteById(dealerId);
    }

    //    update dealer method
    @Transactional
    public Dealer updateDealer(Dealer dealer) {
        if (!dealerRepository.existsById(dealer.getId()))
            throw new NotFoundException(MessageFormat.format(DEALER_ID_NOT_FOUND_MESSAGE, dealer.getId()), HttpStatus.NOT_FOUND);

        dealer.setUpdatedAt(LocalDateTime.now());

        return dealerRepository.save(dealer);
    }
}
