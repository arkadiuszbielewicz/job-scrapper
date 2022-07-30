package pl.sda.jobOffer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.sda.jobOffer.domain.*;
import pl.sda.jobOffer.infrastructure.*;
import pl.sda.jobOffer.infrastructure.jobScrapper.JustJoinItJobScrapper;
import pl.sda.jobOffer.infrastructure.jobScrapper.NoFluffJobsJobScrapper;

import java.util.List;

@Configuration
public class SpringConfig {

    @Bean
    List<JobScrapper> jobScrappers(){
        return List.of(new JustJoinItJobScrapper(), new NoFluffJobsJobScrapper());
    }

    @Bean
    JobOfferService jobOfferService(List<JobScrapper> jobScrappers, JobOfferRepository jobOfferRepository){
        return new JobOfferService(jobScrappers, jobOfferRepository);
    }

    @Bean
    JobOfferRepository jobOfferRepository(JobOfferDAO jobOfferDAO) {
        return new JPAJobOfferRepository(jobOfferDAO);
    }


}
