package com.fbplamachine.onlineAdvertisingService.repositories;

import com.fbplamachine.onlineAdvertisingService.models.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealerRepository extends JpaRepository<Dealer, Long> {
    boolean existsByName(String name);


}
