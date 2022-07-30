package pl.sda.jobOffer.domain;

import lombok.*;

@Data
@NoArgsConstructor

@AllArgsConstructor

public class JobOffer {
    private String title;
    private String company;
    private String location;
    private double minSalary;
    private double maxSalary;
    private String link;

    public JobOffer(String title, String company, String location, Double minSalary, Double maxSalary, String link) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.link = link;
    }
}
