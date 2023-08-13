package com.wf.grab.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import com.wf.grab.IntegrationTest;
import com.wf.grab.domain.Campaign;
import com.wf.grab.repository.CampaignRepository;
import java.time.Duration;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link CampaignResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class CampaignResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_OWNER = "AAAAAAAAAA";
    private static final String UPDATED_OWNER = "BBBBBBBBBB";

    private static final String DEFAULT_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_DETAILS = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/campaigns";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private WebTestClient webTestClient;

    private Campaign campaign;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Campaign createEntity() {
        Campaign campaign = new Campaign().name(DEFAULT_NAME).owner(DEFAULT_OWNER).details(DEFAULT_DETAILS).type(DEFAULT_TYPE);
        return campaign;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Campaign createUpdatedEntity() {
        Campaign campaign = new Campaign().name(UPDATED_NAME).owner(UPDATED_OWNER).details(UPDATED_DETAILS).type(UPDATED_TYPE);
        return campaign;
    }

    @BeforeEach
    public void initTest() {
        campaignRepository.deleteAll().block();
        campaign = createEntity();
    }

    @Test
    void createCampaign() throws Exception {
        int databaseSizeBeforeCreate = campaignRepository.findAll().collectList().block().size();
        // Create the Campaign
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(campaign))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the Campaign in the database
        List<Campaign> campaignList = campaignRepository.findAll().collectList().block();
        assertThat(campaignList).hasSize(databaseSizeBeforeCreate + 1);
        Campaign testCampaign = campaignList.get(campaignList.size() - 1);
        assertThat(testCampaign.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCampaign.getOwner()).isEqualTo(DEFAULT_OWNER);
        assertThat(testCampaign.getDetails()).isEqualTo(DEFAULT_DETAILS);
        assertThat(testCampaign.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    void createCampaignWithExistingId() throws Exception {
        // Create the Campaign with an existing ID
        campaign.setId("existing_id");

        int databaseSizeBeforeCreate = campaignRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(campaign))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Campaign in the database
        List<Campaign> campaignList = campaignRepository.findAll().collectList().block();
        assertThat(campaignList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllCampaigns() {
        // Initialize the database
        campaignRepository.save(campaign).block();

        // Get all the campaignList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=id,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(campaign.getId()))
            .jsonPath("$.[*].name")
            .value(hasItem(DEFAULT_NAME))
            .jsonPath("$.[*].owner")
            .value(hasItem(DEFAULT_OWNER))
            .jsonPath("$.[*].details")
            .value(hasItem(DEFAULT_DETAILS))
            .jsonPath("$.[*].type")
            .value(hasItem(DEFAULT_TYPE));
    }

    @Test
    void getCampaign() {
        // Initialize the database
        campaignRepository.save(campaign).block();

        // Get the campaign
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, campaign.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(campaign.getId()))
            .jsonPath("$.name")
            .value(is(DEFAULT_NAME))
            .jsonPath("$.owner")
            .value(is(DEFAULT_OWNER))
            .jsonPath("$.details")
            .value(is(DEFAULT_DETAILS))
            .jsonPath("$.type")
            .value(is(DEFAULT_TYPE));
    }

    @Test
    void getNonExistingCampaign() {
        // Get the campaign
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingCampaign() throws Exception {
        // Initialize the database
        campaignRepository.save(campaign).block();

        int databaseSizeBeforeUpdate = campaignRepository.findAll().collectList().block().size();

        // Update the campaign
        Campaign updatedCampaign = campaignRepository.findById(campaign.getId()).block();
        updatedCampaign.name(UPDATED_NAME).owner(UPDATED_OWNER).details(UPDATED_DETAILS).type(UPDATED_TYPE);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedCampaign.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedCampaign))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Campaign in the database
        List<Campaign> campaignList = campaignRepository.findAll().collectList().block();
        assertThat(campaignList).hasSize(databaseSizeBeforeUpdate);
        Campaign testCampaign = campaignList.get(campaignList.size() - 1);
        assertThat(testCampaign.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCampaign.getOwner()).isEqualTo(UPDATED_OWNER);
        assertThat(testCampaign.getDetails()).isEqualTo(UPDATED_DETAILS);
        assertThat(testCampaign.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    void putNonExistingCampaign() throws Exception {
        int databaseSizeBeforeUpdate = campaignRepository.findAll().collectList().block().size();
        campaign.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, campaign.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(campaign))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Campaign in the database
        List<Campaign> campaignList = campaignRepository.findAll().collectList().block();
        assertThat(campaignList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchCampaign() throws Exception {
        int databaseSizeBeforeUpdate = campaignRepository.findAll().collectList().block().size();
        campaign.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, UUID.randomUUID().toString())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(campaign))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Campaign in the database
        List<Campaign> campaignList = campaignRepository.findAll().collectList().block();
        assertThat(campaignList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamCampaign() throws Exception {
        int databaseSizeBeforeUpdate = campaignRepository.findAll().collectList().block().size();
        campaign.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(campaign))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Campaign in the database
        List<Campaign> campaignList = campaignRepository.findAll().collectList().block();
        assertThat(campaignList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateCampaignWithPatch() throws Exception {
        // Initialize the database
        campaignRepository.save(campaign).block();

        int databaseSizeBeforeUpdate = campaignRepository.findAll().collectList().block().size();

        // Update the campaign using partial update
        Campaign partialUpdatedCampaign = new Campaign();
        partialUpdatedCampaign.setId(campaign.getId());

        partialUpdatedCampaign.owner(UPDATED_OWNER).type(UPDATED_TYPE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCampaign.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCampaign))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Campaign in the database
        List<Campaign> campaignList = campaignRepository.findAll().collectList().block();
        assertThat(campaignList).hasSize(databaseSizeBeforeUpdate);
        Campaign testCampaign = campaignList.get(campaignList.size() - 1);
        assertThat(testCampaign.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCampaign.getOwner()).isEqualTo(UPDATED_OWNER);
        assertThat(testCampaign.getDetails()).isEqualTo(DEFAULT_DETAILS);
        assertThat(testCampaign.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    void fullUpdateCampaignWithPatch() throws Exception {
        // Initialize the database
        campaignRepository.save(campaign).block();

        int databaseSizeBeforeUpdate = campaignRepository.findAll().collectList().block().size();

        // Update the campaign using partial update
        Campaign partialUpdatedCampaign = new Campaign();
        partialUpdatedCampaign.setId(campaign.getId());

        partialUpdatedCampaign.name(UPDATED_NAME).owner(UPDATED_OWNER).details(UPDATED_DETAILS).type(UPDATED_TYPE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCampaign.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCampaign))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Campaign in the database
        List<Campaign> campaignList = campaignRepository.findAll().collectList().block();
        assertThat(campaignList).hasSize(databaseSizeBeforeUpdate);
        Campaign testCampaign = campaignList.get(campaignList.size() - 1);
        assertThat(testCampaign.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCampaign.getOwner()).isEqualTo(UPDATED_OWNER);
        assertThat(testCampaign.getDetails()).isEqualTo(UPDATED_DETAILS);
        assertThat(testCampaign.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    void patchNonExistingCampaign() throws Exception {
        int databaseSizeBeforeUpdate = campaignRepository.findAll().collectList().block().size();
        campaign.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, campaign.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(campaign))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Campaign in the database
        List<Campaign> campaignList = campaignRepository.findAll().collectList().block();
        assertThat(campaignList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchCampaign() throws Exception {
        int databaseSizeBeforeUpdate = campaignRepository.findAll().collectList().block().size();
        campaign.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, UUID.randomUUID().toString())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(campaign))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Campaign in the database
        List<Campaign> campaignList = campaignRepository.findAll().collectList().block();
        assertThat(campaignList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamCampaign() throws Exception {
        int databaseSizeBeforeUpdate = campaignRepository.findAll().collectList().block().size();
        campaign.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(campaign))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Campaign in the database
        List<Campaign> campaignList = campaignRepository.findAll().collectList().block();
        assertThat(campaignList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteCampaign() {
        // Initialize the database
        campaignRepository.save(campaign).block();

        int databaseSizeBeforeDelete = campaignRepository.findAll().collectList().block().size();

        // Delete the campaign
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, campaign.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<Campaign> campaignList = campaignRepository.findAll().collectList().block();
        assertThat(campaignList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
