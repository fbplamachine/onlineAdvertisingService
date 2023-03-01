package com.fbplamachine.onlineAdvertisingService.config;

import com.fbplamachine.onlineAdvertisingService.models.Dealer;
import com.fbplamachine.onlineAdvertisingService.repositories.DealerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DealerConfig {
    @Bean
    CommandLineRunner commandLineRunner(DealerRepository repository) {
        return args -> {
            Dealer steph = new Dealer(
                    "Steph",
                    2,
                    LocalDateTime.now(),
                    LocalDateTime.now()
            );

            Dealer junior = new Dealer(
                    "Junior",
                    4,
                    LocalDateTime.now(),
                    LocalDateTime.now()
            );

            repository.saveAll(
                    List.of(steph, junior)
            );
        };
    }
}
