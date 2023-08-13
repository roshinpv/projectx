package com.wf.grab.service;

import com.wf.grab.domain.Campaign;
import com.wf.grab.repository.CampaignRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link Campaign}.
 */
@Service
public class CampaignService {

    private final Logger log = LoggerFactory.getLogger(CampaignService.class);

    private final CampaignRepository campaignRepository;

    public CampaignService(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    /**
     * Save a campaign.
     *
     * @param campaign the entity to save.
     * @return the persisted entity.
     */
    public Mono<Campaign> save(Campaign campaign) {
        log.debug("Request to save Campaign : {}", campaign);
        return campaignRepository.save(campaign);
    }

    /**
     * Update a campaign.
     *
     * @param campaign the entity to save.
     * @return the persisted entity.
     */
    public Mono<Campaign> update(Campaign campaign) {
        log.debug("Request to update Campaign : {}", campaign);
        return campaignRepository.save(campaign);
    }

    /**
     * Partially update a campaign.
     *
     * @param campaign the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<Campaign> partialUpdate(Campaign campaign) {
        log.debug("Request to partially update Campaign : {}", campaign);

        return campaignRepository
            .findById(campaign.getId())
            .map(existingCampaign -> {
                if (campaign.getName() != null) {
                    existingCampaign.setName(campaign.getName());
                }
                if (campaign.getOwner() != null) {
                    existingCampaign.setOwner(campaign.getOwner());
                }
                if (campaign.getDetails() != null) {
                    existingCampaign.setDetails(campaign.getDetails());
                }
                if (campaign.getType() != null) {
                    existingCampaign.setType(campaign.getType());
                }

                return existingCampaign;
            })
            .flatMap(campaignRepository::save);
    }

    /**
     * Get all the campaigns.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Flux<Campaign> findAll(Pageable pageable) {
        log.debug("Request to get all Campaigns");
        return campaignRepository.findAllBy(pageable);
    }

    /**
     * Returns the number of campaigns available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return campaignRepository.count();
    }

    /**
     * Get one campaign by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Mono<Campaign> findOne(String id) {
        log.debug("Request to get Campaign : {}", id);
        return campaignRepository.findById(id);
    }

    /**
     * Delete the campaign by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(String id) {
        log.debug("Request to delete Campaign : {}", id);
        return campaignRepository.deleteById(id);
    }
}
