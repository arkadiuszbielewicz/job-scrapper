package pl.sda.jobOffer.domain;


import org.springframework.data.domain.*;
import org.springframework.scheduling.annotation.*;
import pl.sda.jobOffer.api.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class JobOfferService {

    private final List<JobScrapper> jobScrappers;
    private final JobOfferRepository jobOfferRepository;

    public JobOfferService(List<JobScrapper> jobScrappers, JobOfferRepository jobOfferRepository) {
        this.jobScrappers = jobScrappers;
        this.jobOfferRepository = jobOfferRepository;
    }

    @Scheduled(fixedDelay = 2, timeUnit = TimeUnit.HOURS)
    public void populateRepository() {
        List<JobOffer> jobOffers = jobScrappers.stream()
                .flatMap(e -> e.getJobOffers().stream())
                .collect(Collectors.toList());
        jobOfferRepository.replaceAll(jobOffers);
    }

    public List<JobOffer> findAll() {
        return jobOfferRepository.findAll();
    }

    public Page<JobOffer> filterByParams(FilterParamsDto filterParamsDto, Pageable pageable) {
        return jobOfferRepository
                .findOffersByLocationAndSalary(
                        filterParamsDto.getLocation(),
                        filterParamsDto.getMinSalaryOrDefault(),
                        filterParamsDto.getMaxSalaryOrDefault(),
                        pageable
                );
    }
}
