package pl.sda.jobOffer.infrastructure;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;

public interface JobOfferDAO extends JpaRepository<JobOfferEntity, Long> {

    @Query("SELECT job FROM JobOfferEntity job WHERE lower(job.location) LIKE %:location% AND job.minSalary >= :minSalary AND job.maxSalary <= :maxSalary")
    Page<JobOfferEntity> findByLocationAndSalary(
            @Param("location") String location,
            @Param("minSalary") Double minSalary,
            @Param("maxSalary") Double maxSalary,
            Pageable pageable);
}
