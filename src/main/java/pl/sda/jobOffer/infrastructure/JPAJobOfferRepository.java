package pl.sda.jobOffer.infrastructure;

import org.springframework.data.domain.*;
import pl.sda.jobOffer.domain.*;

import java.util.*;
import java.util.stream.*;

public class JPAJobOfferRepository implements JobOfferRepository {

    private final JobOfferDAO jobOfferDAO;

    public JPAJobOfferRepository(JobOfferDAO jobOfferDAO) {
        this.jobOfferDAO = jobOfferDAO;
    }

    @Override
    public void replaceAll(List<JobOffer> jobOffers) {
        jobOfferDAO.deleteAllInBatch();
        jobOfferDAO.saveAll(jobOffers.stream().map(JobOfferEntity::from).collect(Collectors.toList()));
    }

    @Override
    public List<JobOffer> findAll() {
        return jobOfferDAO.findAll().stream().map(JobOfferEntity::toJobOffer).collect(Collectors.toList());
    }

    @Override
    public Page<JobOffer> findOffersByLocationAndSalary(String location, Double minSalary, Double maxSalary, Pageable pageable) {
        return jobOfferDAO.findByLocationAndSalary(location.toLowerCase(), minSalary, maxSalary, pageable).map(JobOfferEntity::toJobOffer);
    }
}
