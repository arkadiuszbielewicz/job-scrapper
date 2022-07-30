package pl.sda.jobOffer.domain;

import org.springframework.data.domain.*;

import java.util.*;

public interface JobOfferRepository {

    void replaceAll(List<JobOffer> jobOffers);

    List<JobOffer> findAll();
    Page<JobOffer> findOffersByLocationAndSalary(String location, Double minSalary, Double maxSalary, Pageable pageable);
}
