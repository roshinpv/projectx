package com.wf.grab.repository;

import com.wf.grab.domain.Campaign;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * Spring Data MongoDB reactive repository for the Campaign entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CampaignRepository extends ReactiveMongoRepository<Campaign, String> {
    Flux<Campaign> findAllBy(Pageable pageable);
}
